package com.mibesoft.demo.jpaDataTablesSpringMVC.repository;

import com.mibesoft.demo.jpaDataTablesSpringMVC.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;


/**
 * User repository extending {@link DataTablesRepository}
 * 
 * @author Damien Arrachequesne
 */
public interface UserRepository extends DataTablesRepository<User, Integer> {

}
