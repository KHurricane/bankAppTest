package com.kharitonov.bankAppTest.service;

import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public Credit getCreditById(Long id) {
        return creditRepository.findById(id).orElse(null);
    }

    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}
