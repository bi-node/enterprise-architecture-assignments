package com.binode.restclient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookDto {
    private long isbn;
    private String title;
    private String author;
}
