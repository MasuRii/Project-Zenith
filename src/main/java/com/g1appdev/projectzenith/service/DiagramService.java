// DiagramService.java
package com.g1appdev.projectzenith.service;

import com.g1appdev.projectzenith.entity.Diagram;
import com.g1appdev.projectzenith.repository.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiagramService {

    @Autowired
    private DiagramRepository diagramRepository;

    public List<Diagram> getAllDiagrams(){
        return diagramRepository.findAll();
    }

    public Diagram getDiagramById(Integer id){
        Optional<Diagram> diagram = diagramRepository.findById(id);
        return diagram.orElse(null);
    }

    public Diagram createDiagram(Diagram diagram){
        return diagramRepository.save(diagram);
    }

    public Diagram updateDiagram(Integer id, Diagram updatedDiagram){
        Optional<Diagram> optionalDiagram = diagramRepository.findById(id);
        if(optionalDiagram.isPresent()){
            Diagram diagram = optionalDiagram.get();
            diagram.setType(updatedDiagram.getType());
            diagram.setFile_path(updatedDiagram.getFile_path());
            diagram.setVersion(updatedDiagram.getVersion());
            return diagramRepository.save(diagram);
        } else {
            return null;
        }
    }

    public void deleteDiagram(Integer id){
        diagramRepository.deleteById(id);
    }
}