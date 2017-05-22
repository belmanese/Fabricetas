package com.fabricetas.controller;

import com.fabricetas.domain.Theme;
import com.fabricetas.domain.dto.ThemeDto;
import com.fabricetas.service.ThemeService;
import com.fabricetas.util.UtilNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Controller that responds to http requests related to a Theme
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    /**
     * To create a theme
     * @param theme for create
     * @return theme created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Theme> create(@RequestBody Theme theme, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(theme.getThemeId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(themeService.create(theme), HttpStatus.CREATED);
    }

    /**
     * Read all themes
     * @return theme list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Theme>> findAll() {
        List<Theme> themes = themeService.findAll();
        if (themes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(themes, HttpStatus.OK);
    }

    /**
     * Read a theme by id
     * @param id of the theme to find
     * @return found theme
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThemeDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        ThemeDto userDto = themeService.findOneDto(id, fetch);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * To edit a theme
     * @param theme to edit
     * @return edited theme
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Theme> updateTheme(@RequestBody Theme theme) {
        if (UtilNumber.isNullOrZero(theme.getThemeId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!themeService.exist(theme.getThemeId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(themeService.update(theme), HttpStatus.OK);
    }

    /**
     * To remove a theme
     * @param id of the theme to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !themeService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        themeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}