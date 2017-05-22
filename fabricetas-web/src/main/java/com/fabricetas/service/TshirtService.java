package com.fabricetas.service;

import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.dto.TshirtDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for tshirt on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("tshirtService")
public interface TshirtService {

	/**
	 * To create a tshirt
	 * @param tshirt for create
	 * @return tshirt created
	 */
	Tshirt create(Tshirt tshirt);

	/**
	 * To remove a tshirt
	 * @param id of the tshirt to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a tshirt exist
	 * @param id of the tshirt to check
	 * @return if tshirt exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a tshirt by id
	 * @param id of the tshirt to find
	 * @return found tshirt
	 */
	Tshirt findOne(Integer id);

	/**
	 * Read a TshirtDto by id
	 * @param id of the tshirt to find
	 * @param fetch String of entities for fetch
	 * @return found tshirt
	 */
	TshirtDto findOneDto(Integer id, String fetch);

	/**
	 * Read all tshirts
	 * @return tshirt list
	 */
	List<Tshirt> findAll();

	/**
	 * To edit a tshirt
	 * @param tshirt to edit
	 * @return edited tshirt
	 */
	Tshirt update(Tshirt tshirt);

}