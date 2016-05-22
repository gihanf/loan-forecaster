package com.gihan.repository;

import com.gihan.model.PaymentSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PaymentScheduleRepository extends CrudRepository<PaymentSchedule, Long> {

}
