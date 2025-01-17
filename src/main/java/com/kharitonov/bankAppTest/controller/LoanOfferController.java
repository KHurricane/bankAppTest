package com.kharitonov.bankAppTest.controller;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.entity.LoanOffer;
import com.kharitonov.bankAppTest.service.ClientService;
import com.kharitonov.bankAppTest.service.CreditService;
import com.kharitonov.bankAppTest.service.LoanOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/loan_offers")
public class LoanOfferController {
    @Autowired
    private LoanOfferService loanOfferService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditService creditService;

    @PostMapping
    public void createLoanOffer(@RequestParam Long clientId, @RequestParam Long creditId, @RequestParam BigDecimal loanAmount) {
        Client client = clientService.getClientById(clientId);
        Credit credit = creditService.getCreditById(creditId);

        loanOfferService.createLoanOffer(client, credit, loanAmount);
    }
    @PutMapping("/{id}")
    public void updateLoanOffer(@PathVariable Long id, @RequestBody LoanOffer loanOffer){
        loanOffer.setId(id);
        loanOfferService.updateLoanOffer(loanOffer);
    }

    @GetMapping
    public List<LoanOffer> getAllLoanOffers() {
        return loanOfferService.getAllLoanOffers();
    }

    @GetMapping("/{id}")
    public LoanOffer getLoanOfferById(@PathVariable Long id) {
        return loanOfferService.getLoanOfferById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLoanOffer(@PathVariable Long id) {
        loanOfferService.deleteLoanOffer(id);
    }
    @GetMapping("/client/{clientId}")
    public List<LoanOffer> getAllLoanOffersByClientId(@PathVariable Long clientId) {
        return loanOfferService.getAllLoanOffersByClientId(clientId);
    }

}
