package model;

import java.util.ArrayList;

public class GameLevel {

    public final GameID gameID;
    public final int rows, cols;
    public final LevelItem[][] level;
    public Position player = new Position(0, 0);
    public Position og = new Position(0, 0);
    public ArrayList<Ranger> ranger1 = new ArrayList<>();
    private int numSteps, baskets, collectedBaskets, playerLife = 3;
    
    
    

    public GameLevel(ArrayList<String> gameLevelRows, GameID gameID) {
        this.gameID = gameID;
        int c = 0;
        for (String s : gameLevelRows) {
            if (s.length() > c) {
                c = s.length();
            }
        }
        rows = gameLevelRows.size();
        cols = c;
        level = new LevelItem[rows][cols];
        numSteps = 0;
        baskets = 0;

        for (int i = 0; i < rows; ++i) {
            String s = gameLevelRows.get(i);
            for (int j = 0; j < s.length(); ++j) {
                switch (s.charAt(j)) {
                    case 'Y':
                        player = new Position(j, i);
                        level[i][j] = LevelItem.EMPTY;
                        break;
                    case ' ':
                        level[i][j] = LevelItem.EMPTY;
                        break;
                    case 'B':
                        level[i][j] = LevelItem.BASKET;
                        baskets++;
                        break;
                    case 'M':
                        level[i][j] = LevelItem.MOUNTAIN;
                        break;
                    case 'T':
                        level[i][j] = LevelItem.TREE;
                        break;
                    case 'R':
                        level[i][j] = LevelItem.EMPTY;
                        ranger1.add(new Ranger(new Position(j, i), Direction.RIGHT));
                        break;
                    case 'r':
                        level[i][j] = LevelItem.EMPTY;
                        ranger1.add(new Ranger(new Position(j, i), Direction.UP));
                        break;
                    default:
                        level[i][j] = LevelItem.EMPTY;
                        break;
                }
            }
            for (int j = s.length(); j < cols; ++j) {
                level[i][j] = LevelItem.EMPTY;
            }
        }
    }

    public GameLevel(GameLevel gl) {
        gameID = gl.gameID;
        rows = gl.rows;
        cols = gl.cols;
        numSteps = gl.numSteps;
        baskets = gl.baskets;
        collectedBaskets = gl.collectedBaskets;
        playerLife = gl.playerLife;
        level = new LevelItem[rows][cols];
        player = new Position(gl.player.x, gl.player.y);
        og = new Position(gl.player.x, gl.player.y);
        ranger1 = gl.ranger1;
        
        for (int i = 0; i < rows; i++) {
            System.arraycopy(gl.level[i], 0, level[i], 0, cols);
        }
    }

    public boolean isValidPosition(Position p) {
        return (p.x >= 0 && p.y >= 0 && p.x < cols && p.y < rows);
    }

    public boolean isFree(Position p) {
        if (!isValidPosition(p)) {
            return false;
        }
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.EMPTY);
    }

    private boolean isRanger(Position p) {
        for(Ranger r : ranger1){
            if(r.getPosition().x == p.x && r.getPosition().y == p.y) return true;
        }
        return false;
    }

    /**
     * Ha a meghívjuk a metódust a játékos pozíciójához viszonítva
     * a szomszédos mezőket megvizsgáljuk, hogy van e rajta Ranger
     * Ha van akkor csökkentjük a játékos életét ha > 0, 
     * majd a jáékost visszaállítjuk az eredeti poziciójára
     * @return 
     */
    public boolean loseHP() {
        Position p = player;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                Position tp = new Position(p.x + x, p.y + y);
                if (isRanger(tp)) {
                    if(playerLife > 0) playerLife--;
                    player = og;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBasket(Position p) {
        if (!isValidPosition(p)) {
            return false;
        }
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.BASKET);
    }

    public boolean isLevelDone(){
        return collectedBaskets == baskets;
    }
    
    /**
     * Megnézzük hogy van e ranger a közelben, ha nincs és
     * a szint még nincs kész és a d irány amerre lépni akar a játékos szabad
     * akkor oda állítjuk a játékost, növeljük a lépésszámot.
     * @param d
     * @return 
     */
    public boolean movePlayer(Direction d) {
        Position curr = player;
        Position next = curr.translate(d);
        if (loseHP()) {
            return false;
        }
        if (!isLevelDone() && isFree(next)) {
            player = next;
            numSteps++;
            if (loseHP()) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * A a ranger ArrayListből az összes rangert mozgatjuk, a rangereknek eltárolt
     * írányba, ha nem szabad a követkető mező akkor visszafordulnak.
     */
    public void moveRanger(){
        for(Ranger r1 : ranger1){
            Position next = r1.getPosition().translate(r1.getDirection());
            if(isFree(next)){
                r1.setPosition(next);
            } else {
                r1.changeDirection();
                next = r1.getPosition().translate(r1.getDirection());
                r1.setPosition(next);
            }
        }
    }
    
    /**
     * Ha a játékos egy kosárra akar lépni  és még nincs kész a szint
     * akkor a játékost ráállítjuk a mezőre, azt a mezőt kicseréljük egy üresre
     * és ha a közelben van egy ranger akkor meghívjuk a liseHP() metódust
     * @param d
     * @return 
     */
    public boolean collectBasekt(Direction d) {
        Position curr = player;
        Position next = curr.translate(d);
        if (!isLevelDone() && isBasket(next)) {
            player = next;
            collectedBaskets++;
            level[next.y][next.x] = LevelItem.EMPTY;
            numSteps++;
            loseHP();
            return true;
        }
        return false;
    }

    public void printLevel() {
        int x = player.x, y = player.y;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == y && j == x) {
                    System.out.print("Y");
                } else {
                    System.out.print(level[i][j].representation);
                }
            }
            System.out.println("");
        }
    }

    public int getNumSteps() {
        return numSteps;
    }

    public int getBaskets() {
        return baskets;
    }

    public int getCollectedBaskets() {
        return collectedBaskets;
    }

    public int getPlayerLife() {
        return playerLife;
    }
    
    public boolean playerAlive(){
        return playerLife >= 1;
    }
    
    public ArrayList<Ranger> getRangers(){
        return ranger1;
    }
}
