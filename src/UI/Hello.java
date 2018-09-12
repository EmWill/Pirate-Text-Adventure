package UI;

public class Hello {

    public Hello(){
    }

    public void hi(){System.out.println("hi");}

    public void world(){System.out.print("world");}

    public static void main(String[] args) {
        Hello h = new Hello();
        h.hi();
        h.world();
    }
}
