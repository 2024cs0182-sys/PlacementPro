package com.placementpro.backend.controller;

import com.placementpro.backend.entity.PlacementDrive;
import com.placementpro.backend.repository.PlacementDriveRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
@CrossOrigin(origins = "*")
public class PlacementDriveController {


    private final PlacementDriveRepository driveRepository;


    public PlacementDriveController(
            PlacementDriveRepository driveRepository) {

        this.driveRepository =
                driveRepository;
    }


    // =========================================
    // GET ALL PLACEMENT DRIVES
    // =========================================

    @GetMapping
    public ResponseEntity<List<PlacementDrive>>
    getAllDrives() {

        return ResponseEntity.ok(
                driveRepository.findAll()
        );
    }


    // =========================================
    // GET ONE DRIVE
    // =========================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getDrive(
            @PathVariable Long id) {

        return driveRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                    () -> ResponseEntity
                        .notFound()
                        .build()
                );
    }


    // =========================================
    // CREATE DRIVE
    // =========================================

    @PostMapping
    public ResponseEntity<PlacementDrive>
    createDrive(
            @RequestBody PlacementDrive drive) {

        PlacementDrive savedDrive =
                driveRepository.save(drive);

        return ResponseEntity.ok(
                savedDrive
        );
    }


    // =========================================
    // DELETE DRIVE
    // =========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrive(
            @PathVariable Long id) {

        if (!driveRepository.existsById(id)) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        driveRepository.deleteById(id);

        return ResponseEntity.ok(
                java.util.Map.of(
                    "message",
                    "Placement drive deleted successfully."
                )
        );
    }

}