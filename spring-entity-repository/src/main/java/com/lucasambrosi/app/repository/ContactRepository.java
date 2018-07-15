package com.lucasambrosi.app.repository;

import com.lucasambrosi.app.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    @Query(value = "SELECT c FROM contacts c WHERE id > 1")
    List<ContactEntity> getIdMore1();
}
