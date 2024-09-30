package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.Module;
import com.g1appdev.projectzenith.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

  @Autowired
  private ModuleService moduleService;

  @PostMapping
  public ResponseEntity < ? > createModule(@Valid @RequestBody Module module, BindingResult bindingResult) {

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

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity < List < Module >> getAllModules() {
    List < Module > modules = moduleService.getAllModules();
    return ResponseEntity.ok(modules);
  }

  @GetMapping("/{id}")
  public ResponseEntity < ? > getModuleById(@PathVariable Integer id) {
    Optional < Module > moduleOpt = moduleService.getModuleById(id);
    if (moduleOpt.isPresent()) {
      return ResponseEntity.ok(moduleOpt.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Module not found with ID: " + id);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity < ? > updateModule(@PathVariable Integer id,
    @Valid @RequestBody Module moduleDetails,
    BindingResult bindingResult) {

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

      if (e.getMessage().contains("not found")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity < ? > deleteModule(@PathVariable Integer id) {
    try {
      moduleService.deleteModule(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}