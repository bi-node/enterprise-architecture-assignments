package com.binode.restclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Get book by id
        ResponseEntity<BookDto> responseEntity = restTemplate.getForEntity("http://localhost:8090/books/2", BookDto.class);
        BookDto bookDto = responseEntity.getBody();
        if (bookDto != null) {
            System.out.println(bookDto);
        }

        // Get all books
        ResponseEntity<BookDto[]> responseEntityList = restTemplate.getForEntity("http://localhost:8090/books", BookDto[].class);
        BookDto[] bookDtoArray = responseEntityList.getBody();
        if (bookDtoArray != null) {
            List<BookDto> bookDtoList = Arrays.asList(bookDtoArray);
            bookDtoList.forEach(book -> System.out.println(book.toString()));
        }

        // delete book by id
        restTemplate.delete("http://localhost:8090/books/2");


        // Update a book
        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setIsbn(3L);
        updatedBookDto.setTitle("Updated Title");
        updatedBookDto.setAuthor("Updated Author");

        restTemplate.put("http://localhost:8090/books/3", updatedBookDto);

        //save new book
        BookDto newBookDto = new BookDto(55, "New Title", "New Author");
        restTemplate.postForEntity("http://localhost:8090/books", newBookDto, BookDto.class);
    }
}
