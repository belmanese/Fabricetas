package com.fabricetas.controller;

import java.util.Arrays;
import java.util.Collections;
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
import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.dto.TshirtDto;
//import com.fabricetas.generator.RandomElement;
import com.fabricetas.repos.StampRepository;
import com.fabricetas.repos.TshirtRepository;
import com.fabricetas.service.StampService;
import com.fabricetas.service.TshirtService;
import com.fabricetas.util.UtilNumber;
import com.google.common.collect.Lists;

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
    
    @Autowired
    private TshirtRepository tshirtRepository;

    @Autowired
    private StampService stampService;

    @Autowired
    private StampRepository stampRepository;
    
//    @Autowired
//    private RandomElement randomElement;

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
     * Read a tshirt by id
     * @param id of the tshirt to find
     * @return found tshirt
     */
    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TshirtDto> generate(
        @RequestParam(value="precioEstampa", required = false) String precioEstampa,
        @RequestParam(value="precioCamisa", required = false) String precioCamisa,
        @RequestParam(value="artistaId", required = false) String artistaId,
        @RequestParam(value="temaId", required = false) String temaId) {

    	List<Tshirt> tshirts = Lists.newArrayList();
    	List<Stamp> stamps = Lists.newArrayList();
    	
    	if(precioCamisa == null)
    		tshirts = tshirtService.findAll();
    	else
    		tshirts = tshirtRepository.findByLowerPrice(precioCamisa);
    	
    	if(precioEstampa == null && artistaId == null && temaId == null)
    		stamps = stampService.findAll();
    	
    	if(precioEstampa != null && artistaId == null && temaId == null)
    		stamps = stampRepository.findByLowerPrice(precioEstampa);
    	
    	if(precioEstampa != null && artistaId != null && temaId == null)
    		stamps = stampRepository.findByLowerPriceAndUser(precioEstampa, artistaId);
    	
    	if(precioEstampa != null && artistaId != null && temaId != null)
    		stamps = stampRepository.findByLowerPriceAndUserAndTheme(precioEstampa, artistaId, temaId);

    	Collections.shuffle(tshirts);
    	Collections.shuffle(stamps);
//    	RandomElement.generateElement(tshirts);
//    	RandomElement.generateElement(stamps);

    	Tshirt tshirtRandom = tshirts.get(0);
    	Stamp stampRandom = stamps.get(0);
    	
    	TshirtDto tshirtDtoRandom = new TshirtDto(
			tshirtRandom.getDescription(), tshirtRandom.getName(), tshirtRandom.getPath(), 
			tshirtRandom.getPrice(), tshirtRandom.getTshirtId());
    	
    	tshirtDtoRandom.setStamp(Arrays.asList(stampRandom));
    	
        if (tshirtDtoRandom == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tshirtDtoRandom, HttpStatus.OK);
    	
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