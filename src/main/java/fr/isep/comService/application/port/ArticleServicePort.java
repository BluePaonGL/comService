package fr.isep.comService.application.port;

import fr.isep.comService.application.DTO.ArticleDto;
import fr.isep.comService.domain.model.Article;

import java.util.List;

public interface ArticleServicePort {
    Article getArticleById(String articleId);
    Article getArticleByArticleTitle(String articleTitle);

    Article saveArticle(ArticleDto articleDto);

    List<Article> getArticles();

    Article addContenuToArticle(String articleId, String contenuId);
    Article removeContenuFromArticle(String articleId, String contenuId);
}
