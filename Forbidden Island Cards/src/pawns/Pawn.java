package pawns;

import java.awt.Point;

import enums.TilesEnums;

abstract public class Pawn {

    protected Point location;

    protected Pawn() {
	}
    abstract public String getPlayerType();

    abstract public TilesEnums startLoc();

    public void setLoc(Point p) {
        this.location = new Point(p);
    }

    public Point getLoc() {
        return location;
    }
    
    public void shoreUp() {
        System.out.println("Some day I'll get around to shoring up");

    }

    public void move() {
        System.out.println("Some day I'll get around to moving..");

    }

    public void helicopterMove() {
        System.out.println("Some day I'll get around to flying..");

    }

}
