package com.fabricetas.repos;

import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.Rating;

/**
 * Interface for generic CRUD operations on a repository for a Rating type.
 * Created on 13/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface RatingRepository extends CrudRepository<Rating, Integer> {}