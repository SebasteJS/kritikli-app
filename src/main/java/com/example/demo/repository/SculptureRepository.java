package com.example.demo.repository;

import com.example.demo.model.Sculpture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SculptureRepository extends JpaRepository<Sculpture, Long> {
}
