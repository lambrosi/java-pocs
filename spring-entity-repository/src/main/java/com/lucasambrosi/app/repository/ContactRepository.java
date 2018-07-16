package com.lucasambrosi.app.repository;

import com.lucasambrosi.app.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    @Query(value = "SELECT c FROM contacts c WHERE personName LIKE %:name%")
    List<ContactEntity> findByName(@Param(value = "name") String name);
}
