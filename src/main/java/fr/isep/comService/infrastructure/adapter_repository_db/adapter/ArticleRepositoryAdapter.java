package fr.isep.comService.infrastructure.adapter_repository_db.adapter;


import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.domain.port.ArticleRepositoryPort;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleDao;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ContenuDao;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ArticleContenuDaoRepository;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ArticleRepository;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ContenuRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ArticleRepositoryAdapter implements ArticleRepositoryPort {

    private final ArticleRepository articleRepository;
    private final ContenuRepository contenuRepository;
    private final ArticleContenuDaoRepository articleContenuDaoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Article findById(String articleId){
        ArticleDao articleDao = this.articleRepository.findByArticleId(articleId);
        try{
            return this.modelMapper.map(articleDao, Article.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This article does not exist in the database", exception);
        }
    }

    @Override
    public List<Article> findByArticleTitle(String articleTitle){
        return this.articleRepository.findAll()
                .stream().filter(articleDao -> articleDao.getArticleTitle().contains(articleTitle))
                .map(articleDao -> this.modelMapper.map(articleDao, Article.class))
                .collect(Collectors.toList());
    }

    @Override
    public Article saveArticle(Article article){
        ArticleDao articleDao = this.modelMapper.map(article, ArticleDao.class);
        return this.modelMapper.map(this.articleRepository.save(articleDao), Article.class);
    }

    @Override
    public List<Article> findAll(){
        return this.articleRepository.findAll()
                .stream().map(articleDao -> this.modelMapper.map(articleDao, Article.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String articleId){
        ArticleDao articleDao = this.articleRepository.findByArticleId(articleId);
        this.articleRepository.delete(articleDao);
    }

    @Override
    public ArticleContenuDao addContenuToArticle(Article article, Contenu contenu) {
        ArticleDao articleDao = this.modelMapper.map(article, ArticleDao.class);
        ContenuDao contenuDao = this.modelMapper.map(contenu, ContenuDao.class);
        ArticleContenuDao articleContenuDao = new ArticleContenuDao();
        articleContenuDao.setArticleDao(articleDao);
        articleContenuDao.setContenuDao(contenuDao);
        this.articleContenuDaoRepository.save(articleContenuDao);
        return articleContenuDao;
    }

    @Override
    public Article removeContenuFromArticle(Article article, Contenu contenu) {
        ArticleDao articleDao = this.modelMapper.map(article, ArticleDao.class);
        ContenuDao contenuDao = this.modelMapper.map(contenu, ContenuDao.class);
        ArticleContenuDao articleContenuDao = this.articleContenuDaoRepository.findByArticleDaoAndContenuDao(articleDao, contenuDao);
        this.articleContenuDaoRepository.delete(articleContenuDao);
        return this.modelMapper.map(articleDao, Article.class);
    }

    @Override
    public List<Contenu> getContentsOfAnArticle(String articleId){
        ArticleDao articleDao = this.articleRepository.findByArticleId(articleId);
        return this.articleContenuDaoRepository.findAll()
                .stream().filter(articleContenuDao1 -> articleContenuDao1.getArticleDao().getArticleId().equals(articleId))
                .sorted(Comparator.comparing(ArticleContenuDao::getContenuDaoOrdering))
                .map(articleContenuDao1 -> this.modelMapper.map(articleContenuDao1.getContenuDao(), Contenu.class))
                .collect(Collectors.toList());

    }
}
