package com.murathnakts.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RootEntity<E> {

    private Integer status;

    private E payload;

    private String errorMessage;

    public static <E> RootEntity<E> success(E payload) {
        RootEntity<E> rootEntity = new RootEntity<E>();
        rootEntity.setStatus(200);
        rootEntity.setPayload(payload);
        rootEntity.setErrorMessage(null);
        return rootEntity;
    }

    public static <E> RootEntity<E> error(String errorMessage) {
        RootEntity<E> rootEntity = new RootEntity<E>();
        rootEntity.setStatus(500);
        rootEntity.setPayload(null);
        rootEntity.setErrorMessage(errorMessage);
        return rootEntity;
    }
}
