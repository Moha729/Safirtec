package com.mo.safir.highlevelModel.month.m;

import com.mo.safir.highlevelModel.day.m.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int monthIndex;

    private int year;

    @OneToMany (mappedBy = "month")
    private List<Day> days;

    public Month(int monthIndex, int year) {
        this.monthIndex = monthIndex;
        this.year = year;
    }

}
