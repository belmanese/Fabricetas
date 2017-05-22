package com.fabricetas.service.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Role;
import com.fabricetas.domain.dto.RoleDto;
import com.fabricetas.repos.RoleRepository;
import com.fabricetas.service.RoleService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

/**
 * Class used as a service for role on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FetchService<Role, RoleDto> fetchService;

    /**
     * To create a role
     * @param role for create
     * @return role created
     */
    public Role create(Role role) {
        return saveOrUpdate(role);
    }

    /**
     * To remove a role
     * @param id of the role to remove
     */
    public void delete(Integer id){
        roleRepository.delete(id);
    }

    /**
     * Check if a role exist
     * @param id of the role to check
     * @return if role exist
     */
    public Boolean exist(Integer id){
        return roleRepository.exists(id);
    }

    /**
     * Read a role by id
      * @param id of the role to find
     * @return found role
     */
    public Role findOne(Integer id){
        return roleRepository.findOne(id);
    }

    /**
     * Read a RoleDto by id
     * @param id of the role to find
     * @param fetch String of entities for fetch
     * @return found role
     */
    public RoleDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all roles
     * @return role list
     */
    public List<Role> findAll(){
        return Lists.newArrayList(roleRepository.findAll());
    }

    /**
     * To edit a role
     * @param role to edit
     * @return edited role
     */
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Private method for create or update a role
     * @param role to create or to update
     * @return role created or updated
     */
    private Role saveOrUpdate(Role role){
        return roleRepository.save(role);
    }

}