package com.fabricetas.service.impls;

import com.fabricetas.domain.Rating;
import com.fabricetas.domain.dto.RatingDto;
import com.fabricetas.repos.RatingRepository;
import com.fabricetas.service.RatingService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for rating on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("ratingService")
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private FetchService<Rating, RatingDto> fetchService;

    /**
     * To create a rating
     * @param rating for create
     * @return rating created
     */
    public Rating create(Rating rating) {
        return saveOrUpdate(rating);
    }

    /**
     * To remove a rating
     * @param id of the rating to remove
     */
    public void delete(Integer id){
        ratingRepository.delete(id);
    }

    /**
     * Check if a rating exist
     * @param id of the rating to check
     * @return if rating exist
     */
    public Boolean exist(Integer id){
        return ratingRepository.exists(id);
    }

    /**
     * Read a rating by id
      * @param id of the rating to find
     * @return found rating
     */
    public Rating findOne(Integer id){
        return ratingRepository.findOne(id);
    }

    /**
     * Read a RatingDto by id
     * @param id of the rating to find
     * @param fetch String of entities for fetch
     * @return found rating
     */
    public RatingDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all ratings
     * @return rating list
     */
    public List<Rating> findAll(){
        return Lists.newArrayList(ratingRepository.findAll());
    }

    /**
     * To edit a rating
     * @param rating to edit
     * @return edited rating
     */
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Private method for create or update a rating
     * @param rating to create or to update
     * @return rating created or updated
     */
    private Rating saveOrUpdate(Rating rating){
        return ratingRepository.save(rating);
    }

}