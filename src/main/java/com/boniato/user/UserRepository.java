package com.boniato.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Lee on 2017. 5. 5..
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByGithub(String github);
}
