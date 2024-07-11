package com.kharitonov.bankAppTest.repository;

import com.kharitonov.bankAppTest.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BankRepository extends JpaRepository<Bank, Long>{
}
