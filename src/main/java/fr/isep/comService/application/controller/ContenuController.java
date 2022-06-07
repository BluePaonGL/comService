package fr.isep.comService.application.controller;


import fr.isep.comService.application.DTO.ContenuDto;
import fr.isep.comService.application.port.ContenuServicePort;
import fr.isep.comService.domain.model.Contenu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contenu")
@Slf4j
@Validated
public class ContenuController {

    private final ContenuServicePort contenuServicePort;

    @PostMapping("/create")
    public ResponseEntity<Contenu> createContenu(@Valid @RequestBody ContenuDto contenuDto){
        return ResponseEntity.ok(this.contenuServicePort.saveContenu(contenuDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contenu>> getAllContenus(){
        return new ResponseEntity<>(this.contenuServicePort.getContenus(), HttpStatus.OK);
    }

    @GetMapping("/byid/{contenuId}")
    public ResponseEntity<Contenu> getContenuById(@PathVariable String contenuId){
        return new ResponseEntity<>(this.contenuServicePort.getContenuById(contenuId), HttpStatus.OK);
    }


    //TODO return list
    @GetMapping("/bytype/{contenuType}")
    public ResponseEntity<List<Contenu>> getContenuByContenuType(@PathVariable String contenuType){
        return new ResponseEntity<>(this.contenuServicePort.getContenuByContenuType(contenuType), HttpStatus.OK);
    }

    //TODO find images/text
}
