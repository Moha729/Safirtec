package com.mo.safir.highlevelModel.day.c;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.day.s.DayService;
import com.mo.safir.highlevelModel.month.c.MonthController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DayController {

    public final DayService service;

    private final MonthController monthController;

    @GetMapping("/new-day")
    public String newDay (Model model){
        model.addAttribute("months", monthController.service.fetchAll());
        return "day/new-day";
    }

    @PostMapping("/post-day")
    public String newDay(@ModelAttribute Day day){
        service.addNew(day);
        return "redirect:/";
    }

}
