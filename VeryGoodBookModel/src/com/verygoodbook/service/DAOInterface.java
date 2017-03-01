/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;

/**
 *
 * @author Administrator
 */
public interface DAOInterface<K, T> {

    void delete(T c) throws VGBException;

    T get(K id) throws VGBException;

    void insert(T c) throws VGBException;

    void update(T c) throws VGBException;
    
}
