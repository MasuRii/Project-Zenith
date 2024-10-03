// DiagramController.java
package com.g1appdev.projectzenith.controller;

import com.g1appdev.projectzenith.entity.Diagram;
import com.g1appdev.projectzenith.service.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diagrams")
public class DiagramController {

    @Autowired
    private DiagramService diagramService;

    @GetMapping
    public List<Diagram> getAllDiagrams(){
        return diagramService.getAllDiagrams();
    }

    @GetMapping("/{id}")
    public Diagram getDiagramById(@PathVariable Integer id){
        return diagramService.getDiagramById(id);
    }

    @PostMapping
    public Diagram createDiagram(@RequestBody Diagram diagram){
        return diagramService.createDiagram(diagram);
    }

    @PutMapping("/{id}")
    public Diagram updateDiagram(@PathVariable Integer id, @RequestBody Diagram diagram){
        return diagramService.updateDiagram(id, diagram);
    }

    @DeleteMapping("/{id}")
    public void deleteDiagram(@PathVariable Integer id){
        diagramService.deleteDiagram(id);
    }
}