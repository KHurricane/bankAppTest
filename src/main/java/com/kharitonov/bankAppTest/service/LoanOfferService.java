package com.kharitonov.bankAppTest.service;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.entity.LoanOffer;
import com.kharitonov.bankAppTest.repository.ClientRepository;
import com.kharitonov.bankAppTest.repository.LoanOfferRepository;
import com.kharitonov.bankAppTest.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanOfferService {
    @Autowired
    private LoanOfferRepository loanOfferRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    public LoanOffer createLoanOffer(Client client, Credit credit, BigDecimal loanAmount) {
        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setClient(client);
        loanOffer.setCredit(credit);
        loanOffer.setLoanAmount(loanAmount);

        List<LoanOffer.PaymentSchedule> paymentSchedules = calculatePaymentSchedule(loanAmount, credit.getInterestRate());
        for (LoanOffer.PaymentSchedule schedule : paymentSchedules) {
            schedule.setLoanOffer(loanOffer);
        }
        loanOffer.setPaymentSchedules(paymentSchedules);

        return loanOfferRepository.save(loanOffer);
    }

    private List<LoanOffer.PaymentSchedule> calculatePaymentSchedule(BigDecimal loanAmount, BigDecimal annualRate) {
        List<LoanOffer.PaymentSchedule> paymentSchedules = new ArrayList<>();
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = loanAmount.multiply(monthlyRate.add(BigDecimal.ONE));

        for (int i = 1; i <= 12; i++) {
            LoanOffer.PaymentSchedule schedule = new LoanOffer.PaymentSchedule();
            schedule.setPaymentDate(new Date());
            schedule.setPaymentAmount(monthlyPayment);
            schedule.setPrincipalAmount(monthlyPayment.subtract(monthlyRate.multiply(loanAmount)));
            schedule.setInterestAmount(monthlyRate.multiply(loanAmount));

            paymentSchedules.add(schedule);
        }

        return paymentSchedules;
    }

    public List<LoanOffer> getAllLoanOffers() {
        return loanOfferRepository.findAll();
    }

    public LoanOffer getLoanOfferById(Long id) {
        return loanOfferRepository.findById(id).orElse(null);
    }

    public void deleteLoanOffer(Long id) {
        loanOfferRepository.deleteById(id);
    }
}
