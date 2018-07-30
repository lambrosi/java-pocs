package com.lucasambrosi.app.repository;

import com.lucasambrosi.app.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    List<ContactEntity> findByPersonNameContaining(String name);
}
