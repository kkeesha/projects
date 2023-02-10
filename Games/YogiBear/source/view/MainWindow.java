package view;

import db.HighScores;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import model.Direction;
import model.Game;
import model.GameID;

public class MainWindow extends JFrame {

    private final Game game;
    private Board board;
    private final JLabel gameStatLabel;
    private final int FPS = 1;
    private Timer newFrameTimer;
    private int level = 1;
    private String playerName;
    private HighScores highScores;

    public MainWindow() throws IOException {
        game = new Game();

        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();

        setTitle("Yogi Bear");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("res/yogi.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuGameLevel = new JMenu("Level");
        JMenu menuGameScale = new JMenu("Zoom");
        createGameLevelMenuItems(menuGameLevel);
        createScaleMenuItems(menuGameScale, 1.0, 2.0, 0.5);

        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("High Scores") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HighScores highScores = new HighScores(10);
                    new HighScoreWindow(highScores.getHighScores(), MainWindow.this);
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuGame.add(menuGameLevel);
        menuGame.add(menuGameScale);
        menuGame.add(menuHighScores);
        menuGame.addSeparator();
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);

        setLayout(new BorderLayout(0, 10));
        gameStatLabel = new JLabel("");
        add(gameStatLabel, BorderLayout.SOUTH);
        try {
            add(board = new Board(game), BorderLayout.CENTER);
        } catch (IOException e) {
        }

        /**
         * A W-A-S-D-ESC gombok lenyomását figyelő KeyListener.
         * A gomboknak megfelelő irányokkal hívja meg a játékos lépéséért felelős
         * metódust. Az ESC- re kilép a programból.
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke);
                if (!game.isLevelLoaded()) {
                    return;
                }
                int kk = ke.getKeyCode();
                Direction d = null;
                switch (kk) {
                    case KeyEvent.VK_W:
                        d = Direction.UP;
                        break;
                    case KeyEvent.VK_A:
                        d = Direction.LEFT;
                        break;
                    case KeyEvent.VK_S:
                        d = Direction.DOWN;
                        break;
                    case KeyEvent.VK_D:
                        d = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                }

                refreshGameStatLabel();
                board.repaint();
                if (game.step(d)) {
                    refreshGameStatLabel();
                    if (game.levelDone()) {
                        JOptionPane.showMessageDialog(MainWindow.this, "Level complete!", "GZ" , JOptionPane.INFORMATION_MESSAGE);
                        game.addBaskets(game.getLevelCollectedBaskets());

                        if (level == game.getLevelsOfDifficulty("EASY").size()) {
                            InputDialog("You won!");
                            startNewGame();
                        }

                        if (level < game.getLevelsOfDifficulty("EASY").size()) {
                            game.loadGame(++level);
                            board.refresh();
                            refreshGameStatLabel();
                            pack();
                        } else {
                            startNewGame();
                        }

                    }
                    //if()
                }
                if (!game.playerAlive()) {
                    InputDialog("Game Over!");
                    startNewGame();
                } else {
                    refreshGameStatLabel();
                    board.repaint();
                }
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        game.loadGame(new GameID("EASY", level));
        board.refresh();
        pack();
        refreshGameStatLabel();
        setVisible(true);
    }

    /**
     * Bekérjük a játkos nevét ha elfogy az élete, vagy végigment az összes szinten
     * és az adatbázisba elentjük a nevét és az elért pontszámot.
     * @param s 
     */
    private void InputDialog(String s) {
        JFrame frame = new JFrame(s);
        frame.setTitle(s);
        playerName = JOptionPane.showInputDialog(frame, s + " What's your name?");
        try {
            highScores = new HighScores(10);
            highScores.putHighScore(playerName, game.getEveryBasket());
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Új játkot indítunk az első szintről.
     */
    private void startNewGame() {
        level = 1;
        game.newGame();
        board.refresh();
        refreshGameStatLabel();
        pack();
    }

    /**
     * Frissítjük a játék eredmény mezőjét.
     */
    private void refreshGameStatLabel() {
        String s = "";
        s += "Player life: " + game.getPlayerLife();
        s += "  |  Collected baskets: " + game.getLevelCollectedBaskets();
        s += "  |  Elapsed time: " + game.getElapsedTime();
        s += "  |  Every basket: " + game.getEveryBasket();
        gameStatLabel.setText(s);
    }

    /**
     * Menübe hozzáadjuk a meglévő szinteket.
     * @param menu 
     */
    private void createGameLevelMenuItems(JMenu menu) {
        for (String s : game.getDifficulties()) {
            JMenu difficultyMenu = new JMenu(s);
            menu.add(difficultyMenu);
            for (Integer i : game.getLevelsOfDifficulty(s)) {
                JMenuItem item = new JMenuItem(new AbstractAction("Level-" + i) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        level = i;
                        game.loadGame(new GameID(s, i));
                        board.refresh();
                        pack();
                    }
                });
                difficultyMenu.add(item);
            }
        }
    }

    /**
     * Játék képernyőjének a nagítása
     * @param menu
     * @param from
     * @param to
     * @param by 
     */
    private void createScaleMenuItems(JMenu menu, double from, double to, double by) {
        while (from <= to) {
            final double scale = from;
            JMenuItem item = new JMenuItem(new AbstractAction(from + "x") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.setScale(scale)) {
                        pack();
                    }
                }
            });
            menu.add(item);
            if (from == to) {
                break;
            }
            from += by;
            if (from > to) {
                from = to;
            }
        }
    }

    /**
     * A rangerek mozgásáért felelős osztály amit minden másodpercben meghívunk.
     */
    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.moveRangers();
            game.nearRangers();
            refreshGameStatLabel();
            repaint();
        }

    }

    // MAIN
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (IOException e) {
        }
    }

}
