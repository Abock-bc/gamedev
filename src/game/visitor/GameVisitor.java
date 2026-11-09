package game.visitor;

import game.entity.Monster;
import game.entity.Treasure;

public interface GameVisitor {
    void visitMonster(Monster monster);
    void visitTreasure(Treasure treasure);
}
