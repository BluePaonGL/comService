package fr.isep.comService.application.controller;

import fr.isep.comService.application.DTO.ArticleDto;
import fr.isep.comService.application.port.ArticleServicePort;
import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ArticleContenuDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
@Validated
public class ArticleController {

    private final ArticleServicePort articleServicePort;

    @PostMapping("/create")
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto){
        return ResponseEntity.ok(this.articleServicePort.saveArticle(articleDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticle(){
        return new ResponseEntity<>(this.articleServicePort.getArticles(), HttpStatus.OK);
    }

    @GetMapping("/byid/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable String articleId){
        return new ResponseEntity<>(this.articleServicePort.getArticleById(articleId), HttpStatus.OK);
    }

    //TODO return list ?
    @GetMapping("/bytitle/{articleTitle}")
    public ResponseEntity<List<Article>> getArticleByArticleTitle(@PathVariable String articleTitle){
        return new ResponseEntity<>(this.articleServicePort.getArticleByArticleTitle(articleTitle), HttpStatus.OK);
    }

    @PostMapping("/{articleId}/add/{contenuId}")
    public ResponseEntity<ArticleContenuDao> addContenuToArticle(@PathVariable String articleId, @PathVariable String contenuId){
        return ResponseEntity.ok(this.articleServicePort.addContenuToArticle(articleId, contenuId));
    }

    @DeleteMapping("/{articleId}/remove/{contenuId}")
    public ResponseEntity<Article> removeContenuFromArticle(@PathVariable String articleId, @PathVariable String contenuId){
        return ResponseEntity.ok(this.articleServicePort.removeContenuFromArticle(articleId, contenuId));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<List<Contenu>> getContentsOfAnArticle(@PathVariable String articleId){
        return new ResponseEntity<>(this.articleServicePort.getContentsOfAnArticle(articleId), HttpStatus.OK);
    }
}
