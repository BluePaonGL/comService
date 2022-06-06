package fr.isep.comService.application.port;

import fr.isep.comService.application.DTO.ContenuDto;
import fr.isep.comService.domain.model.Contenu;

import java.util.List;

public interface ContenuServicePort {
    Contenu getContenuById(String contenuId);
    Contenu getContenuByContenuType(String contenuType);

    Contenu saveContenu(ContenuDto contenuDto);

    List<Contenu> getContenus();
}
