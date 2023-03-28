package org.example.repository;

import org.example.model.HooverInput;
import org.example.model.HooverRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverRepository extends JpaRepository<HooverRecord, Long> {
}