package fr.isep.comService.infrastructure.adapter_repository_db.repository;

import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ContenuDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContenuRepository extends JpaRepository<ContenuDao, Long>, JpaSpecificationExecutor<ContenuDao> {
    ContenuDao findByContenuId(String contenuId);
    ContenuDao findByContenuType(String contenuType);
}
