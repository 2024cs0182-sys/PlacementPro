package com.placementpro.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placementpro.backend.entity.PlacementDrive;

public interface PlacementDriveRepository
        extends JpaRepository<PlacementDrive, Long> {

}