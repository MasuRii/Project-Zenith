package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.Module;
import com.g1appdev.projectzenith.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // For Spring Boot 3.x
// import javax.validation.Valid; // Uncomment for Spring Boot 2.x
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // Create Module
    @PostMapping
    public ResponseEntity<?> createModule(@Valid @RequestBody Module module, BindingResult bindingResult) {
        // Handle Validation Errors
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Module createdModule = moduleService.createModule(module);
            return ResponseEntity.ok(createdModule);
        } catch (RuntimeException e) {
            // Return Bad Request with error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get All Modules
    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        List<Module> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    // Get Module by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Integer id) {
        Optional<Module> moduleOpt = moduleService.getModuleById(id);
        if (moduleOpt.isPresent()) {
            return ResponseEntity.ok(moduleOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Module not found with ID: " + id);
        }
    }

    // Update Module
    @PutMapping("/{id}")
    public ResponseEntity<?> updateModule(@PathVariable Integer id,
                                          @Valid @RequestBody Module moduleDetails,
                                          BindingResult bindingResult) {
        // Handle Validation Errors
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Module updatedModule = moduleService.updateModule(id, moduleDetails);
            return ResponseEntity.ok(updatedModule);
        } catch (RuntimeException e) {
            // Return Bad Request or Not Found based on exception message
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    // Delete Module
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Integer id) {
        try {
            moduleService.deleteModule(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Return Not Found with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}