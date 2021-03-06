package Assign_4;


import BasicIO.*;                // for IO classes
//import static BasicIO.Formats.*; // for field formats
import Collections.*;
//import static java.lang.Math.*;  // for math constants and functions


/** This class ...
  *
  * @author Dhairya Jaiswal
  * @version 1.0 (2018/03/19)                                                        */


public class Maze {
  
  private ASCIIDisplayer display;
  private ASCIIDisplayer display1;
  private ASCIIDataFile  file;
  private char[][] maze;
  
  /** This constructor ...                                                     */
  
  public Maze ( ) {
    
    file = new ASCIIDataFile();
    
    int x = file.readInt();
    int y = file.readInt();
    
    display = new ASCIIDisplayer(x+1,y+1);
    
    maze = new char[x+1][y+1];
    
    loadMazeArray(x,y+1);
    loadMazeDisplay(x,y+1);
    
    int xStart = file.readInt();
    int yStart = file.readInt();
    char c = maze[xStart][yStart];
    
    boolean ans = findPath(xStart,yStart,c);
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
  
  private boolean findPath ( int x, int y, char c ) {
    
    LnkStack<Position> s = new LnkStack<Position>();
    
    Position p;
    p = new Position (x,y,c,0);
    
    int xpos, ypos, ch, st;
    st = 0;
    
    s.push(p);
    
    while ( s.top() != null ) {
      ch = s.top().getC();
      st = s.top().getS();
      xpos = s.top().getX();
      ypos = s.top().getY();
      if ( s.top() == p ) {
        st += 1;
      }
      if ( ch == 'E' ) break;
      if ( st != 4 ) {
        if ( maze[xpos+1][ypos] == ' ' || maze[xpos+1][ypos] == 'E' ) {
          Position newP = new Position (xpos+1,ypos,maze[xpos+1][ypos],0);
          maze[xpos][ypos] = '*';
          s.push(newP);
        }
        else if ( maze[xpos-1][ypos] == ' ' || maze[xpos-1][ypos] == 'E' ) {
          Position newP = new Position (xpos-1,ypos,maze[xpos-1][ypos],0);
          maze[xpos][ypos] = '*';
          s.push(newP);
        }
        else if ( maze[xpos][ypos+1] == ' ' || maze[xpos][ypos+1] == 'E' ) {
          Position newP = new Position (xpos,ypos+1,maze[xpos][ypos+1],0);
          maze[xpos][ypos] = '*';
          s.push(newP);
        }
        else if ( maze[xpos][ypos-1] == ' ' || maze[xpos][ypos-1] == 'E' ) {
          Position newP = new Position (xpos,ypos-1,maze[xpos][ypos-1],0);
          maze[xpos][ypos] = '*';
          s.push(newP);
        }
        else {
          maze[xpos][ypos] = '.';
          if ( s.top() != p ) {
            s.pop();
          }
          else {
            break;
          }
        }
      }
    }
    
    if (s.top() == p) {
      return false;
    }
    else {
      return true;
    }
    
  }
  
  
  
  public static void main ( String[] args ) { Maze c = new Maze(); };
  
  
} // <className>