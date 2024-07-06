package com.binode.restcontrollera;

import com.binode.restcontrollera.mapper.ModelMapperConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(modelMapper.map(book, BookDto.class));
        }
        return bookDTOs;
    }

    public BookDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> modelMapper.map(value, BookDto.class)).orElse(null);
    }

    public BookDto createBook(BookDto bookDto) {
        Optional<Book> book=bookRepository.findById(bookDto.getIsbn());
        if(book.isPresent()) {
           throw new RuntimeException("Book already exists");
        }
        else{
        Book savingbook=modelMapper.map(bookDto, Book.class);
        bookRepository.save(savingbook);
        return bookDto;
        }
    }

    public BookDto updateBook(long id, BookDto bookDto) {
        Book book=bookRepository.findById(id).orElse(null);
        System.out.println(book);
        if(book!=null){
            book.setIsbn(bookDto.getIsbn());
            book.setAuthor(bookDto.getAuthor());
            book.setTitle(bookDto.getTitle());
            bookRepository.save(book);
            return modelMapper.map(book, BookDto.class);
        }
        return null;
    }

    public BookDto deleteBook(Long id) {
       Book book = bookRepository.findById(id).orElse(null);
       if(book!=null){
           bookRepository.delete(book);
           return modelMapper.map(book, BookDto.class);
       }
       return null;
    }

    public BookDto searchBooks(String author) {
        Book book = bookRepository.findByAuthor(author);
        if(book!=null){
            return modelMapper.map(book, BookDto.class);
        }
        return null;
    }


}
