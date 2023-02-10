package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import res.ResourceLoader;

public class Game {
    private final HashMap<String, HashMap<Integer, GameLevel>> gameLevels;
    private GameLevel gameLevel = null;
    private long startTime;
    private int everyBasket = 0;
    private String playerName;
    
    public Game(){
        startTime = System.currentTimeMillis();
        gameLevels = new HashMap<>();
        readLevels();
    }
    
    public void loadGame(GameID gameID){
        everyBasket = 0;
        startTime = System.currentTimeMillis();
        gameLevel = new GameLevel(gameLevels.get(gameID.difficulty).get(gameID.level));
    }
    
    public void loadGame(int level){
        gameLevel = new GameLevel(gameLevels.get("EASY").get(level));
    }
    
    public void printGameLevel(){
        gameLevel.printLevel();
    }
    
    public boolean step(Direction d){
        if(!playerAlive()) return false;
        return (gameLevel.movePlayer(d) || gameLevel.collectBasekt(d));
    }
    
    public boolean playerAlive(){
        return gameLevel.playerAlive();
    }
    
    public boolean levelDone(){
        return gameLevel.isLevelDone();
    }
    
    public void nearRangers(){
        gameLevel.loseHP();
    }
    
    public void setPlayerName(String s){
        playerName = s;
    }
    
    public ArrayList<Ranger> getRangers(){
        return gameLevel.getRangers();
    }
    
    public void addBaskets(int baskets){
        everyBasket += baskets;
    }
    
    public void moveRangers(){
        gameLevel.moveRanger();
    }
    
    public Collection<String> getDifficulties(){ return gameLevels.keySet(); }
    
    public Collection<Integer> getLevelsOfDifficulty(String difficulty){
        if(!gameLevels.containsKey(difficulty)) return null;
        return gameLevels.get(difficulty).keySet();
    }
    
    public boolean isLevelLoaded(){ return gameLevel != null; }
    public int getLevelRows(){ return gameLevel.rows; }
    public int getLevelCols(){ return gameLevel.cols; }
    public LevelItem getItem(int row, int col){ return gameLevel.level[row][col]; }
    public GameID getGameID(){ return (gameLevel != null) ? gameLevel.gameID : null; }
    public int getLevelBaskets(){ return (gameLevel != null) ? gameLevel.getBaskets() : 0; }
    public int getLevelCollectedBaskets(){ return (gameLevel != null) ? gameLevel.getCollectedBaskets() : 0; }
    public int getNumSteps(){ return (gameLevel != null) ? gameLevel.getNumSteps(): 0; }
    public int getPlayerLife(){ return  gameLevel.getPlayerLife(); }
    public int getEveryBasket(){return everyBasket;}
    
    public int getElapsedTime(){
        return (int)(System.currentTimeMillis() - startTime) / 1000;
    }
    
    public Position getPlayerPos(){
        return new Position(gameLevel.player.x, gameLevel.player.y);
    }
    
    private void readLevels(){
        InputStream is;
        is = ResourceLoader.loadResource("res/levels.txt");
        
        try (Scanner sc = new Scanner(is)){
            String line = readNextLine(sc);
            ArrayList<String> gameLevelRows = new ArrayList<>();
            
            while(!line.isEmpty()){
                GameID id = readGameID(line);
                if(id == null) return;
                
                gameLevelRows.clear();
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameLevelRows.add(line);
                    line = readNextLine(sc);
                }
                addNewGameLevel(new GameLevel(gameLevelRows, id));
            }
        } catch(Exception e){
            System.out.println("Hmm");
        }
    }

    private String readNextLine(Scanner sc) {
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }

    private GameID readGameID(String line) {
        line = line.trim();
        if(line.isEmpty() || line.charAt(0) != ';') return null;
        Scanner s = new Scanner(line);
        s.next();
        if(!s.hasNext()) return null;
        String difficulty = s.next().toUpperCase();
        if(!s.hasNextInt()) return null;
        int id= s.nextInt();
        return new GameID(difficulty, id);
    }

    private void addNewGameLevel(GameLevel gameLevel) {
        HashMap<Integer, GameLevel> levelsOfDifficulty;
        if (gameLevels.containsKey(gameLevel.gameID.difficulty)){
            levelsOfDifficulty = gameLevels.get(gameLevel.gameID.difficulty);
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
        } else {
            levelsOfDifficulty = new HashMap<>();
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
            gameLevels.put(gameLevel.gameID.difficulty, levelsOfDifficulty);
        }
    }

    public void newGame() {
        everyBasket = 0;
        startTime = System.currentTimeMillis();
        loadGame(1);
    }
}
