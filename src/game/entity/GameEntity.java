package game.entity;

import game.visitor.GameVisitor;

public interface GameEntity {
    void accept(GameVisitor visitor);
}
