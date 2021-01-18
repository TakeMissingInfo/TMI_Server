package com.takemissinghome.weakperson.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeakPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "week_person_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private WeakPersonType weakPersonType;

    @Enumerated(EnumType.STRING)
    private BenefitType benefitType;

    @Embedded
    private BenefitData benefitData;
}
