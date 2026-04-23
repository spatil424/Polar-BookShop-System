package com.polarbookshop.catalogservice.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;

@WebMvcTest(BookController.class)
class BookControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private BookService bookService;
    
    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
	String isbn = "73737313940";
	when(bookService.viewBookDetails(isbn)).thenThrow(BookNotFoundException.class);
	mockMvc.perform(get("/books/"+isbn))
		.andExpect(status().isNotFound());
    }

}
