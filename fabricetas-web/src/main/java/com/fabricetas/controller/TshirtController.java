package com.fabricetas.controller;

import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.dto.TshirtDto;
import com.fabricetas.service.TshirtService;
import com.fabricetas.util.UtilNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Controller that responds to http requests related to a Tshirt
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/tshirt")
public class TshirtController {

    @Autowired
    private TshirtService tshirtService;

    /**
     * To create a tshirt
     * @param tshirt for create
     * @return tshirt created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tshirt> create(@RequestBody Tshirt tshirt, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(tshirt.getTshirtId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(tshirtService.create(tshirt), HttpStatus.CREATED);
    }

    /**
     * Read all tshirts
     * @return tshirt list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tshirt>> findAll() {
        List<Tshirt> tshirts = tshirtService.findAll();
        if (tshirts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tshirts, HttpStatus.OK);
    }

    /**
     * Read a tshirt by id
     * @param id of the tshirt to find
     * @return found tshirt
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TshirtDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        TshirtDto tshirtDto = tshirtService.findOneDto(id, fetch);
        if (tshirtDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tshirtDto, HttpStatus.OK);
    }

    /**
     * To edit a tshirt
     * @param tshirt to edit
     * @return edited tshirt
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tshirt> updateTshirt(@RequestBody Tshirt tshirt) {
        if (UtilNumber.isNullOrZero(tshirt.getTshirtId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!tshirtService.exist(tshirt.getTshirtId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tshirtService.update(tshirt), HttpStatus.OK);
    }

    /**
     * To remove a tshirt
     * @param id of the tshirt to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !tshirtService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        tshirtService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}