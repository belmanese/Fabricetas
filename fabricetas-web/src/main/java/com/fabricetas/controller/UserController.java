package com.fabricetas.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import com.fabricetas.domain.User;
import com.fabricetas.domain.dto.AutenticacionDto;
import com.fabricetas.domain.dto.UserDto;
import com.fabricetas.service.RoleService;
import com.fabricetas.service.UserService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a user
 * Created on 08/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    

    /**
     * To create a user
     * @param user for create
     * @return user created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        if (!UtilNumber.isNullOrZero(user.getUserId()))
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
        
        Integer idRole = user.getUserId();
        Role role = roleService.findOne( idRole );
        List<Role> roles = new ArrayList<Role>(); 
        roles.add( role );
        
        user.setUserId( 0 );
        user.setRole( roles );
        user.setEstado( "A" );
        
        User usuarioCreado = userService.create(user);

        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);        
    }

    /**
     * Read all users
     * @return user list
     */
    /*
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    */
    /**
     * Read all users
     * @return user list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> findAll( @RequestParam(value="fetch", required= false) String fetch ) {
        List<User> users = userService.findAll();
        
        List<UserDto> usersDto = new ArrayList<UserDto>(); 
        
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			UserDto userDto = userService.findOneDto( user.getUserId(), fetch);
	        
	        User usuario = userService.findOne( user.getUserId() );
	        userDto.setRole( usuario.getRole() );
			usersDto.add( userDto );
		}
                
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }
    
	/*
	 * find all user with role artist
	 * @return Collection with all artist
	 */
    @RequestMapping(value = "/role/artist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findAllUserWithRoleArtist() {
        List<User> artists = (List<User>) userService.findAllUserWithRoleArtist();
        if (artists.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(artists, HttpStatus.OK);
	}

    /**
     * Read a user by id
     * @param id of the user to find
     * @return found user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> findOne(
		@PathVariable("id") Integer id, 
		@RequestParam(value="fetch", required= false) String fetch) {
    	UserDto userDto = userService.findOneDto(id, fetch);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        User usuario = userService.findOne( id );
        userDto.setRole( usuario.getRole() );
        
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * To edit a user
     * @param user to edit
     * @return edited user
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (UtilNumber.isNullOrZero(user.getUserId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!userService.exist(user.getUserId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        user.setEstado( "A" );
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    /**
     * To remove a user
     * @param id of the user to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !userService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        User usuario = userService.findOne(id);
        usuario.setEstado( "D" );
        
        userService.update(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * To authenticate an user
     * @param autenticacionDto for authenticate
     * @return autenticacionDto to user authenticated
     */
    @RequestMapping(value = "/usuario/autenticar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutenticacionDto> autenticar(@RequestBody AutenticacionDto autenticacionDto) {
        
    	boolean isError = true;
    	if(autenticacionDto.getPassword() == null){
			autenticacionDto.setMensajeRespuesta( "El password no debe ser null." );
		}else if("l".equals( autenticacionDto.getTipoLogin() ) ){				//Validacion local
    		if(autenticacionDto.getName() == null){
    			autenticacionDto.setMensajeRespuesta( "El nombre no debe ser null en una autenticación local." );
    		}else{
	        	User user = userService.findOneByName( autenticacionDto.getName() );
	        	if( user == null){
	        		autenticacionDto.setMensajeRespuesta( "El usuario [" + autenticacionDto.getName() + "] no existe." );
	        	}else if( ("A".equals(user.getEstado())) && autenticacionDto.getPassword().equals( user.getPassword() ) ){
	        		autenticacionDto.setMensajeRespuesta( "Autenticación exitosa." );
	        		autenticacionDto.setEmail( user.getEmail() );
	        		autenticacionDto.setFirstName( user.getFirstName() );
	        		autenticacionDto.setIdentificationNumber( user.getIdentificationNumber() );
	        		autenticacionDto.setIdentificationType( user.getIdentificationType() );
	        		autenticacionDto.setLastName( user.getLastName() );
	        		autenticacionDto.setSsoId( user.getSsoId() );

	        		Collection<Role> roles = user.getRole();
	        		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
						Role role = (Role) iterator.next();
						autenticacionDto.setTipo( role.getRoleId().toString() );	
					}
	        		
	        		autenticacionDto.setUserId( user.getUserId().toString() );
	        		isError = false;
	        	}else{
	        		autenticacionDto.setMensajeRespuesta( "El password no coincide." );
	        	}
    		}        	
        }else if("f".equals( autenticacionDto.getTipoLogin() ) ){		//Validacion facebook
        	
        }else if("t".equals( autenticacionDto.getTipoLogin() ) ){		//Validacion tweeter
        	
        }else{															//Error no existe tipo de validacion
        	autenticacionDto.setMensajeRespuesta( "Tipo de autenticación no existe" );
        }
    	
    	//if (!UtilNumber.isNullOrZero(tshirt.getTshirtId()))
        //    return new ResponseEntity<>(HttpStatus.CONFLICT);
    	
    	//return new ResponseEntity<>(tshirtService.create(tshirt), HttpStatus.CREATED);
    	if(isError)
    		return new ResponseEntity<>(autenticacionDto, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(autenticacionDto, HttpStatus.CREATED);
    }
}
