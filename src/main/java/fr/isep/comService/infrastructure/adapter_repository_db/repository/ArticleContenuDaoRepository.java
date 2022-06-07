package fr.isep.comService.infrastructure.adapter_repository_db.repository;

import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleDao;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ContenuDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleContenuDaoRepository extends JpaRepository<ArticleContenuDao, Long>, JpaSpecificationExecutor<ArticleContenuDao> {
    ArticleContenuDao findById(String id);
    List<ArticleContenuDao> findByArticleDao(ArticleDao articleDao);
    ArticleContenuDao findByContenuDao(ContenuDao contenuDao);
    ArticleContenuDao findByArticleDaoAndContenuDao(ArticleDao articleDao, ContenuDao contenuDao);
}
