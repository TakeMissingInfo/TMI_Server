package com.takemissinghome.weakperson.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.takemissinghome.weakperson.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.takemissinghome.weakperson.domain.QBenefitData.*;
import static com.takemissinghome.weakperson.domain.QWeakPerson.*;

@Repository
@RequiredArgsConstructor
public class WeakPersonQueryRepositoryImpl implements WeakPersonQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BenefitData> findAllByWeakPersonTypeAndBenefitTypes(WeakPersonType weakPersonType, List<BenefitType> benefitTypes) {
        return queryFactory
                .select(benefitData)
                .from(weakPerson)
                .where(weakPerson.weakPersonType.eq(weakPersonType),
                        weakPerson.benefitType.in(benefitTypes))
                .fetch();
    }
}
