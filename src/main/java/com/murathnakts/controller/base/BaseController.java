package com.murathnakts.controller.base;

public class BaseController {

    public <E> RootEntity<E> success(E payload) {
        return RootEntity.success(payload);
    }

    public <E> RootEntity<E> error(String errorMessage) {
        return RootEntity.error(errorMessage);
    }
}
