package fr.isep.comService.domain.service;


import fr.isep.comService.application.DTO.ContenuDto;
import fr.isep.comService.application.port.ContenuServicePort;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.domain.port.ContenuRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContenuService implements ContenuServicePort {

    private final ContenuRepositoryPort contenuRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public Contenu getContenuById(String contenuId) {
        return this.contenuRepositoryPort.findById(contenuId);
    }

    @Override
    public List<Contenu> getContenuByContenuType(String contenuType) {
        return this.contenuRepositoryPort.findByContenuType(contenuType);
    }

    @Override
    public Contenu saveContenu(ContenuDto contenuDto) {
        Contenu contenu = this.modelMapper.map(contenuDto, Contenu.class);
        return this.contenuRepositoryPort.saveContenu(contenu);
    }

    @Override
    public List<Contenu> getContenus() {
        return this.contenuRepositoryPort.findAll();
    }
}
