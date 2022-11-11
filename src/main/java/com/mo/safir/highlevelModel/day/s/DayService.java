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

    public List<Day> findAllDaysByMonthId(Long monthId){
        List<Day> newDaysList = new ArrayList<>();
        List<Day> allDays = fetchAll();
        getDaysByMonthId(monthId, newDaysList, allDays);
        return newDaysList;
    }

    private static void getDaysByMonthId(Long monthId, List<Day> newDaysList, List<Day> allDays) {
        for (int i = 0; i < allDays.size(); i++) {
            if (isMonthIdMatch(monthId, allDays, i))
                newDaysList.add(allDays.get(i));
        }
    }

    private static boolean isMonthIdMatch(Long monthId, List<Day> allDays, int i) {
        return allDays.get(i).getMonth().getId() == monthId;
    }

}
