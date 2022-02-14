import java.util.ArrayList;
public class Graphics {

  private ArrayList<Robot> table;

  Graphics(ArrayList<Robot> table) {
    this.table = table;
  }

  public void Draw() {
    System.out.println(" _ _ _ _ _");
    for (int y = 4; y > -1; y--) {
      for (int x = 0; x < 5; x++) {
        //check for robot
        boolean isblank = true;
        String squaretext = "|_";
        for(Robot robo : table) {
          if(robo.GetX() == x && robo.GetY() == y) {
            if(isblank) {
              squaretext = "|"+robo.GetID();
              isblank = false;
            } else {
              squaretext = "|X";
            }
          }
        }
        System.out.print(squaretext);
      }
      System.out.println("|");
    }
  }
}
