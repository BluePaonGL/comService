package fr.isep.comService.infrastructure.adapter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String articleId;
    private String articleTitle;

    @ManyToMany
    @JoinTable(
            name = "ArticleContenu",
            joinColumns = @JoinColumn(name = "articleId"),
            inverseJoinColumns = @JoinColumn(name = "contenuId")
    )
    Set<ContenuDao> contenuDaoSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleDao that = (ArticleDao) o;
        return articleId != null && Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
