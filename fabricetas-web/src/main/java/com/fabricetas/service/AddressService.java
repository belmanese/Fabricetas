package com.fabricetas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Address;
import com.fabricetas.domain.dto.AddressDto;

/**
 * Class used as a service for address on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("addressService")
public interface AddressService {

	/**
	 * To create a address
	 * @param address for create
	 * @return address created
	 */
	Address create(Address address);

	/**
	 * To remove a address
	 * @param id of the address to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a address exist
	 * @param id of the address to check
	 * @return if address exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a address by id
	 * @param id of the address to find
	 * @return found address
	 */
	Address findOne(Integer id);
	
	/**
	 * Read a AddressDto by id
	 * @param id of the address to find
	 * @param fetch String of entities for fetch
	 * @return found address
	 */
	AddressDto findOneDto(Integer id, String fetch);

	/**
	 * Read all addresss
	 * @return address list
	 */
	List<Address> findAll();

	/**
	 * To edit a address
	 * @param address to edit
	 * @return edited address
	 */
	Address update(Address address);

}