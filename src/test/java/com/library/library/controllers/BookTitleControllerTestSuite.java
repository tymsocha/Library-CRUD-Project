package com.library.library.controllers;

import com.library.library.business.book.title.controller.TitlesController;
import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TitlesController.class)
@RunWith(SpringRunner.class)
public class BookTitleControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TitlesController controller;

    @Test
    public void shouldGetAllTitles() throws Exception {
        BookTitleDTO bookTitle = BookTitleDTO.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitleDTO bookTitle1 = BookTitleDTO.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitleDTO bookTitle2 = BookTitleDTO.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        List<BookTitleDTO> bookTitleList = new ArrayList<>();
        bookTitleList.add(bookTitle);
        bookTitleList.add(bookTitle1);
        bookTitleList.add(bookTitle2);

        when(controller.getAll()).thenReturn(bookTitleList);

        mockMvc.perform(get("/v1/library/book/titles/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
