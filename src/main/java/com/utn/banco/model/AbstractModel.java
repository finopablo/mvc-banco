package com.utn.banco.model;

import java.util.List;

/**
 * Created by pablis on 17/04/17.
 */
public interface AbstractModel<T> {
    T add(T value) throws  UnsupportedOperationException;
    void remove(T value) throws  UnsupportedOperationException;
    T update(T value) throws  UnsupportedOperationException;
    void remove(String id) throws  UnsupportedOperationException;
    T getById(String id) throws  UnsupportedOperationException;
    List<T> getAll() throws  UnsupportedOperationException;
}
