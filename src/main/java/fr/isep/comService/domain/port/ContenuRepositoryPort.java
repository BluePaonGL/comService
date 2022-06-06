package fr.isep.comService.domain.port;

import fr.isep.comService.domain.model.Contenu;

import java.util.List;

public interface ContenuRepositoryPort {
    Contenu findById(String contenuId);
    Contenu findByContenuType(String type);

    Contenu saveContenu(Contenu contenu);
    List<Contenu> findAll();

    void delete(String contenuId);
}
