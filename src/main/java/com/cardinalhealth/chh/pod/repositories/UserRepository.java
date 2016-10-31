package com.cardinalhealth.chh.pod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardinalhealth.chh.pod.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByGuid(String guid);

}
