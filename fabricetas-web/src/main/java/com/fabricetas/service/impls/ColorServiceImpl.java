package com.fabricetas.service.impls;

import com.fabricetas.domain.Color;
import com.fabricetas.domain.dto.ColorDto;
import com.fabricetas.repos.ColorRepository;
import com.fabricetas.service.ColorService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for color on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("colorService")
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FetchService<Color, ColorDto> fetchService;

    /**
     * To create a color
     * @param color for create
     * @return color created
     */
    public Color create(Color color) {
        return saveOrUpdate(color);
    }

    /**
     * To remove a color
     * @param id of the color to remove
     */
    public void delete(Integer id){
        colorRepository.delete(id);
    }

    /**
     * Check if a color exist
     * @param id of the color to check
     * @return if color exist
     */
    public Boolean exist(Integer id){
        return colorRepository.exists(id);
    }

    /**
     * Read a color by id
      * @param id of the color to find
     * @return found color
     */
    public Color findOne(Integer id){
        return colorRepository.findOne(id);
    }
    /**
     * Read a ColorDto by id
     * @param id of the user to find
     * @param fetch String of entities for fetch
     * @return found user
     */
    public ColorDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all colors
     * @return color list
     */
    public List<Color> findAll(){
        return Lists.newArrayList(colorRepository.findAll());
    }

    /**
     * To edit a color
     * @param color to edit
     * @return edited color
     */
    public Color update(Color color) {
        return colorRepository.save(color);
    }

    /**
     * Private method for create or update a color
     * @param color to create or to update
     * @return color created or updated
     */
    private Color saveOrUpdate(Color color){
        return colorRepository.save(color);
    }

}