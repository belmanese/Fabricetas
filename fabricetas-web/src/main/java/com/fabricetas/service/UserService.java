package com.fabricetas.service;

import com.fabricetas.domain.User;
import com.fabricetas.domain.dto.UserDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Class used as a service for user on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("userService")
public interface UserService {

	/**
	 * To create a user
	 * @param user for create
	 * @return user created
	 */
	User create(User user);

	/**
	 * To remove a user
	 * @param id of the user to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a user exist
	 * @param id of the user to check
	 * @return if user exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read all users
	 * @return user list
	 */
	List<User> findAll();
	
	/*
	 * find all user with role artist
	 * @return Collection with all artist
	 */
	Collection<User> findAllUserWithRoleArtist();

	/**
	 * Read a user by id
	 * @param id of the user to find
	 * @return found user
	 */
	User findOne(Integer id);
	
	/**
	 * Read a user by name
	 * @param name of the user to find
	 * @return found user
	 */
	User findOneByName(String name);
	
	/**
	 * Read a UserDto by id
	 * @param id of the user to find
	 * @param fetch String of entities for fetch
	 * @return found user
	 */
	UserDto findOneDto(Integer id, String fetch);

	/**
	 * To edit a user
	 * @param user to edit
	 * @return edited user
	 */
	User update(User user);

}