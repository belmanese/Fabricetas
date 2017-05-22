package com.fabricetas.repos;

import com.fabricetas.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for a Role type.
 * Created on 11/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {}