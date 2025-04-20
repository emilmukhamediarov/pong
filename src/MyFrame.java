import javax.swing.*;

class MyFrame extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    MyFrame() {
        super("Pong");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        
        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setVisible(true);

        panel.requestFocusInWindow();
    }

}