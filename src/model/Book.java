package model;

import java.util.ArrayList;
import java.util.List;

public class Book implements Item, Literature   {
    private String title;
    private String description;
    private List<Literature> contents;

    public Book (String title, String description){
        this.title = title;
        this.description = description;
        this.contents = new ArrayList<Literature>();
    }

    public void addLiterature(Literature lit){
        contents.add(lit);
    }

    public void removeLiterature(Literature lit){
        contents.remove(lit);
    }


    @Override
    public void displayContents(boolean first) {

        System.out.println(title + " contains:");
        if (first){
        System.out.println("____");}
        for (Literature lit:contents
             ) {lit.displayContents(false);
        }
        if(!first){
        System.out.println("____");}
    }

    @Override
    public String examine() {
        return description;
    }

    @Override
    public boolean obtainable() {
        return true;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public void use() {
displayContents(true);
    }

    @Override
    public boolean canEquip() {
        return false;
    }

}
