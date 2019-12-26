package root.GameMode.Levels;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import root.GUI.GamePanel;
import root.GUI.Decorator;
import root.GameMode.Visuals.VisualMode;
import root.entities.GameEntity;
import root.entities.plants.Lawnmower;
import root.entities.plants.Plant;
import root.entities.stuffs.MoveableEntity;
import root.entities.zombies.Zombie;
import root.etc.CellsManager;

public abstract class Level {

  // Add new rounds to this
  public static LinkedList<Class<? extends Level>> rounds = new LinkedList<>(List.of(Level.class, Round1.class, Round2.class));
  public ArrayList<MoveableEntity> moveableEntities = new ArrayList<>();
  public ArrayList<Zombie> zombies = new ArrayList<>();
  public ArrayList<Class<? extends Plant>> plantClasses = new ArrayList<>();
  public ArrayList<Class<? extends Zombie>> zombieClasses = new ArrayList<>();
  public ArrayList<Decorator> cards = new ArrayList<>(5);
  public ArrayList<Lawnmower> lawnmowers = new ArrayList<>(5);
  public int MAX_ZOMBIES;
  public int zombieCount = 0;

  public Level(GamePanel gamePanel) {
    CellsManager.init(gamePanel.visualMode);
    GameEntity.setGamePanel(gamePanel);
    GameEntity.setVisualMode();
  }

  public static Level getRound(int levelNumber, GamePanel gamePanel) {
    if (levelNumber < rounds.size()) {
      try {
        return rounds.get(levelNumber).getConstructor(GamePanel.class).newInstance(gamePanel);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public boolean end() {
    return zombieCount >= MAX_ZOMBIES;
  }

  public void addZombie(VisualMode visualMode) {
    try {
      int index = (new Random()).nextInt(zombieClasses.size());
      zombies.add(zombieClasses.get(index).getConstructor(int.class, int.class)
          .newInstance(visualMode.GameDim.width + 10, CellsManager.lanes[new Random().nextInt(CellsManager.lanes.length)]));
      zombieCount++;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
}
