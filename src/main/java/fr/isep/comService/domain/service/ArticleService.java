package fr.isep.comService.domain.service;

import fr.isep.comService.application.DTO.ArticleDto;
import fr.isep.comService.application.port.ArticleServicePort;
import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.domain.port.ArticleRepositoryPort;
import fr.isep.comService.domain.port.ContenuRepositoryPort;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService implements ArticleServicePort {

    private final ArticleRepositoryPort articleRepositoryPort;
    private final ContenuRepositoryPort contenuRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public Article getArticleById(String articleId) {
        return this.articleRepositoryPort.findById(articleId);
    }

    @Override
    public List<Article> getArticleByArticleTitle(String articleTitle) {
        return this.articleRepositoryPort.findByArticleTitle(articleTitle);
    }

    @Override
    public Article saveArticle(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        return this.articleRepositoryPort.saveArticle(article);
    }

    @Override
    public List<Article> getArticles() {
        return this.articleRepositoryPort.findAll();
    }

    @Override
    public ArticleContenuDao addContenuToArticle(String articleId, String contenuId) {
        Article article = this.articleRepositoryPort.findById(articleId);
        Contenu contenu = this.contenuRepositoryPort.findById(contenuId);
        return this.articleRepositoryPort.addContenuToArticle(article, contenu);
    }

    @Override
    public Article removeContenuFromArticle(String articleId, String contenuId) {
        Article article = this.articleRepositoryPort.findById(articleId);
        Contenu contenu = this.contenuRepositoryPort.findById(contenuId);
        return this.articleRepositoryPort.removeContenuFromArticle(article, contenu);
    }

    @Override
    public List<Contenu> getContentsOfAnArticle(String articleId){
        return this.articleRepositoryPort.getContentsOfAnArticle(articleId);
    }
}
