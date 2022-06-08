package fr.isep.comService.application.controller;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.http.client.methods.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class ComControllerTest {
    String baseUrl = "http://localhost:3000";

    @Test
    public void given_article_id_return_200() throws IOException{
        String articleId = "2c948a85813f2ffc01813f3097290000";
        HttpUriRequest request = new HttpGet(this.baseUrl + "/article/byid/" + articleId);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }
}
