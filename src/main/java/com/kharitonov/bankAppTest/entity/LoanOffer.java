package com.kharitonov.bankAppTest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loan_offers")
@Access(AccessType.PROPERTY)
public class LoanOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @NotNull
    private BigDecimal loanAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "loanOffer")
    private List<PaymentSchedule> paymentSchedules = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    @Entity
    @Table(name = "payment_shedules")
    @Access(AccessType.PROPERTY)
    public static class PaymentSchedule {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Date paymentDate;
        private BigDecimal paymentAmount;
        private BigDecimal principalAmount;
        private BigDecimal interestAmount;

        @ManyToOne
        @JoinColumn(name = "loan_offer_id")
        private LoanOffer loanOffer;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
        }

        public BigDecimal getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(BigDecimal paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public BigDecimal getPrincipalAmount() {
            return principalAmount;
        }

        public void setPrincipalAmount(BigDecimal principalAmount) {
            this.principalAmount = principalAmount;
        }

        public BigDecimal getInterestAmount() {
            return interestAmount;
        }

        public void setInterestAmount(BigDecimal interestAmount) {
            this.interestAmount = interestAmount;
        }

        public LoanOffer getCreditOffer() {
            return loanOffer;
        }

        public void setLoanOffer(LoanOffer loanOffer) {
            this.loanOffer = loanOffer;
        }
    }
}
