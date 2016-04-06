package checkersgame;

import java.io.IOException;
import java.util.*;
import static java.lang.Math.*;

public class Rules {

       private static final String DCB[][]  = {
         {"[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]"},
         {"[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]"},
         {"[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]"},
         {"[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]"},
         {"[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]"},
         {"[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]"},
         {"[-]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]"},
         {"[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]"},
         };
       
       private static String[][] CB = new String[8][8];
       private static boolean pieceTaken;
       private static int redCount = 12;
       private static int blackCount = 12;
       private static char turn = 'r';
       private static int xChange, yChange;
       private static String moveOrder;
       private static int numMoves;
       private static int firstRun;
       

    public Rules(){
        firstRun = 0;
        intCB();
    }
    private static void intCB()
    {
        for (int i = 0; i < 8; i++) 
            System.arraycopy(DCB[i], 0, CB[i], 0, 8);
    }
     
     public static boolean isLegal(int piece[], int toSpace[])
     {
       boolean leftRight;
       boolean legal;
       String jumpedPiece;
      
       if (firstRun == 0) {
           firstRun = 666;
           intCB();
       }
       legal = false;
       xChange = (toSpace[0])- (piece[0]);
       yChange = (toSpace[1])-(piece[1]);
       
         if (CB[piece[0]][piece[1]].equals("[r]") && turn == 'r') {
            
              if (CB[toSpace[0]][toSpace[1]].equals("[_]") && xChange == -1) {
                legal = (xChange == -1);
                leftRight =  (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
              jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
               if (jumpedPiece.equals("[b]")|| jumpedPiece.equals("[B]")) {
               legal = (xChange == -2);
               leftRight =(abs(yChange) == 2);
                    if (leftRight== true && legal == true)  {
                        legal = true;
                        pieceTaken = true;
                        blackCount--;
                    } else legal = false;  
              } 
        }
        
          if (CB[piece[0]][piece[1]].equals("[R]") && turn == 'r') {
           if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
                legal = (abs(xChange) == 1);
                leftRight = (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[b]")|| jumpedPiece.equals("[B]")) {
                legal =  (abs(xChange) == 2);
                leftRight = (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                } else legal = false;
              } 
        }
          
          if (CB[piece[0]][piece[1]].equals("[b]") && turn == 'b')  {
            if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
                legal = (xChange == 1);
                leftRight = (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[r]")|| jumpedPiece.equals("[R]")) {
                legal =  (xChange == 2);
                leftRight = (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                }
                else legal = false;
              }     
        }
        
        if (CB[piece[0]][piece[1]].equals("[B]") && turn == 'b')
        {
            if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
                legal = (abs(xChange) == 1);
                leftRight = (abs(yChange) == 1);
                legal =  (leftRight == true && legal == true);  
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[r]") || jumpedPiece.equals("[R]")) {
                legal =  (abs(xChange) == 2);
                leftRight =  (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                } else legal = false;
              } 
        }
        
        if (legal == true) 
            updateBoard(piece, toSpace);
        
        return legal;
     }
     private  static boolean updateBoard(int piece[], int toSpace[])
     {
         boolean endGame;
         boolean isKinged;
         
         isKinged = (Character.isUpperCase(CB[toSpace[0]][toSpace[1]].charAt(1)));
         //#,#_#,#K
         moveOrder += piece[0] + ',' + piece[1];
         moveOrder += '_' + toSpace[0] + ',' + toSpace[1];
         if (isKinged) moveOrder += Character.toUpperCase(turn);
         else moveOrder += turn;
         numMoves++;
         
            if (CB[piece[0]][piece[1]].equals("[r]") && turn == 'r') {
                 if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]+1][toSpace[1]-(yChange)] ="[_]";
             } else turn = 'b';
                 CB[piece[0]][piece[1]] = "[_]";
       
                if (toSpace[0] != 0) CB[toSpace[0]][toSpace[1]] = "[r]";
                else CB[toSpace[0]][toSpace[1]] = "[R]";
         }
         
         if (CB[piece[0]][piece[1]].equals("[R]")&& turn == 'r')
         {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(xChange)][(toSpace[1])-(yChange)] = "[_]";
             } else turn = 'b';
            
              CB[piece[0]][piece[1]] = "[_]";
              CB[toSpace[0]][toSpace[1]] = "[R]"; 
         }
         if (CB[piece[0]][piece[1]].equals("[b]")&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(xChange)][(toSpace[1])-(yChange)] = "[_]";
             } else turn = 'r';
       
              CB[piece[0]][piece[1]] = "[_]";
              if (toSpace[0] != 7) CB[toSpace[0]][toSpace[1]] = "[b]";
              else CB[toSpace[0]][toSpace[1]] = "[B]";  
         }
          
          if (CB[piece[0]][piece[1]].equals("[B]")&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]-(xChange)][toSpace[1]-(yChange)] = "[_]";
             } else turn = 'r';
            
              CB[piece[0]][piece[1]] = "[_]";
              CB[toSpace[0]][toSpace[1]] = "[B]";
         }
          
          endGame = (redCount == 0 || blackCount == 0);
          return endGame;
        }
     
     public static  void playMoves(int[][] pieceMove, int[][] moveToSpace, String[] moves, int toTurn) {
         int pM[] = new int[2];
         int sM[] = new int[2];
            for (int i = 0; i < toTurn; i++) {
               if (i % 2 == 0) {
                   if (Character.isUpperCase(moves[i].charAt(7))){
                       pM[0] = pieceMove[i][0];
                       pM[1] = pieceMove[i][1];
                       sM[0] = moveToSpace[i][0];
                       sM[0] = moveToSpace[i][1];
                       isLegal(pM, sM);
                   }
               } else {
                    pM[0] = pieceMove[i][0];
                       pM[1] = pieceMove[i][1];
                       sM[0] = moveToSpace[i][0];
                       sM[0] = moveToSpace[i][1];
                       isLegal(pM, sM);
               }
           }
     }
     public String[][] getCB() {
         return CB;
     }
    
    
}
