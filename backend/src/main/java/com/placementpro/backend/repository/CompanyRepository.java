package com.placementpro.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placementpro.backend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}