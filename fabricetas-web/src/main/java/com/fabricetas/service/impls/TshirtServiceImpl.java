package com.fabricetas.service.impls;

import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.dto.TshirtDto;
import com.fabricetas.repos.TshirtRepository;
import com.fabricetas.service.TshirtService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for tshirt on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("tshirtService")
public class TshirtServiceImpl implements TshirtService {

    @Autowired
    private TshirtRepository tshirtRepository;

    @Autowired
    private FetchService<Tshirt, TshirtDto> fetchService;

    /**
     * To create a tshirt
     * @param tshirt for create
     * @return tshirt created
     */
    public Tshirt create(Tshirt tshirt) {
        return saveOrUpdate(tshirt);
    }

    /**
     * To remove a tshirt
     * @param id of the tshirt to remove
     */
    public void delete(Integer id){
        tshirtRepository.delete(id);
    }

    /**
     * Check if a tshirt exist
     * @param id of the tshirt to check
     * @return if tshirt exist
     */
    public Boolean exist(Integer id){
        return tshirtRepository.exists(id);
    }

    /**
     * Read a tshirt by id
      * @param id of the tshirt to find
     * @return found tshirt
     */
    public Tshirt findOne(Integer id){
        return tshirtRepository.findOne(id);
    }

    /**
     * Read a TshirtDto by id
     * @param id of the tshirt to find
     * @param fetch String of entities for fetch
     * @return found tshirt
     */
    public TshirtDto findOneDto(Integer id, String fetch){
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all tshirts
     * @return tshirt list
     */
    public List<Tshirt> findAll(){
        return Lists.newArrayList(tshirtRepository.findAll());
    }

    /**
     * To edit a tshirt
     * @param tshirt to edit
     * @return edited tshirt
     */
    public Tshirt update(Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    /**
     * Private method for create or update a tshirt
     * @param tshirt to create or to update
     * @return tshirt created or updated
     */
    private Tshirt saveOrUpdate(Tshirt tshirt){
        return tshirtRepository.save(tshirt);
    }

}