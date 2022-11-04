package com.mo.safir.midlevelModel.income.income.r;

import com.mo.safir.midlevelModel.income.income.m.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income, Long> {
}
