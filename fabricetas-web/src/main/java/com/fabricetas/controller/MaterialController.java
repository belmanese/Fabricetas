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

import com.fabricetas.domain.Material;
import com.fabricetas.domain.dto.MaterialDto;
import com.fabricetas.service.MaterialService;
import com.fabricetas.util.UtilNumber;

/**
 * Controller that responds to http requests related to a Material
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    /**
     * To create a material
     * @param material for create
     * @return material created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Material> create(@RequestBody Material material, UriComponentsBuilder ucBuilder) {
        if (!UtilNumber.isNullOrZero(material.getMaterialId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(materialService.create(material), HttpStatus.CREATED);
    }

    /**
     * Read all materials
     * @return material list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Material>> findAll() {
        List<Material> materials = materialService.findAll();
        if (materials.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    /**
     * Read a material by id
     * @param id of the material to find
     * @return found material
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterialDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        MaterialDto materialDto = materialService.findOneDto(id, fetch);
        if (materialDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(materialDto, HttpStatus.OK);
    }

    /**
     * To edit a material
     * @param material to edit
     * @return edited material
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Material> updateMaterial(@RequestBody Material material) {
        if (UtilNumber.isNullOrZero(material.getMaterialId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!materialService.exist(material.getMaterialId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(materialService.update(material), HttpStatus.OK);
    }

    /**
     * To remove a material
     * @param id of the material to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !materialService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}