package com.internaute.internaute_microservice.modules.internaute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/internautes")
public class InternauteController {

    @Autowired
    private InternauteService internauteService;

    @GetMapping
    public List<Internaute> getAllInternautes() {
        return internauteService.getAllInternautes();
    }

    @GetMapping("/{id}")
    public Optional<Internaute> getInternauteById(@PathVariable Long id) {
        return internauteService.getInternauteById(id);         
    }

    @PostMapping
    public Internaute ajouterInternaute(@RequestBody Internaute internaute) {
        return internauteService.createInternaute(internaute);
    }

    @PutMapping("/{id}")
    public Internaute updateInternaute(@PathVariable Long id, @RequestBody Internaute internaute) {
        return internauteService.updateInternaute(id, internaute).get();
    }

    @DeleteMapping("/{id}")
    public void deleteInternaute(@PathVariable Long id) {
        internauteService.deleteInternaute(id);
    }
}