package com.mo.safir.home.c;

import com.mo.safir.highlevelModel.day.c.DayController;
import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.month.c.MonthController;
import com.mo.safir.highlevelModel.month.m.Month;
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

    private final MonthController monthController;

    private final DayController dayController;

    private ArrayList<Day> days(){
        return (ArrayList<Day>) dayController.service.fetchAll();
    }

    private ArrayList<Month> months (){
        return (ArrayList<Month>) monthController.service.fetchAll();
    }


    @GetMapping("/month/{id}/day")
    public String getLastMonth(@PathVariable ("id") Long id){
        Month month = getMonthById(id);
        Day day = getLastDay(month);
        return "redirect:/month/"+id+"/day/"+day.getId();
    }


    public void setLogic1Settings(Long id, Long dayId, Model model) {

        Month currentMonth = getMonthById(id);

        Day currentDay = getSpecificDay(currentMonth, dayId);

        //model.addAllAttributes(List<>)


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

    public Day getLastDay(Month month) {
        Day day;
        if(isDayListOfMonthNull(month)){
            day = makeDay(month);

        } else {
            day = makeLastDay(month);
        }
        return day;
    }

    private static Day makeLastDay(Month month) {
        return month.getDays().get(month.getDays().size() - 1);
    }

    private static boolean isDayListOfMonthNull(Month month) {
        return month.getDays().size() == 0;
    }

    private static Day makeDay( Month month) {
        Day day;
        day = new Day();
        day.setId(0L);
        day.setDayIndex(0);
        day.setMonth(month);
        return day;
    }

    public Day getSpecificDay(Month month, Long dayId){
        Day day;
        if (isDayListOfMonthNull(month))
            day = makeDay(month);

         else
            day = getDayById(dayId);

        return day;
    }

    private Day getDayById(Long dayId) {
        return dayController.service.findById(dayId);
    }

    public List<Day> getAllDaysByMonthId(Month currentMonth) {
        return dayController.service.findAllDaysByMonthId(currentMonth.getId());

    }

}
