package com.placementpro.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementpro.backend.entity.Application;
import com.placementpro.backend.entity.PlacementDrive;
import com.placementpro.backend.repository.ApplicationRepository;
import com.placementpro.backend.repository.PlacementDriveRepository;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    private final PlacementDriveRepository driveRepository;


    public ApplicationController(
            ApplicationRepository applicationRepository,
            PlacementDriveRepository driveRepository) {

        this.applicationRepository =
                applicationRepository;

        this.driveRepository =
                driveRepository;
    }


    // =========================================
    // APPLY FOR PLACEMENT DRIVE
    // =========================================

    @PostMapping
    public ResponseEntity<?> apply(
            @RequestBody Application application) {

        if (application.getStudentEmail() == null ||
            application.getStudentEmail().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        Map.of(
                            "message",
                            "Student email is required."
                        )
                    );
        }


        if (application.getDriveId() == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        Map.of(
                            "message",
                            "Drive ID is required."
                        )
                    );
        }


        PlacementDrive drive =
                driveRepository
                        .findById(
                            application.getDriveId()
                        )
                        .orElse(null);


        if (drive == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }


        application.setCompanyName(
                drive.getCompanyName()
        );


        application.setJobRole(
                drive.getJobRole()
        );


        application.setStatus(
                "Applied"
        );


        Application savedApplication =
                applicationRepository.save(
                    application
                );


        return ResponseEntity.ok(
                savedApplication
        );
    }


    // =========================================
    // GET STUDENT APPLICATIONS
    // =========================================

    @GetMapping("/{email}")
    public ResponseEntity<List<Application>>
    getApplications(
            @PathVariable String email) {

        return ResponseEntity.ok(
            applicationRepository
                .findByStudentEmail(email)
        );
    }

}