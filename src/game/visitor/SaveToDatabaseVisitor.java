package game.visitor;

import game.entity.Monster;
import game.entity.Treasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete Visitor: its single operation is preparing data to save the game state to a database.
 * There is no real database: SQL statements are collected instead (simulation).
 */
public class SaveToDatabaseVisitor implements GameVisitor {
    private final List<String> sqlStatements = new ArrayList<>();

    @Override
    public void visitMonster(Monster monster) {
        sqlStatements.add(String.format(
                "INSERT INTO monsters(name, x, y, health, damage) VALUES ('%s', %d, %d, %d, %d);",
                monster.getName(), monster.getX(), monster.getY(),
                monster.getHealth(), monster.getDamage()));
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        sqlStatements.add(String.format(
                "INSERT INTO treasures(title, x, y, gold_value) VALUES ('%s', %d, %d, %d);",
                treasure.getTitle(), treasure.getX(), treasure.getY(), treasure.getGoldValue()));
    }

    public List<String> getSqlStatements() {
        return Collections.unmodifiableList(sqlStatements);
    }
}
