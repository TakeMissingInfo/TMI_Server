package com.takemissinghome.weakperson.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeakPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "week_person_id")
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private WeakPersonType weakPersonType;

    @Enumerated(EnumType.STRING)
    private BenefitType benefitType;

    @Embedded
    private BenefitData benefitData;

    @Builder
    public WeakPerson(Long svcId, WeakPersonType weakPersonType, BenefitType benefitType, BenefitData benefitData) {
        this.weakPersonType = weakPersonType;
        this.benefitType = benefitType;
        this.benefitData = benefitData;
    }
}
