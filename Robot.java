public class Robot {

  //An enum that can cycle through directions:
  private enum Direction {
    NORTH {
      @Override
      public Direction previous() {
        return WEST;
      }
    },
    EAST,
    SOUTH,
    WEST {
      @Override
      public Direction next() {
        return NORTH;
      };
    };
    public Direction next() {
      return values()[ordinal() + 1];
    }
    public Direction previous() {
      return values()[ordinal() - 1];
    }
  }

  private int x;
  private int y;
  private Direction dir;
  private int id;

  Robot(int x, int y, int id, String dir) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.dir = Direction.valueOf(dir);
  }

  //Rotate 90* left
  public void Left() {
    dir = dir.previous();
  }

  //Rotate 90* right
  public void Right() {
    dir = dir.next();
  }

  //Attempt to move in the currently faced direction.
  //Will not move if it is higher or lower than maximum.
  public void Move() {
    switch(dir) {
      case NORTH:
        if(y<4){y++;} else {SayNo();}
        break;
      case EAST:
        if(x<4) {x++;} else {SayNo();}
        break;
      case SOUTH:
        if (y>0) {y--;} else {SayNo();}
        break;
      case WEST:
        if(x>0) {x--;} else {SayNo();}
        break;
    }
  }

  private void SayNo() {
    System.out.println("Robot "+ id +": I can't go that way, or I will fall!");
  }

  public void Report() {
    System.out.println("Robot " + id + " is currently at:" +
    "\nNorth " + y +
    "\nEast " + x +
    "\nFacing " + dir.toString());
    if(x == 0 || x == 5 || y == 0 || y == 5) {
      System.out.println("I'm on an edge and will avoid falling off!");
    }
  }

  public Direction GetDirection() {
    return dir;
  }

  public int GetX() {
    return x;
  }

  public int GetY() {
    return y;
  }

  public int GetID() {
    return id;
  }
}
