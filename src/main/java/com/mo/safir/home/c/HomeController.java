package com.mo.safir.home.c;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.month.m.Month;
import com.mo.safir.home.s.ProgramStartupSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class HomeController {

    private final ProgramStartupSettings programStartupSettings;

    private final Logic1Controller logic1Controller;

    private final Logic2Controller logic2Controller;



    @GetMapping()
    public String getIndex (){
        Month month = logic1Controller.getLastMonth();
        Day day = logic1Controller.getLastDay(month);

        return "redirect:/month/"+month.getId()+"/day/"+day.getId();
    }

    @GetMapping("/month/{monthId}/day/{dayId}")
    public String getIndex(@PathVariable("monthId") Long monthId,
                           @PathVariable ("dayId") Long dayId,
                           Model model){

        programStartupSettings.setAttributes(model);

        logic1Controller.setLogic1Settings(monthId, dayId, model);
        logic2Controller.setLogic2Settings(dayId, monthId, model);

        return "home/index";

    }
}
