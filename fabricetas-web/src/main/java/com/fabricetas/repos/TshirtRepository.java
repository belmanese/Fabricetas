package com.fabricetas.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fabricetas.domain.Tshirt;

import java.util.List;


/**
 * Interface for generic CRUD operations on a repository for a Tshirt type.
 * Created on 13/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface TshirtRepository extends CrudRepository<Tshirt, Integer> {
	
	@Query("SELECT t FROM Tshirt t where t.price < :priceTshirt") 
	List<Tshirt> findByLowerPrice(@Param("priceTshirt") String priceTshirt);	
	
}