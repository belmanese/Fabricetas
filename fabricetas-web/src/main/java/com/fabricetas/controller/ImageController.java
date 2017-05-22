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

import com.fabricetas.domain.PersistentFile;
import com.fabricetas.domain.dto.PersistentFileDto;
import com.fabricetas.service.PersistentFileService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a PersistentFile
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private PersistentFileService persistentFileService;

    /**
     * To create a persistentFile
     * @param persistentFile for create
     * @return persistentFile created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersistentFile> create(@RequestBody PersistentFile persistentFile, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(persistentFile.getFileId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(persistentFileService.create(persistentFile), HttpStatus.CREATED);
    }

    /**
     * Read all persistentFiles
     * @return persistentFile list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersistentFile>> findAll() {
        List<PersistentFile> persistentFiles = persistentFileService.findAll();
        if (persistentFiles.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(persistentFiles, HttpStatus.OK);
    }

    /**
     * Read a persistentFile by id
     * @param id of the persistentFile to find
     * @return found persistentFile
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersistentFileDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        PersistentFileDto userDto = persistentFileService.findOneDto(id, fetch);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * To edit a persistentFile
     * @param persistentFile to edit
     * @return edited persistentFile
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersistentFile> updatePersistentFile(@RequestBody PersistentFile persistentFile) {
        if (UtilNumber.isNullOrZero(persistentFile.getFileId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!persistentFileService.exist(persistentFile.getFileId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(persistentFileService.update(persistentFile), HttpStatus.OK);
    }

    /**
     * To remove a persistentFile
     * @param id of the persistentFile to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !persistentFileService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        persistentFileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}