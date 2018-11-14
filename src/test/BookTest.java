package test;

import model.Book;
import model.Chapter;
import model.Literature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookTest {
    private Book book;
    private Book section1;
    private Book section2;
    private Chapter boringPart;
    private Chapter coolPart;

    @BeforeEach
    public void initialize(){
        book = new Book("History", "blah");
        section1 = new Book("Anime", "");
        section2 = new Book("Real Stuff", "");
        boringPart = new Chapter("Everything about envelopes", "");
        coolPart = new Chapter("The truth about 50 years ago", "");
        book.addLiterature(section1);
        book.addLiterature(section2);
        section1.addLiterature(coolPart);
        section2.addLiterature(boringPart);


    }

    @Test
    public void displayContents(){
        book.displayContents(true);
    }


}
