package com.polarbookshop.catalogservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.polarbookshop.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;
    
    @Test
    void whenPostRequestThenBookCreated() {
	var expectedBook = new Book("1231231231","New Title","Sumeet", 6.56);
	webTestClient.post()
		.uri("/books")
		.bodyValue(expectedBook)
		.exchange()
		.expectStatus().isCreated()
		.expectBody(Book.class).value(
				actualBook -> {
				    Assertions.assertThat(actualBook).isNotNull();
				    Assertions.assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				}
			);
	
    }

}
