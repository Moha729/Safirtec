package com.mo.safir.highlevelModel.month.c;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.month.m.Month;
import com.mo.safir.highlevelModel.month.s.MonthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class MonthController {

    public final MonthService service;

    //public final DayController dayController;


    @GetMapping("/new-month")
    public String newMonth() {

        return "month/new-month";
    }

    @PostMapping("/post-month")
    public String newMonth(@ModelAttribute Month month){
        //System.out.println(month.getMonthIndex() + " month index");
        //System.out.println(month.getYear() + " month year");
        service.addNew(month);

        int amountOfDays = countDays(month);

        for (int i = 1; i <= amountOfDays; i++) {
            Day newDay = new Day();
            newDay.setDayIndex(i);
            newDay.setMonth(month);
            //dayController.service.addNew(newDay);
        }
        return "redirect:/";
    }

    private int countDays(Month month) {
        if(month.getMonthIndex() == 1 || month.getMonthIndex() == 3 || month.getMonthIndex() == 5 ||
                month.getMonthIndex() == 7 || month.getMonthIndex() ==  8 ||
                month.getMonthIndex() == 10 || month.getMonthIndex() == 12){
            return 31;
        } else
            return 30;
    }
}
