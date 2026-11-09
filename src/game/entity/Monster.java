package game.entity;

import game.visitor.GameVisitor;

/** Concrete Element: a monster. */
public class Monster implements GameEntity {
    private final String name;
    private final int x;
    private final int y;
    private final int health;
    private final int damage;

    public Monster(String name, int x, int y, int health, int damage) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void accept(GameVisitor visitor) {
        visitor.visitMonster(this); // double dispatch: the element type is known right here
    }

    public String getName()  { return name; }
    public int getX()        { return x; }
    public int getY()        { return y; }
    public int getHealth()   { return health; }
    public int getDamage()   { return damage; }
}
