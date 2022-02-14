import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    int robotcount = 0;
    int currentrobot = 0;
    ArrayList<Robot> table = new ArrayList<Robot>();
    Graphics graphics = new Graphics(table);
    boolean exit = false;
    boolean placedfirst = false;
    Scanner inputScanner = new Scanner(System.in);
    String[] fOptions = {"NORTH", "SOUTH", "EAST", "WEST"};

    System.out.println("Welcome To Robot Table! \n First, you will need to place a robot");

    //Main loop, will run until "EXIT" Command
    while(!exit) {
      System.out.println("\nCOMMANDS: \n - PLACE X,Y, DIRECTION" +
      "\n - LEFT \n - RIGHT \n - MOVE \n - REPORT \n - ACTIVATE ID \n - EXIT");
      graphics.Draw();
      String input = inputScanner.nextLine().toUpperCase();
      Scanner ss = new Scanner(input).useDelimiter("[,\\s]+");
      String command = "Null";
      if(ss.hasNext()) {
        command = ss.next();
      }
      //Ensure there is a robot placed, or being placed, first:
      if(!command.equals("PLACE") && !placedfirst) {
        System.out.println ("First command must be \"PLACE\"");
      } else {
        switch(command) {
          case "PLACE":
            int x;
            int y;
            String dir;
            if(ss.hasNextInt()) {
              x = ss.nextInt();
            } else {
              System.out.println("Incorrect arguments given.");
              break;
            }
            if(ss.hasNextInt()) {
              y = ss.nextInt();
            } else {
              System.out.println("Incorrect arguments given.");
              break;
            }
            if(ss.hasNext()) {
              dir = ss.next();
            } else {
              System.out.println("Incorrect arguments given.");
              break;
            }
            //Check that the place command is valid:
            if(PlacementOk(x, y, dir, fOptions)) {
              Robot robo = new Robot(x, y, robotcount, dir);
              table.add(robo);
              currentrobot = robotcount++;
              placedfirst = true;
            }
            break;
          case "LEFT":
            table.get(currentrobot).Left();
            break;
          case "RIGHT":
            table.get(currentrobot).Right();
            break;
          case "MOVE":
            table.get(currentrobot).Move();
            break;
          case "REPORT":
            System.out.println("Active robot: " + currentrobot);
            System.out.println("Robots on table: " + table.size());
            table.get(currentrobot).Report(); //TODO
            break;
          case "ACTIVATE":
            int selectedid;
            if(ss.hasNextInt()) {
              selectedid = ss.nextInt();
            } else {
              System.out.println("'ACTIVATE' must be followed by a number.");
              break;
            }
            if(selectedid < 0 || selectedid > table.size()) {
              System.out.println("Not a valid robot ID");
              break;
            } else {
                currentrobot = selectedid;
            }
            break;
          case "EXIT":
            exit = true;
          default:
            System.out.println("INVALID COMMAND");
        }
      }
    }
    System.out.println("Thank you for using Robot Table.");
  }

  //Checks if the place command is appropriate:
  public static boolean PlacementOk(int x, int y, String dir, String[] options) {
    //Checks the coordinates are on the table:
    if(x > 4 || x < 0 || y > 4 || y < 0) {
      System.out.println("That location is not on the table :(");
      return false;
    }
    //Checks that the direction given is valid:
    for(String str : options) {
      if(dir.equals(str)) {
        return true;
      }
    }
    System.out.println("That is not a valid direction.");
    return false;
  }
}
