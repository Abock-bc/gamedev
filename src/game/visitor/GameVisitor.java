package game.visitor;

import game.entity.Monster;
import game.entity.Treasure;

/** Visitor: one visit method per concrete element type. */
public interface GameVisitor {
    void visitMonster(Monster monster);
    void visitTreasure(Treasure treasure);
}
