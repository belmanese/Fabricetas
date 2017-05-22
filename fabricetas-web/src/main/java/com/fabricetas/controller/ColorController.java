package com.fabricetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabricetas.domain.Color;
import com.fabricetas.domain.dto.ColorDto;
import com.fabricetas.service.ColorService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Color
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    /**
     * To create a color
     * @param color for create
     * @return color created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Color> create(@RequestBody Color color, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(color.getColorId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(colorService.create(color), HttpStatus.CREATED);
    }

    /**
     * Read all colors
     * @return color list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Color>> findAll() {
        List<Color> colors = colorService.findAll();
        if (colors.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    /**
     * Read a color by id
     * @param id of the color to find
     * @return found color
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ColorDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        ColorDto userDto = colorService.findOneDto(id, fetch);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * To edit a color
     * @param color to edit
     * @return edited color
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Color> updateColor(@RequestBody Color color) {
        if (UtilNumber.isNullOrZero(color.getColorId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!colorService.exist(color.getColorId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(colorService.update(color), HttpStatus.OK);
    }

    /**
     * To remove a color
     * @param id of the color to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !colorService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        colorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}