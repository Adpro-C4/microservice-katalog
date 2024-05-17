package com.adpro.katalog.api;

public enum API {

    GET_ORDER_DETAILS
            ("https://api-gateway-specialitystore.up.railway.app/purchase-service/order/view/order-data/");
    private final String url;

    API(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

