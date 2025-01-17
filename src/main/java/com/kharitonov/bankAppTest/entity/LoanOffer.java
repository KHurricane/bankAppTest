package com.kharitonov.bankAppTest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loan_offers")
@Access(AccessType.FIELD)
public class LoanOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @NotNull
    @Column(name = "loan_amount")
    private BigDecimal loanAmount;

    @NotNull
    @Column(name = "total_interest")
    private BigDecimal totalInterest;

    @JsonIgnore
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

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    @Entity
    @Table(name = "payment_schedules")
    @Access(AccessType.FIELD)
    public static class PaymentSchedule {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "payment_date")
        private Date paymentDate;
        @Column(name = "payment_amount")
        private BigDecimal paymentAmount;
        @Column(name = "principal_amount")
        private BigDecimal principalAmount;
        @Column(name = "interest_amount")
        private BigDecimal interestAmount;

        @ManyToOne
        @JoinColumn(name = "loan_offer_id")
        @JsonBackReference
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
