package com.takemissinghome.cafeteria.repository;

import com.takemissinghome.cafeteria.model.Cafeteria;

import java.util.List;

public interface CafeteriaRepositoryCustom {
    List<Cafeteria> findAllNearby(double latitude, double longitude);
}
