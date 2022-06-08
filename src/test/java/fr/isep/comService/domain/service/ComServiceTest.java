package fr.isep.comService.domain.service;


import fr.isep.comService.domain.model.Article;
import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.infrastructure.adapter_repository_db.adapter.ArticleRepositoryAdapter;
import fr.isep.comService.infrastructure.adapter_repository_db.adapter.ContenuRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComServiceTest {

    @Mock
    private ArticleRepositoryAdapter articleRepositoryAdapter;

    @Mock
    private ContenuRepositoryAdapter contenuRepositoryAdapter;

    @InjectMocks
    private ContenuService contenuService;

    @InjectMocks
    private ArticleService articleService;
    
    @BeforeEach
    void setMockOutput(){
        when(articleRepositoryAdapter.findById(not(eq("123")))).thenThrow(IllegalArgumentException.class);
        when(articleRepositoryAdapter.findById("123")).thenReturn(this.getValidArticle());
        when(articleRepositoryAdapter.findByArticleTitle("title")).thenReturn(this.getValidListArticle());
        when(articleRepositoryAdapter.findByArticleTitle(not(eq("title")))).thenThrow(IllegalArgumentException.class);
        when(contenuRepositoryAdapter.findById("123")).thenReturn(this.getValidContenu());
        when(contenuRepositoryAdapter.findById(not(eq("123")))).thenThrow(IllegalArgumentException.class);
        when(contenuRepositoryAdapter.findByContenuType("text")).thenReturn(this.getValidListContenu());
        when(contenuRepositoryAdapter.findByContenuType(not(eq("text")))).thenThrow(IllegalArgumentException.class);
    }

    protected Article getValidArticle(){
        return Article.builder()
                .articleId("123")
                .articleTitle("title")
                .build();
    }

    protected List<Article> getValidListArticle(){
        return List.of(Article.builder()
                .articleId("123")
                .articleTitle("title")
                .build());
    }

    protected Contenu getValidContenu(){
        return Contenu.builder()
                .contenuId("123")
                .type("text")
                .contenu("contenu")
                .ordering(1)
                .build();
    }

    protected List<Contenu> getValidListContenu(){
        return List.of(Contenu.builder()
                .contenuId("123")
                .type("text")
                .contenu("contenu")
                .ordering(1)
                .build());
    }

    @Test
    void should_return_valid_article_when_provided_valid_id(){
        assertEquals(this.articleService.getArticleById("123"), getValidArticle());
    }

    @Test
    void should_throw_NoSuchElementException_when_provided_with_invalid_id() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.articleService.getArticleById("234");
        });
    }

    @Test
    void should_return_valid_article_list_when_provided_valid_title(){
        assertEquals(this.articleService.getArticleByArticleTitle("title"), getValidListArticle());
    }

    @Test
    void should_throw_NoSuchElementException_when_provided_with_invalid_title() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.articleService.getArticleByArticleTitle("tlit");
        });
    }

    @Test
    void should_return_valid_contenu_when_provided_valid_id(){
        assertEquals(this.contenuService.getContenuById("123"), getValidContenu());
    }

    @Test
    void should_throw_NoSuchElementException_when_provided_content_with_invalid_id() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.contenuService.getContenuById("234");
        });
    }

    @Test
    void should_return_valid_contenu_list_when_provided_valid_type(){
        assertEquals(this.contenuService.getContenuByContenuType("text"), getValidListContenu());
    }

    @Test
    void should_throw_NoSuchElementException_when_provided_with_invalid_type() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.contenuService.getContenuByContenuType("tt");
        });
    }
}
