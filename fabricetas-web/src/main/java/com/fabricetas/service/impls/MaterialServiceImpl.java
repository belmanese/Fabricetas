package com.fabricetas.service.impls;

import com.fabricetas.domain.Material;
import com.fabricetas.domain.dto.MaterialDto;
import com.fabricetas.repos.MaterialRepository;
import com.fabricetas.service.MaterialService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for material on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private FetchService<Material, MaterialDto> fetchService;

    /**
     * To create a material
     * @param material for create
     * @return material created
     */
    public Material create(Material material) {
        return saveOrUpdate(material);
    }

    /**
     * To remove a material
     * @param id of the material to remove
     */
    public void delete(Integer id){
        materialRepository.delete(id);
    }

    /**
     * Check if a material exist
     * @param id of the material to check
     * @return if material exist
     */
    public Boolean exist(Integer id){
        return materialRepository.exists(id);
    }

    /**
     * Read a material by id
      * @param id of the material to find
     * @return found material
     */
    public Material findOne(Integer id){
        return materialRepository.findOne(id);
    }

    /**
     * Read a MaterialDto by id
     * @param id of the material to find
     * @param fetch String of entities for fetch
     * @return found material
     */
    public MaterialDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all materials
     * @return material list
     */
    public List<Material> findAll(){
        return Lists.newArrayList(materialRepository.findAll());
    }

    /**
     * To edit a material
     * @param material to edit
     * @return edited material
     */
    public Material update(Material material) {
        return materialRepository.save(material);
    }

    /**
     * Private method for create or update a material
     * @param material to create or to update
     * @return material created or updated
     */
    private Material saveOrUpdate(Material material){
        return materialRepository.save(material);
    }

}