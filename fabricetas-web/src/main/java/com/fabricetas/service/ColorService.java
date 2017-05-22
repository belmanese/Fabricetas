package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.ColorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Color;

/**
 * Class used as a service for color on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("colorService")
public interface ColorService {

	/**
	 * To create a color
	 * @param color for create
	 * @return color created
	 */
	Color create(Color color);

	/**
	 * To remove a color
	 * @param id of the color to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a color exist
	 * @param id of the color to check
	 * @return if color exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a color by id
	 * @param id of the color to find
	 * @return found color
	 */
	Color findOne(Integer id);
	/**
	 * Read a ColorDto by id
	 * @param id of the user to find
	 * @param fetch String of entities for fetch
	 * @return found user
	 */
	ColorDto findOneDto(Integer id, String fetch);

	/**
	 * Read all colors
	 * @return color list
	 */
	List<Color> findAll();

	/**
	 * To edit a color
	 * @param color to edit
	 * @return edited color
	 */
	Color update(Color color);

}