package org.nbd.repos;

public interface IRepo<T> {
    void add(T obj);

    T getByID(int id) throws Exception;

    void remove(int id) throws Exception;

    long getSize();
}