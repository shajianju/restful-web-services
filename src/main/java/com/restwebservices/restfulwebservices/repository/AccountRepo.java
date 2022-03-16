package com.restwebservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restwebservices.restfulwebservices.models.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
