package com.fabricetas.service.impls;

import com.fabricetas.domain.User;
import com.fabricetas.domain.dto.UserDto;
import com.fabricetas.repos.UserRepository;
import com.fabricetas.service.UserService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FetchService<User, UserDto> fetchService;

    /**
     * To create a user
     * @param user for create
     * @return user created
     */
    public User create(User user) {
        return saveOrUpdate(user);
    }

    /**
     * To remove a user
     * @param id of the user to remove
     */
    public void delete(Integer id){
        userRepository.delete(id);
    }

    /**
     * Check if a user exist
     * @param id of the user to check
     * @return if user exist
     */
    public Boolean exist(Integer id){
        return userRepository.exists(id);
    }

    /**
     * Read all users
     * @return user list
     */
    public List<User> findAll(){
        return Lists.newArrayList(userRepository.findAllActivo());
    }
    
	/*
	 * find all user with role artist
	 * @return Collection with all artist
	 */
	public Collection<User> findAllUserWithRoleArtist() {
		return userRepository.findAllUserWithRoleArtist();
	}

    /**
     * Read a user by id
      * @param id of the user to find
     * @return found user
     */
    public User findOne(Integer id){
        return userRepository.findOne(id);
    }
    
	/**
	 * Read a UserDto by id
	 * @param id of the user to find
	 * @param fetch String of entities for fetch
	 * @return found user
	 */
	public UserDto findOneDto(Integer id, String fetch) {
		return fetchService.doFetch(findOne(id), fetch);
	}

    /**
     * To edit a user
     * @param user to edit
     * @return edited user
     */
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Private method for create or update a user
     * @param user to create or to update
     * @return user created or updated
     */
    private User saveOrUpdate(User user){
        return userRepository.save(user);
    }

    /**
	 * Read a user by name
	 * @param name of the user to find
	 * @return found user
	 */
	public User findOneByName(String name) {
		return userRepository.findOneByName( name );
	}

}