package com.kharitonov.bankAppTest.repository;


import com.kharitonov.bankAppTest.entity.LoanOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanOfferRepository extends JpaRepository<LoanOffer, Long> {
}
