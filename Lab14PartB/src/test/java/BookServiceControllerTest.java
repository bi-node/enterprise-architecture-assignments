import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


public class BookServiceControllerTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetAllBooksTest(){
        Book book1=new Book("878","Book 878", 18.95, "Joe Smith");
        Book book2=new Book("123","Book 123", 20.95, "Jane Smith");
        //adding two books for test
        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);
        //testing the getall method
        given()
                .when()
                .get("/books")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("books.isbn",hasItems("878","123"))
                .body("books.title",hasItems("Book 878", "Book 123"))
                .body("books.price",hasItems(18.95f, 20.95f))
                .body("books.author",hasItems("Joe Smith", "Jane Smith"));
        //cleanup
        given()
                .when()
                .delete("/books/878");



        given()
                .when()
                .delete("/books/123");

    }

    @Test
    public void testDeleteBookTest(){
        Book book=new Book("878","Book 878", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        given()
                .when()
                .delete("/books/878");

        given()
                .when()
                .get("/books/878")
                .then()
                .statusCode(404);

    }

    @Test
    public void testAddBookTest(){
        Book book=new Book("878","Book 878", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/books/878")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 878"))
                .body("author",equalTo("Joe Smith"))
                .body("price",equalTo(18.95f));


        //cleanup
        given()
                .when()
                .delete("/books/878");


    }

    @Test
    public void testUpdateBookTest(){
        Book book=new Book("878","Book 878", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        book.setAuthor("Jane Smith");
        book.setPrice(60.5);

        given()
                .contentType("application/json")
                .body(book)
                .when()
                .put("/books/878")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 878"))
                .body("author",equalTo("Jane Smith"))
                .body("price",equalTo(60.5f));

        //cleanup
        given()
                .when()
                .delete("books/878");



    }


}
