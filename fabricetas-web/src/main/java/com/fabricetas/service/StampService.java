package com.fabricetas.service;

import java.util.Collection;
import java.util.List;

import com.fabricetas.domain.dto.StampDto;
import com.fabricetas.domain.dto.view.StampForHomeDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Stamp;

/**
 * Class used as a service for stamp on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("stampService")
public interface StampService {

	/**
	 * To create a stamp
	 * @param stamp for create
	 * @return stamp created
	 */
	Stamp create(Stamp stamp);

	/**
	 * To remove a stamp
	 * @param id of the stamp to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a stamp exist
	 * @param id of the stamp to check
	 * @return if stamp exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read all stamps
	 * @return stamp list
	 */
	List<Stamp> findAll();

	/**
	 * Read all stamps 
	 * @return stamp list by home
	 */
	Collection<StampForHomeDto> findAllByHome(Integer userId, Integer themeId);

	/**
	 * Read a stamp by id
	 * @param id of the stamp to find
	 * @return found stamp
	 */
	Stamp findOne(Integer id);

	/**
	 * Read a StampDto by id
	 * @param id of the stamp to find
	 * @param fetch String of entities for fetch
	 * @return found stamp
	 */
	StampDto findOneDto(Integer id, String fetch);

	/**
	 * To edit a stamp
	 * @param stamp to edit
	 * @return edited stamp
	 */
	Stamp update(Stamp stamp);

}