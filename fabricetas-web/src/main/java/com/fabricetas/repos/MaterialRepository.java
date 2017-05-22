package com.fabricetas.repos;

import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.Material;

/**
 * Interface for generic CRUD operations on a repository for a Material type.
 * Created on 13/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface MaterialRepository extends CrudRepository<Material, Integer> {}