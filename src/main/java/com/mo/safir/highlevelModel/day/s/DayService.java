package com.mo.safir.highlevelModel.day.s;

import com.mo.safir.highlevelModel.day.m.Day;
import com.mo.safir.highlevelModel.day.r.DayRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DayService {

    private final DayRepo repo;

    public List<Day> fetchAll(){
        return repo.findAll();
    }

    public void addNew(Day day){
        repo.save(day);
    }

    public Day findById(Long id){
        return repo.findById(id).orElseThrow(() ->
                new RuntimeException("%s %d not found!".formatted("DAY:", id)));
    }

    public List<Day> findAllById(Long id){
        return getDayList(id);
    }

    private List<Day> getDayList(Long id) {
        List<Day> days = new ArrayList<>();
/*        List<Day> allDays = new ArrayList<Day>();
        if(fetchAll() != null){
        allDays = fetchAll();}
        else {
            System.out.println("Error creating empty list");}
        renderDayList(id, days, allDays);*/
        return days;
    }

    private void renderDayList(Long id, List<Day> days, List<Day> allDays) {
        for (int i = 0; i < allDays.size(); i++)
            validateMonth(id, days, allDays, i);

    }

    private void validateMonth(Long id, List<Day> days, List<Day> allDays, int i) {
        if (getMonthId(i, allDays) == id)
            days.add(allDays.get(i));
    }

    private Long getMonthId(int i, List<Day> allDays) {
        return allDays.get(i).getMonth().getId();
    }

}
