package com.binode.restcontrollera;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private long isbn;
    private String title;
    private String author;
}
