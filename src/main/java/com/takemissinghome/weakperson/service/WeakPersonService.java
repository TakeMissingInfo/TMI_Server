package com.takemissinghome.weakperson.service;

import com.takemissinghome.weakperson.api.request.BenefitDataRequest;
import com.takemissinghome.weakperson.domain.BenefitData;
import com.takemissinghome.weakperson.domain.BenefitType;
import com.takemissinghome.weakperson.domain.WeakPerson;
import com.takemissinghome.weakperson.domain.WeakPersonType;
import com.takemissinghome.weakperson.repository.WeakPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class WeakPersonService {

    private final WeakPersonRepository weakPersonRepository;

    @Transactional
    public void renewData(WeakPersonType weakPersonType, BenefitType benefitType,
                          List<BenefitDataRequest> benefitDataRequests) {
        final List<WeakPerson> weakPeople = toWeakPeople(benefitType, weakPersonType, benefitDataRequests);
        weakPersonRepository.saveAll(weakPeople);
    }

    private List<WeakPerson> toWeakPeople(BenefitType benefitType, WeakPersonType weakPersonType, List<BenefitDataRequest> benefitDataRequests) {
        return benefitDataRequests.stream()
                .map(benefitDataRequest -> WeakPerson.builder()
                        .benefitType(benefitType)
                        .weakPersonType(weakPersonType)
                        .benefitData(new BenefitData(benefitDataRequest.getSvcId(),
                                benefitDataRequest.getName(),
                                benefitDataRequest.getCompetentInstitution(),
                                benefitDataRequest.getSupportForm(),
                                benefitDataRequest.getDetailsInfo(),
                                benefitDataRequest.getDetailsInfoUrl()))
                        .build())
                .collect(toList());
    }

    public List<BenefitData> findBenefitDataDetails(String weakPerson, List<String> benefits) {
        final WeakPersonType weakPersonType = WeakPersonType.valueOf(weakPerson);
        final List<BenefitType> benefitTypes = benefits.stream()
                .map(BenefitType::valueOf)
                .collect(toList());

        return weakPersonRepository.findAllByWeakPersonTypeAndBenefitTypes(weakPersonType, benefitTypes);
    }
}
