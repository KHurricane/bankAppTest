package com.kharitonov.bankAppTest.service;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;

import com.kharitonov.bankAppTest.repository.impl.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    public List<Credit> getAllCredits() {
        return creditRepository.getAllCredits();
    }

    public Credit getCreditById(Long id) {
        return creditRepository.getById(id);
    }

    public void saveCredit(Credit credit) {
        creditRepository.createById(credit);
    }

    public void updateClient(Credit credit){
        creditRepository.updateById(credit);
    }


    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}
