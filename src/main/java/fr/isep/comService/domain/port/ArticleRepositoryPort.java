package fr.isep.comService.domain.port;

import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;

import java.util.List;

public interface ArticleRepositoryPort {
    Article findById(String articleId);
    List<Article> findByArticleTitle(String articleTitle);

    //TODO page ?

    Article saveArticle(Article article);
    List<Article> findAll();

    ArticleContenuDao addContenuToArticle(Article article, Contenu contenu);
    Article removeContenuFromArticle(Article article, Contenu contenu);

    List<Contenu> getContentsOfAnArticle(String articleId);
    void delete(String articleId);
}
