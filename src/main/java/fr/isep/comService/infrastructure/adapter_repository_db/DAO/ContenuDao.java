package fr.isep.comService.infrastructure.adapter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//TODO Créer table contenu et relation OneToMany
//Ou pas ? On veut pouvoir ajouter autant d'éléments que nécessaire et les trier

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContenuDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String contenuId;
    private String contenuType;
    private String contenu;

    @ManyToMany(mappedBy = "contenuDaoSet")
    Set<ArticleDao> articleDaoSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContenuDao that = (ContenuDao) o;
        return contenuId != null && Objects.equals(contenuId, that.contenuId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
