package fr.isep.comService.infrastructure.adapter_repository_db.repository;

import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<ArticleDao, Long>, JpaSpecificationExecutor<ArticleDao> {
    ArticleDao findByArticleId(String articleId);
    ArticleDao findByArticleTitle(String articleTitle);
}
