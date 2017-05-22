package com.fabricetas.service.impls;

import com.fabricetas.domain.Theme;
import com.fabricetas.domain.dto.ThemeDto;
import com.fabricetas.repos.ThemeRepository;
import com.fabricetas.service.ThemeService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for theme on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("themeService")
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private FetchService<Theme, ThemeDto> fetchService;

    /**
     * To create a theme
     * @param theme for create
     * @return theme created
     */
    public Theme create(Theme theme) {
        return saveOrUpdate(theme);
    }

    /**
     * To remove a theme
     * @param id of the theme to remove
     */
    public void delete(Integer id){
        themeRepository.delete(id);
    }

    /**
     * Check if a theme exist
     * @param id of the theme to check
     * @return if theme exist
     */
    public Boolean exist(Integer id){
        return themeRepository.exists(id);
    }

    /**
     * Read a theme by id
      * @param id of the theme to find
     * @return found theme
     */
    public Theme findOne(Integer id){
        return themeRepository.findOne(id);
    }

    /**
     * Read a ThemeDto by id
     * @param id of the theme to find
     * @param fetch String of entities for fetch
     * @return found theme
     */
    public ThemeDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all themes
     * @return theme list
     */
    public List<Theme> findAll(){
        return Lists.newArrayList(themeRepository.findAll());
    }

    /**
     * To edit a theme
     * @param theme to edit
     * @return edited theme
     */
    public Theme update(Theme theme) {
        return themeRepository.save(theme);
    }

    /**
     * Private method for create or update a theme
     * @param theme to create or to update
     * @return theme created or updated
     */
    private Theme saveOrUpdate(Theme theme){
        return themeRepository.save(theme);
    }

}