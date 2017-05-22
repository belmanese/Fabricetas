package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.RatingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Rating;

/**
 * Class used as a service for rating on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("ratingService")
public interface RatingService {

	/**
	 * To create a rating
	 * @param rating for create
	 * @return rating created
	 */
	Rating create(Rating rating);

	/**
	 * To remove a rating
	 * @param id of the rating to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a rating exist
	 * @param id of the rating to check
	 * @return if rating exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a rating by id
	 * @param id of the rating to find
	 * @return found rating
	 */
	Rating findOne(Integer id);

	/**
	 * Read a RatingDto by id
	 * @param id of the rating to find
	 * @param fetch String of entities for fetch
	 * @return found rating
	 */
	RatingDto findOneDto(Integer id, String fetch);

	/**
	 * Read all ratings
	 * @return rating list
	 */
	List<Rating> findAll();

	/**
	 * To edit a rating
	 * @param rating to edit
	 * @return edited rating
	 */
	Rating update(Rating rating);

}