package com.kharitonov.bankAppTest.repository;

import com.kharitonov.bankAppTest.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
