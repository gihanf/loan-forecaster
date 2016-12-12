package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gihan.model.Loan;
import com.gihan.model.LoanDTO;
import com.gihan.repository.LoanRepository;

@Component
public class LoanCreator implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> exp = loanRepository.findAll();
        final List<LoanDTO> loans = new ArrayList<>();
        exp.stream()
//                .sorted((a, b) -> a.getPaymentSchedule().getFirstPaymentDate().isBefore(b.getPaymentSchedule().getFirstPaymentDate()) ? 1 : -1)
                .forEach(loan -> {
                    LoanDTO dto = new LoanDTO(
                            loan.getId(),
                            loan.getDescription(),
                            loan.getPrincipalAmount(),
                            loan.getCurrentBalance(),
                            loan.getInterestRate(),
                            loan.getTerm());
                    loans.add(dto);
                });
        return loans;
    }
}
