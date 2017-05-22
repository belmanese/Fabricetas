package com.fabricetas.service.impls;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.dto.StampDto;
import com.fabricetas.domain.dto.view.StampForHomeDto;
import com.fabricetas.repos.StampRepository;
import com.fabricetas.service.StampService;
import com.fabricetas.util.CallService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

/**
 * Class used as a service for stamp on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("stampService")
public class StampServiceImpl implements StampService {

    @Autowired
    private StampRepository stampRepository;

    @Autowired
    private FetchService<Stamp, StampDto> fetchService;
    
    @Autowired
    private CallService<StampForHomeDto> callService;

    /**
     * To create a stamp
     * @param stamp for create
     * @return stamp created
     */
    public Stamp create(Stamp stamp) {
        return saveOrUpdate(stamp);
    }

    /**
     * To remove a stamp
     * @param id of the stamp to remove
     */
    public void delete(Integer id){
        stampRepository.delete(id);
    }

    /**
     * Check if a stamp exist
     * @param id of the stamp to check
     * @return if stamp exist
     */
    public Boolean exist(Integer id){
        return stampRepository.exists(id);
    }

    /**
     * Read all stamps
     * @return stamp list
     */
    public List<Stamp> findAll(){
        return Lists.newArrayList(stampRepository.findAll());
    }

	/**
	 * Read all stamps 
	 * @return stamp list by home
	 */
	public Collection<StampForHomeDto> findAllByHome(Integer userId, Integer themeId) {
		if (userId == null)
			userId = 0;
		if (themeId == null)
			themeId = 0;
		return callService.callProcedure(
                new StampForHomeDto(),
                "STAMPS_FOR_HOME",
                Lists.newArrayList(userId, themeId),
                Lists.newArrayList(
            		"stampId","name","description","path","totalRating",
            		"price","themeId","themeName","artistId","artistName")
        		);
	}

    /**
     * Read a stamp by id
      * @param id of the stamp to find
     * @return found stamp
     */
    public Stamp findOne(Integer id){
        return stampRepository.findOne(id);
    }

    /**
     * Read a StampDto by id
     * @param id of the stamp to find
     * @param fetch String of entities for fetch
     * @return found stamp
     */
    public StampDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * To edit a stamp
     * @param stamp to edit
     * @return edited stamp
     */
    public Stamp update(Stamp stamp) {
        return stampRepository.save(stamp);
    }

    /**
     * Private method for create or update a stamp
     * @param stamp to create or to update
     * @return stamp created or updated
     */
    private Stamp saveOrUpdate(Stamp stamp){
        return stampRepository.save(stamp);
    }

}