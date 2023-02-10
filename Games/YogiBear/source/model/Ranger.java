package model;

public class Ranger {
    private Position position;
    private Direction direction;
    
    public Ranger(Position position, Direction direction){
        this.position = position;
        this.direction = direction;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
    
    public void changeDirection(){
        switch(direction){
            case UP:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.UP;
                break;
            case LEFT:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.LEFT;
                break;
        }
    }
}
