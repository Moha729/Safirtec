package com.mo.safir.highlevelModel.day.m;

import com.mo.safir.highlevelModel.month.m.Month;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dayIndex;

    @ManyToOne
    private Month month;


}
