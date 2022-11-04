package com.mo.safir.midlevelModel.spending.spending.c;

import com.mo.safir.highlevelModel.day.c.DayController;
import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.midlevelModel.income.income.c.IncomeController;
import com.mo.safir.midlevelModel.income.income.m.Income;
import com.mo.safir.midlevelModel.spending.spending.m.Spending;
import com.mo.safir.midlevelModel.spending.spending.s.SpendingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class SpendingController {

    public final SpendingService service;

    private final DayController dayController;

    private final IncomeController incomeController;

    //private final MonthController monthController;

    @GetMapping("/month/{monthId}/day/{dayId}/new-spending")
    public String newSpending (@PathVariable ("dayId") Long dayId, Model model){
        Day day = dayController.service.findById(dayId);
        model.addAttribute("day", dayController.service.findById(dayId));


        return "spending/new-spending";
    }

    @PostMapping("/post-spending")
    public String newSpending(@ModelAttribute Spending spending){
        service.addNew(spending);
        return "redirect:/";
    }

    @GetMapping("/month/{monthId}/day/{dayId}/view")
    public String getAll(@PathVariable ("dayId") Long dayId,Model model){
        List<Spending> spendings = service.findAllById(dayId);
        model.addAttribute("day", dayController.service.findById(dayId));
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

        return "spending/view-spendings";
    }



/*    private ArrayList<Spending> spendings;

    @PostMapping("/post-day")
    public String newDay(@ModelAttribute Day day){
        service.addNew(day);
        return "redirect:/";
    }


    @GetMapping("/view-model4")
    public String getViewModel(Model model){
        spendings = spendingService.fetchAll();
        model.addAttribute("model5List", spendings);
        return "model4/view-model4";
    }

    @GetMapping("/new-model4")
    public String getNewModel (){
        return "model4/new-model4";
    }

    @PostMapping("/postNewModel4")
    public String postNewModel (@ModelAttribute Spending spending){
        spendingService.addNew(spending);
        return "redirect:/";
    }*/
}
