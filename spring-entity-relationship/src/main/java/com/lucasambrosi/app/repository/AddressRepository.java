package com.lucasambrosi.app.repository;

import com.lucasambrosi.app.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
