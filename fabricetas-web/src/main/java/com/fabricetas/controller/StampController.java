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

import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.dto.StampDto;
import com.fabricetas.domain.dto.view.StampForHomeDto;
import com.fabricetas.service.StampService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Stamp
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/stamp")
public class StampController {

    @Autowired
    private StampService stampService;

    /**
     * To create a stamp
     * @param stamp for create
     * @return stamp created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stamp> create(@RequestBody StampDto stampDto, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(stampDto.getStampId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(stampService.create(stampDto.entity()), HttpStatus.CREATED);
    }

    /**
     * Read all stamps
     * @return stamp list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stamp>> findAll() {
        List<Stamp> stamps = stampService.findAll();
        if (stamps.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(stamps, HttpStatus.OK);
    }

    /**
     * Read all stamps
     * @return stamp list
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StampForHomeDto>> findAllForHome(
    		@RequestParam(value="artistId", required= false) Integer userId,
    		@RequestParam(value="themeId", required= false) Integer themeId) {
    	
    	List<StampForHomeDto> stamps = (List<StampForHomeDto>) stampService.findAllByHome(userId,themeId);
        if (stamps.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(stamps, HttpStatus.OK);
    }
    /*@RequestMapping(value = "/home/{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StampForHomeDto>> findAllForHome(@PathVariable("artist") Integer userId) {
    	
    	List<StampForHomeDto> stamps = (List<StampForHomeDto>) stampService.findAllByHome(userId);
        if (stamps.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(stamps, HttpStatus.OK);
    }*/

    /**
     * Read a stamp by id
     * @param id of the stamp to find
     * @return found stamp
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StampDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        StampDto userDto = stampService.findOneDto(id, fetch);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * To edit a stamp
     * @param stamp to edit
     * @return edited stamp
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stamp> updateStamp(@RequestBody StampDto stampDto) {
        if (UtilNumber.isNullOrZero(stampDto.getStampId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!stampService.exist(stampDto.getStampId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(stampService.update(stampDto.entity(stampService.findOne(stampDto.getStampId()))), HttpStatus.OK);
    }

    /**
     * To remove a stamp
     * @param id of the stamp to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !stampService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        stampService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
