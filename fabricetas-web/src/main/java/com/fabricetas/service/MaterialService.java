package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.MaterialDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Material;

/**
 * Class used as a service for material on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("materialService")
public interface MaterialService {

	/**
	 * To create a material
	 * @param material for create
	 * @return material created
	 */
	Material create(Material material);

	/**
	 * To remove a material
	 * @param id of the material to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a material exist
	 * @param id of the material to check
	 * @return if material exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a material by id
	 * @param id of the material to find
	 * @return found material
	 */
	Material findOne(Integer id);

	/**
	 * Read a MaterialDto by id
	 * @param id of the material to find
	 * @param fetch String of entities for fetch
	 * @return found material
	 */
	MaterialDto findOneDto(Integer id, String fetch);

	/**
	 * Read all materials
	 * @return material list
	 */
	List<Material> findAll();

	/**
	 * To edit a material
	 * @param material to edit
	 * @return edited material
	 */
	Material update(Material material);

}