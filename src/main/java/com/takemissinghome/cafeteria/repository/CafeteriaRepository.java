package com.takemissinghome.cafeteria.repository;

import com.takemissinghome.cafeteria.model.Cafeteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeteriaRepository extends JpaRepository<Cafeteria, Long>, CafeteriaRepositoryCustom {
}
