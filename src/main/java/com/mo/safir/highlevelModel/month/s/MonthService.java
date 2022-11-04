package com.mo.safir.highlevelModel.month.s;

import com.mo.safir.highlevelModel.month.m.Month;
import com.mo.safir.highlevelModel.month.r.MonthRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MonthService {

    private final MonthRepo repo;

    public List<Month> fetchAll(){
        return  repo.findAll();
    }

    public void addNew(Month month){
        repo.save(month);
    }

    public Month findById(Long id){
        return repo.findById(id).orElseThrow(()->
                new RuntimeException("%s %d not found!".formatted("month", id)));
    }

}
