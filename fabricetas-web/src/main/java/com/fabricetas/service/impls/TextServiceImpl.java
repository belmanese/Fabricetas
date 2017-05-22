package com.fabricetas.service.impls;

import com.fabricetas.domain.Text;
import com.fabricetas.domain.dto.TextDto;
import com.fabricetas.repos.TextRepository;
import com.fabricetas.service.TextService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for text on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("textService")
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private FetchService<Text, TextDto> fetchService;

    /**
     * To create a text
     * @param text for create
     * @return text created
     */
    public Text create(Text text) {
        return saveOrUpdate(text);
    }

    /**
     * To remove a text
     * @param id of the text to remove
     */
    public void delete(Integer id){
        textRepository.delete(id);
    }

    /**
     * Check if a text exist
     * @param id of the text to check
     * @return if text exist
     */
    public Boolean exist(Integer id){
        return textRepository.exists(id);
    }

    /**
     * Read a text by id
      * @param id of the text to find
     * @return found text
     */
    public Text findOne(Integer id){
        return textRepository.findOne(id);
    }

    /**
     * Read a TextDto by id
     * @param id of the text to find
     * @param fetch String of entities for fetch
     * @return found text
     */
    public TextDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all texts
     * @return text list
     */
    public List<Text> findAll(){
        return Lists.newArrayList(textRepository.findAll());
    }

    /**
     * To edit a text
     * @param text to edit
     * @return edited text
     */
    public Text update(Text text) {
        return textRepository.save(text);
    }

    /**
     * Private method for create or update a text
     * @param text to create or to update
     * @return text created or updated
     */
    private Text saveOrUpdate(Text text){
        return textRepository.save(text);
    }

}