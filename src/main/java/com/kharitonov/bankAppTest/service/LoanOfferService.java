package com.kharitonov.bankAppTest.service;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.entity.LoanOffer;


import com.kharitonov.bankAppTest.repository.impl.ClientRepository;
import com.kharitonov.bankAppTest.repository.impl.CreditRepository;
import com.kharitonov.bankAppTest.repository.impl.LoanOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void createLoanOffer(Client client, Credit credit, BigDecimal loanAmount) {
        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setClient(client);
        loanOffer.setCredit(credit);
        loanOffer.setLoanAmount(loanAmount);
        BigDecimal totalInterest = calculatePaymentSchedule(loanOffer, loanAmount, credit.getInterestRate());
        loanOffer.setTotalInterest(totalInterest);

        loanOfferRepository.createById(loanOffer);
    }


    /**
     * Рассчитывает график платежей для кредитного предложения.
     *
     * @param loanOffer  Кредитное предложение, для которого нужно рассчитать график платежей.
     * @param loanAmount Сумма кредита.
     * @param annualRate Годовая процентная ставка.
     * @return Общая сумма процентов, уплаченных по кредиту.
     */
    private BigDecimal calculatePaymentSchedule(LoanOffer loanOffer, BigDecimal loanAmount, BigDecimal annualRate) {
        List<LoanOffer.PaymentSchedule> paymentSchedules = new ArrayList<>();
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
        BigDecimal totalInterest = BigDecimal.ZERO;
// Цикл для расчета ежемесячных платежей на 12 месяцев
        for (int i = 1; i <= 12; i++) {
            LoanOffer.PaymentSchedule schedule = new LoanOffer.PaymentSchedule();
            schedule.setPaymentDate(getNextMonthDate(i)); // Установка правильной даты для каждого месяца
            BigDecimal interestAmount = monthlyRate.multiply(loanAmount);
            BigDecimal principalAmount = loanAmount.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
            BigDecimal monthlyPayment = interestAmount.add(principalAmount);

            schedule.setPaymentAmount(monthlyPayment);
            schedule.setPrincipalAmount(principalAmount);
            schedule.setInterestAmount(interestAmount);
            schedule.setLoanOffer(loanOffer);

            paymentSchedules.add(schedule); // Добавление текущего графика в список
            totalInterest = totalInterest.add(interestAmount);
        }

        loanOffer.setPaymentSchedules(paymentSchedules);// Установка списка графиков платежей для кредитного предложения
        return totalInterest;
    }

    /**
     * Возвращает дату для следующего месяца на основе текущей даты.
     *
     * @param month Количество месяцев, на которое нужно сместить текущую дату.
     * @return Дата следующего месяца.
     */
    private Date getNextMonthDate(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public void updateLoanOffer(LoanOffer loanOffer) {
        loanOfferRepository.updateById(loanOffer);
    }

    public List<LoanOffer> getAllLoanOffers() {
        return loanOfferRepository.getAllOffers();
    }

    public LoanOffer getLoanOfferById(Long id) {
        return loanOfferRepository.getById(id);
    }

    public void deleteLoanOffer(Long id) {
        LoanOffer loanOffer = loanOfferRepository.getById(id);
        loanOfferRepository.deleteById(loanOffer);
    }

    public List<LoanOffer> getAllLoanOffersByClientId(Long clientId) {
        return loanOfferRepository.getAllOffersByClientId(clientId);
    }


}
