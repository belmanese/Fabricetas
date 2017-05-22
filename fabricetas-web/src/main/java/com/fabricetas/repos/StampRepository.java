package com.fabricetas.repos;

import com.fabricetas.domain.Stamp;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for a Stamp type.
 * Created on 23/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface StampRepository extends CrudRepository<Stamp, Integer> { }