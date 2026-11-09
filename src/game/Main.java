package game;

import game.entity.Monster;
import game.entity.Player;
import game.entity.Treasure;
import game.map.GameMap;
import game.visitor.CollisionDetectionVisitor;
import game.visitor.SaveToDatabaseVisitor;

public class Main {
    public static void main(String[] args) {
        GameMap map = new GameMap();
        map.add(new Monster("Orc", 3, 4, 100, 15));
        map.add(new Monster("Skeleton", 7, 1, 40, 5));
        map.add(new Treasure("Golden Crown", 3, 4, 500));
        map.add(new Treasure("Old Chest", 9, 9, 120));

        Player player = new Player("Hero", 3, 4);

        System.out.println("Collision detection");
        CollisionDetectionVisitor collisionVisitor = new CollisionDetectionVisitor(player);
        map.accept(collisionVisitor);
        collisionVisitor.getCollisions().forEach(System.out::println);

        System.out.println("\n Save to database ");
        SaveToDatabaseVisitor saveVisitor = new SaveToDatabaseVisitor();
        map.accept(saveVisitor);
        saveVisitor.getSqlStatements().forEach(System.out::println);
    }
}
