package com.mo.safir.home.c;

import com.mo.safir.highlevelModel.day.c.DayController;
import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.month.c.MonthController;
import com.mo.safir.highlevelModel.month.m.Month;
import com.mo.safir.home.s.ProgramStartupSettings;
import com.mo.safir.midlevelModel.income.income.c.IncomeController;
import com.mo.safir.midlevelModel.income.income.m.Income;
import com.mo.safir.midlevelModel.spending.spending.c.SpendingController;
import com.mo.safir.midlevelModel.spending.spending.m.Spending;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class Logic1Controller {

    private final ProgramStartupSettings service;

    private final MonthController monthController;

    //refactor back to private
    public final DayController dayController;

    public final IncomeController incomeController;

    public final SpendingController spendingController;


    private ArrayList<Day> days(){
        return (ArrayList<Day>) dayController.service.fetchAll();
    }

    private ArrayList<Month> months (){
        return (ArrayList<Month>) monthController.service.fetchAll();
    }


    @GetMapping("/month/{id}/day")
    public String getLastMonth(@PathVariable ("id") Long id){
        Month month = getMonthById(id);
        Day day = getDay(month);
        return "redirect:/month/"+id+"/day/"+day.getId();
    }

    public void setLogic2Settings(Long dayId,Long monthId, Model model){

        Month month = getMonthById(monthId);
        Day day = getDay(month, dayId);

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
    public void setLogic1Settings(Long id, Long dayId, Model model) {

        Month currentMonth = getMonthById(id);

        Day currentDay = getDay(currentMonth, dayId);

        service.setAttributes(model);

        model.addAttribute("monthList", months());
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("monthsDays", getAllDaysByMonthId(currentMonth));
        model.addAttribute("currentDay", currentDay);
    }

    public Month getLastMonth() {
        return months().get(months().size() - 1);
    }


    public Month getMonthById(Long id) {
        return monthController.service.findById(id);
    }

    public Day getDay(Month month) {
        Day day;
        if(month.getDays().size() == 0){
            day = new Day();
            day.setId(0L);
            day.setDayIndex(0);

        } else {
            day = month.getDays().get(month.getDays().size() - 1);
        }
        return day;
    }

    public Day getDay(Month month, Long dayId){
        Day day;
        if (month.getDays().size() == 0){
            day = new Day();
            day.setId(0L);
            day.setDayIndex(0);

        } else {
            day = dayController.service.findById(dayId);
        }
        return day;
    }

    public List<Day> getAllDaysByMonthId(Month currentMonth) {
        return dayController.service.findAllById(currentMonth.getId());
    }

}
/**
 * Man kunne overveje at oprette en ny kontroller, som kun
 * står for at håndtere måneder og dage, og sende og modtage
 * data fra hjemme-controlleren.
 * På denne måde kan hjemme-kontrolleren være åben over
 * at modtage data fra flere controller/kilder
 */