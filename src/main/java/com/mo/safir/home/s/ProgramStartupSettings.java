package com.mo.safir.home.s;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProgramStartupSettings {


    public void setAttributes(Model model){
        model.addAttribute("title", "Safir");
        model.addAttribute("homeButtonText", "SAFIR");
        model.addAttribute("userName", "Safir");
    }


}
