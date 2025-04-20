import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.ActionEvent;

class GamePanel extends JPanel implements KeyListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private final int BALL_SIZE = 13;

    boolean wPressed = false;
    boolean sPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;

    class Paddle {
        private final int PADDLE_WIDTH = 8;
        private final int PADDLE_HEIGHT = 70;
        private int positionX;
        private int positionY;

        Paddle(int x, int y) {
            positionX = x;
            positionY = y;
        }
        public int getX() {
            return positionX;
        }
        public int getY() {
            return positionY;
        }
        public int getWidth() {
            return PADDLE_WIDTH;
        }
        public int getHeight() {
            return PADDLE_HEIGHT;
        }      
        public void moveDown() {
            if (positionY + 5 + PADDLE_HEIGHT <= HEIGHT ) 
            positionY += 5;
        }
        public void moveUp() {
            if (positionY - 5 >= 0)
            positionY -= 5;
        }

    }

    GamePanel() {
        setPreferredSize(new Dimension (WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(wPressed) paddleLeft.moveUp();
                if(sPressed) paddleLeft.moveDown();
                if(upPressed) paddleRight.moveUp();
                if(downPressed) paddleRight.moveDown();
                repaint();
            }
        });
        timer.start();
    }

    Paddle paddleLeft = new Paddle(30, 265);
    Paddle paddleRight = new Paddle(770, 265);

    @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code== KeyEvent.VK_W) wPressed = true;
            if (code == KeyEvent.VK_S) sPressed = true;
            if (code == KeyEvent.VK_UP) upPressed = true;
            if (code == KeyEvent.VK_DOWN) downPressed = true;
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            if (code== KeyEvent.VK_W) wPressed = false;
            if (code == KeyEvent.VK_S) sPressed = false;
            if (code == KeyEvent.VK_UP) upPressed = false;
            if (code == KeyEvent.VK_DOWN) downPressed = false;
        }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
        g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
        g.fillOval(WIDTH/2, HEIGHT/2, BALL_SIZE, BALL_SIZE);
    }
}
