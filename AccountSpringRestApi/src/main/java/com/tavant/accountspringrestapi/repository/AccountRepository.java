package com.tavant.accountspringrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavant.accountspringrestapi.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
