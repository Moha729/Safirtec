package com.mo.safir.home.c;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.month.m.Month;
import com.mo.safir.midlevelModel.income.income.c.IncomeController;
import com.mo.safir.midlevelModel.income.income.m.Income;
import com.mo.safir.midlevelModel.spending.spending.c.SpendingController;
import com.mo.safir.midlevelModel.spending.spending.m.Spending;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@AllArgsConstructor
@Controller
public class Logic2Controller {

    private final IncomeController incomeController;

    private final SpendingController spendingController;

    private final Logic1Controller logic1Controller;

    public void setLogic2Settings(Long dayId,Long monthId, Model model){

        Month month = logic1Controller.getMonthById(monthId);
        Day day = logic1Controller.getSpecificDay(month, dayId);

        List<Spending> spendings = spendingController.service.findAllById(dayId);
        model.addAttribute("day", day);
        model.addAttribute("spendings", spendings);

        Income income = incomeController.service.findByDayId(dayId);
        model.addAttribute("income", income);

        int totalSpending = 0;
        for (int i = 0; i < spendings.size(); i++) {
            totalSpending += spendings.get(i).getAmount();
        }
        model.addAttribute("totalSpending", totalSpending);

        int difference = income.getAmount() - totalSpending;
        model.addAttribute("difference", difference);
    }
}
