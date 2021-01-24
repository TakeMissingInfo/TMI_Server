package com.takemissinghome.weakperson.repository;


import com.takemissinghome.weakperson.domain.BenefitData;
import com.takemissinghome.weakperson.domain.BenefitType;
import com.takemissinghome.weakperson.domain.WeakPersonType;

import java.util.List;

public interface WeakPersonQueryRepository {
    List<BenefitData> findAllByWeakPersonTypeAndBenefitTypes(WeakPersonType weakPersonType, List<BenefitType> benefitTypes);
}
