package com.binode.restcontrollera;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        BookDto book=bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDto book) {
        try {
            BookDto createdBook = bookService.createBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println("Error while creating the book " + e.getMessage());
            return new ResponseEntity<>(new BookError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable long id, @RequestBody BookDto book) {
        BookDto updatedBook=bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable long id){
        BookDto deletedBook=bookService.deleteBook(id);
        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }

}
