package model;

public class Chapter implements Literature {
    private String title;
    private String content;

    public Chapter(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Override
    public void displayContents(boolean first
    ) {
        System.out.println(title);

    }


}
