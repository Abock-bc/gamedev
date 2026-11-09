package game.visitor;

import game.entity.Monster;
import game.entity.Player;
import game.entity.Treasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollisionDetectionVisitor implements GameVisitor {
    private final Player player;
    private final List<String> collisions = new ArrayList<>();

    public CollisionDetectionVisitor(Player player) {
        this.player = player;
    }

    @Override
    public void visitMonster(Monster monster) {
        if (isSamePosition(monster.getX(), monster.getY())) {
            collisions.add(player.getName() + " collided with monster " + monster.getName()
                    + " and took " + monster.getDamage() + " damage");
        }
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        if (isSamePosition(treasure.getX(), treasure.getY())) {
            collisions.add(player.getName() + " found treasure \"" + treasure.getTitle()
                    + "\" (+" + treasure.getGoldValue() + " gold)");
        }
    }

    public List<String> getCollisions() {
        return Collections.unmodifiableList(collisions);
    }

    private boolean isSamePosition(int x, int y) {
        return player.getX() == x && player.getY() == y;
    }
}
