package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Game;
import model.LevelItem;
import model.Position;
import model.Ranger;
import res.ResourceLoader;

public class Board extends JPanel{
    private Game game;
    private final Image empty, basket, mountain, ranger1, tree, player;
    private double scale;
    private int scaled_size;
    private final int title_size = 50;

    public Board(Game g) throws IOException{
        game = g;
        scale = 1.0;
        scaled_size = (int)(scale * title_size);
        empty = ResourceLoader.loadImage("res/empty.png");
        basket = ResourceLoader.loadImage("res/basket.png");
        mountain = ResourceLoader.loadImage("res/mountain2.png");
        ranger1 = ResourceLoader.loadImage("res/ranger.png");
        tree = ResourceLoader.loadImage("res/tree.png");
        player = ResourceLoader.loadImage("res/yogi.png");
    }
    
    /**
     * Frissíti a pályát és újrarajzolja.
     * @return 
     */
    public boolean refresh(){
        if(!game.isLevelLoaded()) return false;
        Dimension dim = new Dimension(game.getLevelCols() * scaled_size, game.getLevelRows() * scaled_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    public boolean setScale(double scale){
        this.scale = scale;
        scaled_size = (int)(scale * title_size);
        return refresh();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        if(!game.isLevelLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getLevelCols();
        int h = game.getLevelRows();
        Position p = game.getPlayerPos();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Image img = null;
                LevelItem li = game.getItem(y, x);
                switch(li){
                    case EMPTY: img = empty; break;
                    case MOUNTAIN: img = mountain; break;
                    case TREE: img = tree; break;
                    case BASKET: img = basket; break;
                }
                if(p.x == x && p.y == y) img = player;
                
                ArrayList<Ranger> rangers = game.getRangers();
                
                for(Ranger r : rangers){
                    Position p2 = r.getPosition();
                    if(p2.x == x && p2.y == y) img = ranger1;
                }
                
                if(img == null) continue;
                gr.drawImage(img, x * scaled_size, y * scaled_size, scaled_size, scaled_size, null);
            }
        }
                
    }
}
