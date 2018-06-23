package index1.developer.acadview.com.libraryapp;


import java.io.Serializable;
import java.util.ArrayList;

public class BookObjectModel implements Serializable {

    String category;
    ArrayList<String> book;

    public BookObjectModel(String category, ArrayList<String> book) {
        this.category = category;
        this.book =  book;
    }


    public String getCategory() { return category; }

    public ArrayList<String> getBook() { return book; }

    }
