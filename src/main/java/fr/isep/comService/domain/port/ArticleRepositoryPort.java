package fr.isep.comService.domain.port;

import fr.isep.comService.domain.model.Article;

import java.util.List;

public interface ArticleRepositoryPort {
    Article findById(String articleId);
    Article findByArticleTitle(String articleTitle);

    //TODO page ?

    Article saveArticle(Article article);
    List<Article> findAll();

    Article addContenuToArticle(Article article, String contenuId);
    Article removeContenuFromArticle(Article article, String contenuId);

    void delete(String articleId);
}
