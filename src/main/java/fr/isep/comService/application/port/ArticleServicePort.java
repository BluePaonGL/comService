package fr.isep.comService.application.port;

import fr.isep.comService.application.DTO.ArticleDto;
import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;

import java.util.List;

public interface ArticleServicePort {
    Article getArticleById(String articleId);
    List<Article> getArticleByArticleTitle(String articleTitle);

    Article saveArticle(ArticleDto articleDto);

    List<Article> getArticles();

    ArticleContenuDao addContenuToArticle(String articleId, String contenuId);
    Article removeContenuFromArticle(String articleId, String contenuId);

    List<Contenu> getContentsOfAnArticle(String articleId);
}
