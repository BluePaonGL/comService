package fr.isep.comService.infrastructure.adapter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleContenuKey implements Serializable{
    private String id;

    @Column(name = "articleId")
    String articleId;

    @Column(name = "contenuId")
    String contenuId;

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setContenuId(String contenuId) {
        this.contenuId = contenuId;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getContenuId() {
        return contenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleContenuKey that = (ArticleContenuKey) o;
        return id != null && Objects.equals(articleId, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

