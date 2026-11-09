package game.entity;

import game.visitor.GameVisitor;

/** Element: any entity on the map that can be "visited". */
public interface GameEntity {
    void accept(GameVisitor visitor);
}
