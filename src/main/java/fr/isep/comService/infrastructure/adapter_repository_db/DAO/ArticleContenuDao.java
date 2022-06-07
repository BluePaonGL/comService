package fr.isep.comService.infrastructure.adapter_repository_db.DAO;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContenuDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "articleId")
    ArticleDao articleDao;

    @ManyToOne
    @JoinColumn(name = "contenuId")
    ContenuDao contenuDao;

    public int getContenuDaoOrdering(){
        return this.contenuDao.getOrdering();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleContenuDao that = (ArticleContenuDao) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
