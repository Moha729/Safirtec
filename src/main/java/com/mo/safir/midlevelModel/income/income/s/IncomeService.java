package com.mo.safir.midlevelModel.income.income.s;

import com.mo.safir.midlevelModel.income.income.m.Income;
import com.mo.safir.midlevelModel.income.income.r.IncomeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class IncomeService {

    private final IncomeRepo repo;

    public ArrayList<Income> fetchAll(){
        List<Income> list = repo.findAll();
        return (ArrayList<Income>) list;
    }

    public Income addNew(Income model){
        repo.save(model);
        return model;
    }

    public Income findById(Long id){
        return repo.findById(id).orElseThrow(()->
                new RuntimeException("%s %d not found!".formatted("model4", id)));
    }

    public Income findByDayId(Long id){
        //giv mig den income som har dayId = id
        Income income = new Income();
        for (int i = 0; i < fetchAll().size(); i++) {
            if (fetchAll().get(i).getDay().getId() == id){
                income = fetchAll().get(i);
            }
        }
        return income;
    }


}
