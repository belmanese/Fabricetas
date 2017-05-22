package com.fabricetas.service.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Address;
import com.fabricetas.domain.dto.AddressDto;
import com.fabricetas.repos.AddressRepository;
import com.fabricetas.service.AddressService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

/**
 * Class used as a service for address on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FetchService<Address, AddressDto> fetchService;

    /**
     * To create a address
     * @param address for create
     * @return address created
     */
    public Address create(Address address) {
        return saveOrUpdate(address);
    }

    /**
     * To remove a address
     * @param id of the address to remove
     */
    public void delete(Integer id){
        addressRepository.delete(id);
    }

    /**
     * Check if a address exist
     * @param id of the address to check
     * @return if address exist
     */
    public Boolean exist(Integer id){
        return addressRepository.exists(id);
    }

    /**
     * Read a address by id
      * @param id of the address to find
     * @return found address
     */
    public Address findOne(Integer id){
        return addressRepository.findOne(id);
    }

	/**
	 * Read a UserDto by id
	 * @param id of the user to find
	 * @param fetch String of entities for fetch
	 * @return found user
	 */
	public AddressDto findOneDto(Integer id, String fetch) {
		return fetchService.doFetch(findOne(id), fetch);		
	}
	
    /**
     * Read all addresss
     * @return address list
     */
    public List<Address> findAll(){
        return Lists.newArrayList(addressRepository.findAll());
    }

    /**
     * To edit a address
     * @param address to edit
     * @return edited address
     */
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Private method for create or update a address
     * @param address to create or to update
     * @return address created or updated
     */
    private Address saveOrUpdate(Address address){
        return addressRepository.save(address);
    }

}