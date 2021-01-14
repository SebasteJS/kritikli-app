package com.example.demo.repository;

import com.example.demo.model.Poetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoetryRepository extends JpaRepository<Poetry, Long> {
}
