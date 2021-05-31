package com.faceit.cv_microservices.user_service.repository;

import com.faceit.cv_microservices.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);

    boolean existsUserByUserName(String userName);
}
