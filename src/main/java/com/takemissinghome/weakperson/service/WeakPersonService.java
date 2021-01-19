package com.takemissinghome.weakperson.service;

import com.takemissinghome.weakperson.api.dto.request.BenefitDataRequest;
import com.takemissinghome.weakperson.api.dto.request.WeakPersonDetailRequest;
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
    public void renewData(WeakPersonDetailRequest weakPersonDetailRequest) {
        BenefitType benefitType = BenefitType.findByCode(weakPersonDetailRequest.getBenefitCode());
        WeakPersonType weakPersonType = WeakPersonType.findByCode(weakPersonDetailRequest.getWeakPersonCode());

        final List<WeakPerson> weakPeople = toWeakPersonDataList(benefitType, weakPersonType, weakPersonDetailRequest.getBenefitDataRequests());
        weakPersonRepository.saveAll(weakPeople);
    }

    private List<WeakPerson> toWeakPersonDataList(BenefitType benefitType, WeakPersonType weakPersonType, List<BenefitDataRequest> benefitDataRequests) {
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
}
