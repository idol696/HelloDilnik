package ru.prostostudia;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import javafx.scene.input.KeyCode;
import ru.prostostudia.components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ru.prostostudia.Main.MainType.*;

public class Main extends GameApplication {

    public static final int TILE_SIZE = 40;

    public enum MainType {
        PLAYER, WALL, BRICK, ENEMY
    }


    private AStarGrid grid;

    private PlayerComponent playerComponent;

    public AStarGrid getGrid() {
        return grid;
    }

    @Override
    protected void initInput() {

        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveUp();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveLeft();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveDown();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveRight();
            }
        }, KeyCode.D);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new MainFactory());
        grid = AStarGrid.fromWorld(getGameWorld(), 15, 15, 40, 40, type -> {
            if (type.equals(WALL) || type.equals(BRICK))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        Entity player = spawn("Player");
        playerComponent = player.getComponent(PlayerComponent.class);
    }

    public static void main(String[] args) {

        double a = 0.34;
        double b = 3;
        double c = a + b - 100;
        double r = Math.abs(c);
        System.out.println("Hello world! " + r);

        launch(args);


    }
}