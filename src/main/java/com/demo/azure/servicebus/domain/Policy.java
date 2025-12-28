package com.demo.azure.servicebus.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Policy {

    private String policyNumber;
    private String policyType;
    private BigDecimal premiumAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Policy() {}

    public Policy(String policyNumber, String policyType,
                  BigDecimal premiumAmount,
                  LocalDate startDate,
                  LocalDate endDate) {
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyType() { return policyType; }
    public BigDecimal getPremiumAmount() { return premiumAmount; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
