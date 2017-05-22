package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.TextDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Text;

/**
 * Class used as a service for text on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("textService")
public interface TextService {

	/**
	 * To create a text
	 * @param text for create
	 * @return text created
	 */
	Text create(Text text);

	/**
	 * To remove a text
	 * @param id of the text to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a text exist
	 * @param id of the text to check
	 * @return if text exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a text by id
	 * @param id of the text to find
	 * @return found text
	 */
	Text findOne(Integer id);

	/**
	 * Read a TextDto by id
	 * @param id of the text to find
	 * @param fetch String of entities for fetch
	 * @return found text
	 */
	TextDto findOneDto(Integer id, String fetch);

	/**
	 * Read all texts
	 * @return text list
	 */
	List<Text> findAll();

	/**
	 * To edit a text
	 * @param text to edit
	 * @return edited text
	 */
	Text update(Text text);

}