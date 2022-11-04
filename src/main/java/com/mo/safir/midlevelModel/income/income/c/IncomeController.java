package com.mo.safir.midlevelModel.income.income.c;

import com.mo.safir.highlevelModel.day.c.DayController;
import com.mo.safir.midlevelModel.income.income.m.Income;
import com.mo.safir.midlevelModel.income.income.s.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class IncomeController {

    public final IncomeService service;

    private final DayController dayController;



    @GetMapping("/month/{monthId}/day/{dayId}/new-income")
    public String newIncome(@PathVariable("dayId") Long dayId, Model model){
        model.addAttribute("day", dayController.service.findById(dayId));

        return "income/new-income";
    }

    @PostMapping("/post-income")
    public String newIncome(@ModelAttribute Income income){
        service.addNew(income);
        return "redirect:/";
    }



    /*



    @GetMapping("/month/{monthId}/day/{dayId}/view")
    public String getAll(@PathVariable ("dayId") Long dayId,Model model){
        List<Spending> spendings = service.findAllById(dayId);
        model.addAttribute("day", dayController.service.findById(dayId));
        model.addAttribute("spendings", spendings);
        return "spending/view-spendings";
    }

---------------------------------------------------------------------------------
    private ArrayList<Income> incomes;


    @GetMapping("/view-model4")
    public String getViewModel(Model model){
        incomes = incomeService.fetchAll();
        model.addAttribute("model5List", incomes);
        return "model4/view-model4";
    }

    @GetMapping("/new-model4")
    public String getNewModel (){
        return "model4/new-model4";
    }

    @PostMapping("/postNewModel4")
    public String postNewModel (@ModelAttribute Income income){
        incomeService.addNew(income);
        return "redirect:/";
    }*/
}
