package Assign_4;


import BasicIO.*;                // for IO classes


/** This class ...
  *
  * @author Dhairya Jaiswal
  * @version 1.0 (2018/03/19)                                                        */


public class MazeB {
  
  private ASCIIDisplayer display;
  private ASCIIDisplayer display1;
  private ASCIIDataFile  file;
  private char[][] maze;
  
  /** This constructor ...                                                     */
  
  public MazeB ( ) {
    
    file = new ASCIIDataFile();
    
    int x = file.readInt();
    int y = file.readInt();
    
    display = new ASCIIDisplayer(x+1,y+1);
    
    maze = new char[x+1][y+1];
    
    loadMazeArray(x,y+1);
    loadMazeDisplay(x,y+1);
    
    int xStart = file.readInt();
    int yStart = file.readInt();
    
    boolean ans = findPath(xStart,yStart);
    if (ans) System.out.println("TRUE");
    
    display1 = new ASCIIDisplayer(x+1,y+1);
    loadMazeDisplay2(x,y+1);
    
  }; // constructor
  
  
  private void loadMazeArray ( int x, int y ) {
    for (int i=0; i<x; i++) {
      for (int j=0; j<y; j++) {
        maze[i][j] = file.readC();
      }
    }
  }
  
  private void loadMazeDisplay ( int x, int y ) {
    for (int i=0; i<x; i++) {
      for (int j=0; j<y; j++) {
        display.writeC(maze[i][j]);
      }
    }
  }
  
  private void loadMazeDisplay2 ( int x, int y ) {
    for (int i=0; i<x; i++) {
      for (int j=0; j<y; j++) {
        display1.writeC(maze[i][j]);
      }
    }
  }
  
  private boolean findPath ( int x, int y ) {
    if (maze[x][y] == ' ') {
      maze[x][y] = '*';
      return findPath(x,y);
    } else if (maze[x+1][y] == ' ') {  //Checking south side
      
      return findPath(x+1,y);
    } else if (maze[x][y+1] == ' ') {  //Checking East Side
      
      return findPath(x,y+1);
      
    } else if (maze[x-1][y] == ' ') {  //Checking North Side
      
      return findPath(x-1,y);
      
    } else if (maze[x][y-1] == ' ') {  //Checking west side
      
      return findPath(x,y-1);  
    } else if (maze[x+1][y] == 'E' || maze[x][y+1] == 'E' || maze[x-1][y] == 'E' || maze[x][y-1] == 'E') {
      return true;  //Once you reach the end   
    } 
    
    //BackTracking
    if (maze[x][y] == '*') {
      maze[x][y] = '.';
      return findPath(x,y);
      
    } else if (maze[x-1][y] == '*') {  //Checking North Side
      return findPath(x-1,y);
    } else if (maze[x][y+1] == '*') {  //Checking East Side
      return findPath(x,y+1); 
    } else if (maze[x][y-1] == '*') {  //Checking west side
      return findPath(x,y-1); 
    } else if (maze[x+1][y] == '*') {  //Checking south side
      return findPath(x+1,y); 
    }
    return false;
  }
  
  
  
  public static void main ( String[] args ) { MazeB c = new MazeB(); };
  
  
} // <className>