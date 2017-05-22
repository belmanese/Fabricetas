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

import com.fabricetas.domain.Address;
import com.fabricetas.domain.dto.AddressDto;
import com.fabricetas.service.AddressService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Address
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * To create a address
     * @param address for create
     * @return address created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> create(@RequestBody Address address, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(address.getAddressId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(addressService.create(address), HttpStatus.CREATED);
    }

    /**
     * Read all addresss
     * @return address list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresss = addressService.findAll();
        if (addresss.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(addresss, HttpStatus.OK);
    }

    /**
     * Read a address by id
     * @param id of the address to find
     * @return found address
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> findOne(
    		@PathVariable("id") Integer id, 
    		@RequestParam(value="fetch", required= false) String fetch) {
    	AddressDto addressDto = addressService.findOneDto(id, fetch);
        if (addressDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    /**
     * To edit a address
     * @param address to edit
     * @return edited address
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        if (UtilNumber.isNullOrZero(address.getAddressId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!addressService.exist(address.getAddressId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(addressService.update(address), HttpStatus.OK);
    }

    /**
     * To remove a address
     * @param id of the address to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !addressService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}