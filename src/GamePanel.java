import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.ActionEvent;

class GamePanel extends JPanel implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

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

    class Ball {
        public  final int BALL_SIZE = 13;
        private int x = GamePanel.WIDTH / 2;
        private int y = GamePanel.HEIGHT / 2;
        
        private int velocityX = 3;
        private int velocityY = 2;


        Ball() {}

        public void move() {
            x += velocityX;
            y += velocityY;
            if (hitTop() || hitBottom()) velocityY *= -1;
            if (collideWith(paddleLeft) || collideWith(paddleRight)) velocityX *= -1;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public boolean hitTop() {
            return y <= 0;
        }
        public boolean hitBottom() {
            return y + BALL_SIZE >= GamePanel.HEIGHT;
        }
        public boolean collideWith(Paddle p) {
            int ballLeft = x;
            int ballRight = x + BALL_SIZE;
            int ballTop = y;
            int ballBottom = y + BALL_SIZE;

            int paddleLeft = p.getX();
            int paddleRight = p.getX() + p.getWidth();
            int paddleTop = p.getY();
            int paddleBottom = p.getY() + p.getHeight();

            // Axis-Aligned Bounding Box (AABB) collision detection
            return ballRight >= paddleLeft &&
                ballLeft <= paddleRight &&
                ballBottom >= paddleTop &&
                ballTop <= paddleBottom;
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
                ball.move();
                repaint();
            }
        });
        timer.start();
    }

    Paddle paddleLeft = new Paddle(30, 265);
    Paddle paddleRight = new Paddle(770, 265);
    Ball ball = new Ball();

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
        g.fillOval(ball.getX(), ball.getY(), ball.BALL_SIZE, ball.BALL_SIZE);
    }
}
