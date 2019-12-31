package root.entities.movable;

import java.awt.Image;
import java.util.Random;

public class LuckyBalloon extends MovableObjects {

  public static final int existLoop = 100;
  private static final int bound = 20;
  private static Random luck = new Random();
  public boolean clicked = false;

  public LuckyBalloon(int x, int y) {
    super(x, y);
    generateMove();
  }

  private void generateMove() {
    dx = luck.nextInt(bound) - bound * 1.0 / 2;
    dy = luck.nextInt(bound) - bound * 1.0 / 2;
  }

  @Override
  public void actions() {
    LoopCounter++;
    position.x += dx;
    position.y += dy;
    if (position.x <= 0 || position.x + getImage().getWidth(null) >= visualMode.GameDim.width) {
      dx *= -1;
      dy = luck.nextInt(bound) - bound * 1.0 / 2;
    } else if (position.y <= 0
        || position.y + getImage().getHeight(null) >= visualMode.GameDim.height) {
      dy *= -1;
      dx = luck.nextInt(bound) - bound * 1.0 / 2;
    }
  }

  public void luckyEffect() {
    int opt = luck.nextInt(3);
    if (opt == 0) {
      System.out.println(opt);
    } else if (opt == 1) {
      System.out.println(opt);
    } else if (opt == 2) {
      System.out.println(opt);
    }

    clicked = true;
  }

  @Override
  public Image getImage() {
    return visualMode.LuckyBalloonImage;
  }
}