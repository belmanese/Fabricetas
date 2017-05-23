package com.fabricetas.repos;

import java.util.List;

import com.fabricetas.domain.Stamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interface for generic CRUD operations on a repository for a Stamp type.
 * Created on 23/04/2017.
 * @author belman
 * @see org.springframework.data.repository.CrudRepository
 */
public interface StampRepository extends CrudRepository<Stamp, Integer> { 
	
	@Query("SELECT s FROM Stamp s where s.price < :priceStamp") 
	List<Stamp> findByLowerPrice(@Param("priceStamp") String priceStamp);	
	
	@Query("SELECT s FROM Stamp s inner join s.user where s.price < :priceStamp and s.user.userId = :userId") 
	List<Stamp> findByLowerPriceAndUser(@Param("priceStamp") String priceStamp, @Param("userId") String userId);	
	
	@Query("SELECT s FROM Stamp s inner join s.user u inner join s.theme t where s.price < :priceStamp and u.userId = :userId and t.themeId = :themeId") 
	List<Stamp> findByLowerPriceAndUserAndTheme(@Param("priceStamp") String priceStamp, @Param("userId") String userId, @Param("themeId") String themeId);	
	
}