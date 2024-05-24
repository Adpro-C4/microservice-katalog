package com.adpro.katalog.service;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.model.dto.ProductDTO;
import com.adpro.katalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        // Use findAll method from JpaRepository to fetch all products
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) throws NoSuchElementException {
        // Use getReferenceById method from JpaRepository to find a product by ID
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new NoSuchElementException("No such product with id: " + productId);
        }
    }

    @Override
    public Product edit(Product updatedProduct) throws NoSuchElementException {
        Long productId = updatedProduct.getId();
        Product product = findById(productId);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setDiscount(updatedProduct.getDiscount());
        product.setBrand(updatedProduct.getBrand());
        product.setCategory(updatedProduct.getCategory());
        product.setImage(updatedProduct.getImage());
        product.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) throws NoSuchElementException {
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void updateProductsStock(List<ProductDTO> productDTOs) {
        // Mengonversi List<ProductDTO> ke Map<Long, ProductDTO> menggunakan ID yang dikonversi menjadi Long
        Map<Long, ProductDTO> productDTOMap = productDTOs.stream()
                .collect(Collectors.toMap(dto -> Long.parseLong(dto.getProductId()), dto -> dto));

        // Mengambil produk dari database berdasarkan ID yang ada di List<ProductDTO>
        List<Product> products = productRepository.findAllById(productDTOMap.keySet());

        // Memperbarui stok produk berdasarkan data yang ada di List<ProductDTO>
        for (Product product : products) {
            ProductDTO dto = productDTOMap.get(product.getId());
            if (dto != null) {
                product.setQuantity(product.getQuantity() - dto.getQuantity()); // Menetapkan stok baru dari DTO
                // Check if the product quantity is zero and send WebSocket message
            }
        }

        // Menyimpan perubahan ke basis data
        productRepository.saveAll(products);
    }
}
