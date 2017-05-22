package com.fabricetas.repos;

import com.fabricetas.domain.Text;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for a Text type.
 * Created on 11/04/2017.
 * @author belman
 * @see CrudRepository
 */
public interface TextRepository extends CrudRepository<Text, Integer> {}