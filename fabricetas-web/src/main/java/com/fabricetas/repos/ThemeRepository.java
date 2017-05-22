package com.fabricetas.repos;

import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.Theme;

/**
 * Interface for generic CRUD operations on a repository for a Theme type.
 * Created on 13/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface ThemeRepository extends CrudRepository<Theme, Integer> {}