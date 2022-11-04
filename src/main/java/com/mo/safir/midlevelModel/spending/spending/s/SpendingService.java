package com.mo.safir.midlevelModel.spending.spending.s;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.midlevelModel.spending.spending.m.Spending;
import com.mo.safir.midlevelModel.spending.spending.r.SpeningRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SpendingService {

    private final SpeningRepo repo;

    public ArrayList<Spending> fetchAll(){
        List<Spending> list = repo.findAll();
        return (ArrayList<Spending>) list;
    }

    public Spending addNew(Spending model){
        repo.save(model);
        return model;
    }

    public Spending findById(Long id){
        return repo.findById(id).orElseThrow(()->
                new RuntimeException("%s %d not found!".formatted("model4", id)));
    }

    //find spendings for this.day
    public List<Spending> findAllById(Long dayId){
        if(dayId == 0){
            Day day = new Day();
            day.setDayIndex(1);
            System.out.println("dayId is null");
        }
        return getSpendingList(dayId);
    }

    private List<Spending> getSpendingList(Long id) {
        List<Spending> spendings = new ArrayList<>();
        List<Spending> allSpendings = fetchAll();
        renderSpeningList(id, spendings, allSpendings);
        return spendings;
    }

    private void renderSpeningList(Long id, List<Spending> spendings, List<Spending> allSpendings) {
        for (int i = 0; i < allSpendings.size(); i++)
            validateDay(id, spendings, allSpendings, i);

    }

    private void validateDay(Long id, List<Spending> spendings, List<Spending> allSpendings, int i) {
        if (getDayId(i, allSpendings) == id)
            spendings.add(allSpendings.get(i));
    }

    private Long getDayId(int i, List<Spending> allSpendings) {
        return allSpendings.get(i).getDay().getId();
    }


}
