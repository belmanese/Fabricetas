package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.RoleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Role;

/**
 * Class used as a service for role on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("roleService")
public interface RoleService {

	/**
	 * To create a role
	 * @param role for create
	 * @return role created
	 */
	Role create(Role role);

	/**
	 * To remove a role
	 * @param id of the role to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a role exist
	 * @param id of the role to check
	 * @return if role exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a role by id
	 * @param id of the role to find
	 * @return found role
	 */
	Role findOne(Integer id);

	/**
	 * Read a RoleDto by id
	 * @param id of the role to find
	 * @param fetch String of entities for fetch
	 * @return found role
	 */
	RoleDto findOneDto(Integer id, String fetch);

	/**
	 * Read all roles
	 * @return role list
	 */
	List<Role> findAll();

	/**
	 * To edit a role
	 * @param role to edit
	 * @return edited role
	 */
	Role update(Role role);

}