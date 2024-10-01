package ru.prostostudia;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.prostostudia.components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static ru.prostostudia.Main.MainType.*;
import static ru.prostostudia.Main.TILE_SIZE;

public class MainFactory implements EntityFactory {
    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder(data)
                .atAnchored(new Point2D(20, 20), new Point2D(20, 20))
                .type(PLAYER)
                .viewWithBBox(new Rectangle(TILE_SIZE, TILE_SIZE, Color.BLUE))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(40, 40, 150))
                .with(new AStarMoveComponent(FXGL.<Main>getAppCast().getGrid()))
                .with(new PlayerComponent())
                .build();
    }
}
