package com.placementpro.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placementpro.backend.entity.Application;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    List<Application> findByStudentEmail(
            String studentEmail
    );

}