package fr.isep.comService.domain.model;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class Contenu {
    private String contenuId;
    private String type;
    private String contenu;
    private Integer ordering;
}
