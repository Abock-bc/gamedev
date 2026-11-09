package game.entity;

import game.visitor.GameVisitor;

public class Treasure implements GameEntity {
    private final String title;
    private final int x;
    private final int y;
    private final int goldValue;

    public Treasure(String title, int x, int y, int goldValue) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.goldValue = goldValue;
    }

    @Override
    public void accept(GameVisitor visitor) {
        visitor.visitTreasure(this); // double dispatch
    }

    public String getTitle()   { return title; }
    public int getX()          { return x; }
    public int getY()          { return y; }
    public int getGoldValue()  { return goldValue; }
}
