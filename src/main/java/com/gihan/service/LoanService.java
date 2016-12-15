package com.gihan.service;

import java.util.List;

import com.gihan.model.CandidateLoanDTO;
import com.gihan.model.LoanDTO;

public interface LoanService {

    long createLoan(CandidateLoanDTO dto);

    List<LoanDTO> getAllLoans();

    void delete(int loanId);

    void edit(LoanDTO loanDTO);

}
