package com.patiun.meetuprestapi.dao;


import com.patiun.meetuprestapi.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getById(Integer id) throws DaoException;

    List<T> getAll() throws DaoException;

    void create(T item) throws DaoException;

    void update(T item, Integer id) throws DaoException;

    void delete(Integer id) throws DaoException;

}
