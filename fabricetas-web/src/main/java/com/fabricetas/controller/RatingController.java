package com.fabricetas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabricetas.domain.Rating;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.dto.RatingDto;
import com.fabricetas.service.RatingService;
import com.fabricetas.service.StampService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Rating
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private StampService stampService;

    /**
     * To create a rating
     * @param rating for create
     * @return rating created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> create(@RequestBody Rating rating, UriComponentsBuilder ucBuilder) {
    	if (!UtilNumber.isNullOrZero(rating.getRatingId()))
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	return new ResponseEntity<>(ratingService.create(rating), HttpStatus.CREATED);
    }

    /**
     * Read all ratings
     * @return rating list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rating>> findAll() {
        List<Rating> ratings = ratingService.findAll();
        if (ratings.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    /**
     * Read a rating by id
     * @param id of the rating to find
     * @return found rating
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        RatingDto ratingDto = ratingService.findOneDto(id, fetch);
        if (ratingDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ratingDto, HttpStatus.OK);
    }

    /**
     * To edit a rating
     * @param rating to edit
     * @return edited rating
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        if (UtilNumber.isNullOrZero(rating.getRatingId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!ratingService.exist(rating.getRatingId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ratingService.update(rating), HttpStatus.OK);
    }

    /**
     * To remove a rating
     * @param id of the rating to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !ratingService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ratingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * To create a rating stamp relation
     * @param rating Id and stamp Id     
     * @return rating created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(value = "/stamp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRatingStamp(@RequestBody ModelMap modelMap, UriComponentsBuilder ucBuilder) {
    	
	
    	Rating rating = ratingService.findOne(Integer.valueOf(modelMap.get("ratingId").toString()));
    	Stamp stamp = stampService.findOne(Integer.valueOf(modelMap.get("stampId").toString()));
    	if (rating == null || stamp == null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		try {
			stamp.getRating().add(rating);
			if(stampService.create(stamp) != null)
				return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    }

}