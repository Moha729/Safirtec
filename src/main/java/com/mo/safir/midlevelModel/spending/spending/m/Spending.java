package com.mo.safir.midlevelModel.spending.spending.m;

import com.mo.safir.highlevelModel.day.m.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Spending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplier;

    private int amount;


    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
}
