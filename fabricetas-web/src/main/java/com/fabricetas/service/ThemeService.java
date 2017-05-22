package com.fabricetas.service;

import com.fabricetas.domain.Theme;
import com.fabricetas.domain.dto.ThemeDto;
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
public interface ThemeService {

	/**
	 * To create a theme
	 * @param theme for create
	 * @return theme created
	 */
	Theme create(Theme theme);

	/**
	 * To remove a theme
	 * @param id of the theme to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a theme exist
	 * @param id of the theme to check
	 * @return if theme exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a theme by id
	 * @param id of the theme to find
	 * @return found theme
	 */
	Theme findOne(Integer id);

	/**
	 * Read a ThemeDto by id
	 * @param id of the theme to find
	 * @param fetch String of entities for fetch
	 * @return found theme
	 */
	ThemeDto findOneDto(Integer id, String fetch);

	/**
	 * Read all themes
	 * @return theme list
	 */
	List<Theme> findAll();

	/**
	 * To edit a theme
	 * @param theme to edit
	 * @return edited theme
	 */
	Theme update(Theme theme);

}