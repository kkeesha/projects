package model;

public enum LevelItem {
    EMPTY(' '), BASKET('B'), MOUNTAIN('M'), RANGER('R'), TREE('T'), YOGI('Y');
    public final char representation;

    private LevelItem(char representation) {
        this.representation = representation;
    }
    
}
