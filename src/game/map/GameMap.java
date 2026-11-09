package game.map;

import game.entity.GameEntity;
import game.visitor.GameVisitor;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final List<GameEntity> entities = new ArrayList<>();

    public void add(GameEntity entity) {
        entities.add(entity);
    }

    public void accept(GameVisitor visitor) {
        for (GameEntity entity : entities) {
            entity.accept(visitor);
        }
    }
}
