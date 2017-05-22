package com.fabricetas.repos;

import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.Address;

/**
 * Interface for generic CRUD operations on a repository for a Address type.
 * Created on 11/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface AddressRepository extends CrudRepository<Address, Integer> { }