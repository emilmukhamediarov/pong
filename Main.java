import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            System.out.println("Launching..");
            MyFrame myframe = new MyFrame();
            
        });
    }

}