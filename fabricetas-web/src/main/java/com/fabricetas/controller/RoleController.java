package com.fabricetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabricetas.domain.Role;
import com.fabricetas.domain.dto.RoleDto;
import com.fabricetas.service.RoleService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Role
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * To create a role
     * @param role for create
     * @return role created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> create(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(role.getRoleId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(roleService.create(role), HttpStatus.CREATED);
    }

    /**
     * Read all roles
     * @return role list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        if (roles.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /**
     * Read a role by id
     * @param id of the role to find
     * @return found role
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        RoleDto roleDto = roleService.findOneDto(id, fetch);
        if (roleDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    /**
     * To edit a role
     * @param role to edit
     * @return edited role
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        if (UtilNumber.isNullOrZero(role.getRoleId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!roleService.exist(role.getRoleId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(roleService.update(role), HttpStatus.OK);
    }

    /**
     * To remove a role
     * @param id of the role to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !roleService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}