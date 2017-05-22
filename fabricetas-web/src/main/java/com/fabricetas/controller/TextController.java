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

import com.fabricetas.domain.Text;
import com.fabricetas.domain.dto.TextDto;
import com.fabricetas.service.TextService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Text
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextService textService;

    /**
     * To create a text
     * @param text for create
     * @return text created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Text> create(@RequestBody Text text, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(text.getTextId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(textService.create(text), HttpStatus.CREATED);
    }

    /**
     * Read all texts
     * @return text list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Text>> findAll() {
        List<Text> texts = textService.findAll();
        if (texts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(texts, HttpStatus.OK);
    }

    /**
     * Read a text by id
     * @param id of the text to find
     * @return found text
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TextDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        TextDto textDto = textService.findOneDto(id, fetch);
        if (textDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(textDto, HttpStatus.OK);
    }

    /**
     * To edit a text
     * @param text to edit
     * @return edited text
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Text> updateText(@RequestBody Text text) {
        if (UtilNumber.isNullOrZero(text.getTextId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!textService.exist(text.getTextId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(textService.update(text), HttpStatus.OK);
    }

    /**
     * To remove a text
     * @param id of the text to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !textService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        textService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}