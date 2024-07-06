package com.binode.restcontrollera;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="books")
public class Book {
    @Id
    private long isbn;
    private String title;
    private String author;
}
