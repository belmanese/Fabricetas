package com.fabricetas.repos;

import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.PersistentFile;

/**
 * Interface for generic CRUD operations on a repository for a PersistentFile type.
 * Created on 11/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface PersistentFileRepository extends CrudRepository<PersistentFile, Integer> {}
