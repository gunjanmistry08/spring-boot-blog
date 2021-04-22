/**
 * 
 */
package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.User;

/**
 * @author gunjanmistry
 *
 */

@RepositoryRestResource(collectionResourceRel = "users", path = "users" )
public interface UserRepo extends JpaRepository<User, Long> {
	 User findByUserName(String userName);

}
