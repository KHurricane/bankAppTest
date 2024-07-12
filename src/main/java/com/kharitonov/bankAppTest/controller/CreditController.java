package com.kharitonov.bankAppTest.controller;

import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {
    @Autowired
    private CreditService creditService;

    @GetMapping
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public Credit getCreditById(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PostMapping
    public void createCredit(@RequestBody Credit credit) {
        creditService.saveCredit(credit);
    }

    @PutMapping("/{id}")
    public void updateCredit(@PathVariable Long id, @RequestBody Credit credit) {
        credit.setId(id);
        creditService.saveCredit(credit);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }
}
