package fr.isep.comService.infrastructure.adapter_repository_db.adapter;


import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.domain.port.ArticleRepositoryPort;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleDao;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ContenuDao;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ArticleRepository;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ContenuRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ArticleRepositoryAdapter implements ArticleRepositoryPort {

    private final ArticleRepository articleRepository;
    private final ContenuRepository contenuRepository;
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
    public Article findByArticleTitle(String articleTitle){
        ArticleDao articleDao = this.articleRepository.findByArticleTitle(articleTitle);
        try{
            return this.modelMapper.map(articleDao, Article.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This article does not exist in the database", exception);
        }
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
    public Article addContenuToArticle(Article article, String contenuId) {
        ArticleDao articleDao = this.modelMapper.map(article, ArticleDao.class);
        ContenuDao contenuDao = this.contenuRepository.findByContenuId(contenuId);

        Set<ContenuDao> setOfContenu = new HashSet<>(articleDao.getContenuDaoSet());
        setOfContenu.add(contenuDao);
        articleDao.setContenuDaoSet(setOfContenu);

        Set<ArticleDao> setOfArticle = new HashSet<>(contenuDao.getArticleDaoSet());
        setOfArticle.add(articleDao);
        contenuDao.setArticleDaoSet(setOfArticle);

        this.modelMapper.map(this.contenuRepository.save(contenuDao), Contenu.class);
        return this.modelMapper.map(this.articleRepository.save(articleDao), Article.class);
    }

    @Override
    public Article removeContenuFromArticle(Article article, String contenuId) {
        ArticleDao articleDao = this.modelMapper.map(article, ArticleDao.class);
        ContenuDao contenuDao = this.contenuRepository.findByContenuId(contenuId);

        Set<ContenuDao> setOfContenu = new HashSet<>(articleDao.getContenuDaoSet());
        setOfContenu.remove(contenuDao);
        articleDao.setContenuDaoSet(setOfContenu);

        Set<ArticleDao> setOfArticle = new HashSet<>(contenuDao.getArticleDaoSet());
        setOfArticle.remove(articleDao);
        contenuDao.setArticleDaoSet(setOfArticle);

        this.modelMapper.map(this.contenuRepository.save(contenuDao), Contenu.class);
        return this.modelMapper.map(this.articleRepository.save(articleDao), Article.class);
    }
}
