package com.fabricetas.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fabricetas.domain.User;

/**
 * Interface for generic CRUD operations on a repository for a User type.
 * Created on 08/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface UserRepository  extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u INNER JOIN u.role r WHERE r.name = 'Artista' AND UPPER(u.estado) = 'A'")
	Collection<User> findAllUserWithRoleArtist();
	
	@Query("SELECT u FROM User u WHERE UPPER(u.name) = UPPER(?1) AND UPPER(u.estado) = 'A'")
	User findOneByName( String name );
		
	@Query("SELECT u FROM User u WHERE UPPER(u.estado) = 'A'")
	Collection<User> findAllActivo();
}