package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.PersistentFileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.PersistentFile;

/**
 * Class used as a service for persistentFile on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("persistentFileService")
public interface PersistentFileService {

	/**
	 * To create a persistentFile
	 * @param persistentFile for create
	 * @return persistentFile created
	 */
	PersistentFile create(PersistentFile persistentFile);

	/**
	 * To remove a persistentFile
	 * @param id of the persistentFile to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a persistentFile exist
	 * @param id of the persistentFile to check
	 * @return if persistentFile exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a persistentFile by id
	 * @param id of the persistentFile to find
	 * @return found persistentFile
	 */
	PersistentFile findOne(Integer id);

	/**
	 * Read a PersistentFileDto by id
	 * @param id of the user to find
	 * @param fetch String of entities for fetch
	 * @return found file
	 */
	PersistentFileDto findOneDto(Integer id, String fetch);

	/**
	 * Read all persistentFiles
	 * @return persistentFile list
	 */
	List<PersistentFile> findAll();

	/**
	 * To edit a persistentFile
	 * @param persistentFile to edit
	 * @return edited persistentFile
	 */
	PersistentFile update(PersistentFile persistentFile);

}