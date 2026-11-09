# Visitor Pattern — Game Objects and Entities (Java)

Assignment #6, Software Design Patterns. Topic: **Game development — game objects and entities**.

## Structure

```
src/game
├── Main.java                              # client
├── entity
│   ├── GameEntity.java                    # Element: accept(GameVisitor)
│   ├── Monster.java                       # Concrete Element
│   ├── Treasure.java                      # Concrete Element
│   └── Player.java                        # context for collision checks
├── visitor
│   ├── GameVisitor.java                   # Visitor: visitMonster / visitTreasure
│   ├── CollisionDetectionVisitor.java     # Concrete Visitor #1
│   └── SaveToDatabaseVisitor.java         # Concrete Visitor #2
└── map
    └── GameMap.java                       # Object Structure
```

## Mapping to assignment requirements

| Component | Implementation |
| --- | --- |
| Element Interface | `GameEntity.accept(GameVisitor)` |
| Concrete Elements (>=2) | `Monster`, `Treasure` — call `visitor.visitMonster(this)` / `visitor.visitTreasure(this)` |
| Visitor Interface | `GameVisitor` — one `visitX()` per element type |
| Concrete Visitors (>=2) | `CollisionDetectionVisitor`, `SaveToDatabaseVisitor` |
| Object Structure | `GameMap` — holds a list of entities and calls `accept()` on each |

## Double dispatch

`GameMap.accept(visitor)` calls `entity.accept(visitor)` — the first dispatch, on the runtime type
of the element (`Monster` or `Treasure`). Inside `accept()`, the element calls
`visitor.visitMonster(this)` — the second dispatch, on the runtime type of the visitor.
The result: the executed method depends on the pair (element type, visitor type), with no `instanceof`.

## Clean Code: principles and justification

1. **Meaningful names.** Elements: `GameEntity`, `Monster`, `Treasure`; visitors end with `Visitor`
   and are named after their operation. `visitMonster` / `visitTreasure` state exactly who is being visited.
2. **No `instanceof` or type checks.** Double dispatch selects the algorithm.
3. **Single Responsibility.** `CollisionDetectionVisitor` only handles collisions,
   `SaveToDatabaseVisitor` only prepares data for the database. Elements hold data and know nothing about these operations.
4. **Open/Closed — extensibility.** A new operation (e.g. `RenderVisitor` or `AiBehaviorVisitor`)
   is just a new class implementing `GameVisitor`. `Monster`, `Treasure` and `GameMap` stay unchanged.
5. **Encapsulation: a deliberate trade-off.** Visitors need element data, so elements expose only
   read-only getters. All fields are `final`, there are no setters and no internal collections are exposed,
   so a visitor cannot modify element state.
6. **Encapsulated visitor results.** Result lists are returned via
   `Collections.unmodifiableList`, protecting the visitor's internal state.
7. **Small methods, no duplication.** The position check is extracted into `isSamePosition`,
   and each method does one thing.
8. **Immutability.** Fields of elements and `Player` are `final`.

## Pattern limitation

Adding a new element type (e.g. `Trap`) requires adding `visitTrap()` to `GameVisitor`
and to every visitor. Visitor fits best when the set of element types is stable and operations are added often.

## Build and run

Requires JDK 17+.

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out game.Main
```

## Expected output

```
=== Collision detection ===
Hero collided with monster Orc and took 15 damage
Hero found treasure "Golden Crown" (+500 gold)

=== Save to database ===
INSERT INTO monsters(name, x, y, health, damage) VALUES ('Orc', 3, 4, 100, 15);
INSERT INTO monsters(name, x, y, health, damage) VALUES ('Skeleton', 7, 1, 40, 5);
INSERT INTO treasures(title, x, y, gold_value) VALUES ('Golden Crown', 3, 4, 500);
INSERT INTO treasures(title, x, y, gold_value) VALUES ('Old Chest', 9, 9, 120);
```
