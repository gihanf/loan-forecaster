package com.gihan.testHelper;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.Frequency;

public class CandidateExpenseDtoBuilder {
    private String description;
    private BigDecimal amount;
    private Frequency frequency;
    private LocalDate firstPaymentDate;

    public CandidateExpenseDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CandidateExpenseDtoBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public CandidateExpenseDtoBuilder withFrequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public CandidateExpenseDtoBuilder withFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
        return this;
    }

    public CandidateExpenseDTO build() {
        return new CandidateExpenseDTO(
                this.description,
                this.amount,
                this.frequency,
                this.firstPaymentDate
        );
    }
}
