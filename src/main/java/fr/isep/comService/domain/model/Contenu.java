package fr.isep.comService.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contenu {
    private String contenuId;
    private String type;
    private String contenu;
    private Integer ordering;
}
