package com.takemissinghome.weakperson.repository;

import com.takemissinghome.weakperson.domain.WeakPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeakPersonRepository extends JpaRepository<WeakPerson, Long> {
}
