package com.takemissinghome.cafeteria.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.takemissinghome.cafeteria.model.Cafeteria;
import com.takemissinghome.utils.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.takemissinghome.cafeteria.model.QCafeteria.cafeteria;

@Repository
@RequiredArgsConstructor
public class CafeteriaRepositoryCustomImpl implements CafeteriaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cafeteria> findAllNearby(double latitude, double longitude) {
        final List<Cafeteria> cafeterias = queryFactory.selectFrom(cafeteria)
                .where(cafeteria.id.in(
                        queryFactory.select(cafeteria.id.max())
                                .from(cafeteria)
                                .groupBy(cafeteria.address)))
                .fetch();
        final Map<Cafeteria, Double> orderDistanceMap = createCafeteriaDistanceMap(cafeterias, latitude, longitude);
        final Map<Cafeteria, Double> sortByValue = MapUtil.sortByValue(orderDistanceMap);

        return new ArrayList<>(sortByValue.keySet());
    }

    private Map<Cafeteria, Double> createCafeteriaDistanceMap(List<Cafeteria> cafeterias, double latitude, double longitude) {
        return cafeterias.stream()
                .collect(Collectors.toMap(c -> c, c -> c.calculateDistance(latitude, longitude)));
    }
}
