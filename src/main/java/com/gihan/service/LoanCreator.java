package com.gihan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gihan.model.*;
import com.gihan.repository.LoanRepository;

@Component
public class LoanCreator implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public long createLoan(CandidateLoanDTO dto) {
        Loan loan = new Loan(dto.getDescription(),
                dto.getPrincipalAmount(), dto.getBalance(), dto.getInterestRate(),
                dto.getTerm(), dto.getStartDate());
        return loanRepository.save(loan).getId();
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> exp = loanRepository.findAll();
        final List<LoanDTO> loans = new ArrayList<>();
        exp.stream()
                .forEach(loan -> {
                    LoanDTO dto = new LoanDTO(
                            loan.getId(),
                            loan.getDescription(),
                            loan.getPrincipalAmount(),
                            loan.getCurrentBalance(),
                            loan.getInterestRate(),
                            loan.getTerm(),
                            loan.getStartDate());
                    loans.add(dto);
                });
        return loans;
    }

    @Override
    public void delete(int loanId) {
        loanRepository.delete(loanId);
    }

    @Override
    public void edit(LoanDTO modifiedLoan) {
        Loan loan = loanRepository.findById(modifiedLoan.getLoanId());
        loan.setDescription(modifiedLoan.getDescription());
        loan.setPrincipalAmount(modifiedLoan.getPrincipalAmount());
        loan.setCurrentBalance(modifiedLoan.getCurrentBalance());
        loan.setInterestRate(modifiedLoan.getInterestRate());
        loan.setTerm(modifiedLoan.getTerm());
        loanRepository.save(loan);
    }
}
