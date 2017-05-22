package com.fabricetas.service.impls;

import com.fabricetas.domain.PersistentFile;
import com.fabricetas.domain.dto.PersistentFileDto;
import com.fabricetas.repos.PersistentFileRepository;
import com.fabricetas.service.PersistentFileService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for persistentFile on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("persistentFileService")
public class PersistentFileServiceImpl implements PersistentFileService {

    @Autowired
    private PersistentFileRepository persistentFileRepository;

    @Autowired
    private FetchService<PersistentFile, PersistentFileDto> fetchService;

    /**
     * To create a persistentFile
     * @param persistentFile for create
     * @return persistentFile created
     */
    public PersistentFile create(PersistentFile persistentFile) {
        return saveOrUpdate(persistentFile);
    }

    /**
     * To remove a persistentFile
     * @param id of the persistentFile to remove
     */
    public void delete(Integer id){
        persistentFileRepository.delete(id);
    }

    /**
     * Check if a persistentFile exist
     * @param id of the persistentFile to check
     * @return if persistentFile exist
     */
    public Boolean exist(Integer id){
        return persistentFileRepository.exists(id);
    }

    /**
     * Read a persistentFile by id
      * @param id of the persistentFile to find
     * @return found persistentFile
     */
    public PersistentFile findOne(Integer id){
        return persistentFileRepository.findOne(id);
    }

    /**
     * Read a PersistentFileDto by id
     * @param id of the user to find
     * @param fetch String of entities for fetch
     * @return found file
     */
    public PersistentFileDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all persistentFiles
     * @return persistentFile list
     */
    public List<PersistentFile> findAll(){
        return Lists.newArrayList(persistentFileRepository.findAll());
    }

    /**
     * To edit a persistentFile
     * @param persistentFile to edit
     * @return edited persistentFile
     */
    public PersistentFile update(PersistentFile persistentFile) {
        return persistentFileRepository.save(persistentFile);
    }

    /**
     * Private method for create or update a persistentFile
     * @param persistentFile to create or to update
     * @return persistentFile created or updated
     */
    private PersistentFile saveOrUpdate(PersistentFile persistentFile){
        return persistentFileRepository.save(persistentFile);
    }

}