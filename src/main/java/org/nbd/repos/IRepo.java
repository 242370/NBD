package org.nbd.repos;

public interface IRepo<T> {
    void add(T obj);

    T getByID(int id);

    void remove(int id);

    long getSize();
}