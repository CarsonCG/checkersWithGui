package checkersgame;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import static java.lang.System.out;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CheckersGame extends Application {

    private static final int BOARD_SIZE = 8 ;
    private static final int SQUARE_SIZE= 50 ;
    private static final int NUM_PIECES = 12 ;
    private static int piece[] = new int[2]; 
    private static int toSpace[] = new int[2]; 
    private static int numClicks = 0;
    private static Rules rule;
    private final Save save = new Save();
    private static boolean legalMove;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CheckersGame");
        primaryStage.getIcons().add(new Image("http://a1.mzstatic.com/us/r30/Purple69/v4/fb/b5/81/fbb58164-d1a3-d3f5-68c0-4cd157e9461e/icon175x175.png"));
        Scene scene = new Scene(new Group());
        final GridPane checkerBoard = new GridPane();
        
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        
        while (name.length() > 8) {
            name = save.inpBox("Player 1 Name: ");
            
            if (name.length()>8) save.msgBox("Name too long, Maximum size 8 characters");
        }
        Label[] p1Name = new Label[8];
        for (int i = 0; i < name.length(); i++) { 
            p1Name[i] = new Label(name.charAt(i) + " ");
            p1Name[i].setFont(new Font("Arial", 30));
            //charName[i].setTextFill(Color.web("#0076a3"));
            p1Name[i].setTextFill(Color.web("#F00000"));
            p1Name[i].setTextAlignment(TextAlignment.JUSTIFY);
        }
        for (int i = 0; i < name.length(); i++)
            checkerBoard.add(p1Name[i], 0 +(i), 500);
        
       name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
          while (name.length() > 8) {
            name = save.inpBox("Player 2 Name: ");
            
            if (name.length()>8) save.msgBox("Name too long, Maximum size 8 characters");
        };
          
        Label[] p2Name = new Label[(8];
        for (int i = 0; i < name.length(); i++) { 
            p2Name[i] = new Label(name.charAt(i) + " ");
            p2Name[i].setFont(new Font("Arial", 30));
            p2Name[i].setTextFill(Color.web("#0076a3"));
            //p2Name[i].setTextFill(Color.web("#F00000"));
            p2Name[i].setTextAlignment(TextAlignment.JUSTIFY);
        }
        for (int i = 0; i < name.length(); i++)
            checkerBoard.add(p2Name[i], 0 +(i), 0);
        
        
        configureBoardLayout(checkerBoard);
        addSquaresToBoard(checkerBoard);

        Circle[] redPieces = new Circle[NUM_PIECES];
        Circle[] blackPieces = new Circle[NUM_PIECES];
        addPiecesToBoard(checkerBoard, redPieces, blackPieces);
        
        BorderPane root;
        root = new BorderPane();
        root.setCenter(checkerBoard);
        primaryStage.setScene(new Scene(root, 400, 530,Color.LIGHTGRAY));
        
 
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("_File");
        Menu option = new Menu("_Options");
        Menu edit = new Menu("_Edit");
        MenuItem pSave = new MenuItem("----Save----");
        MenuItem saveMoves = new MenuItem("Save Moves");
        MenuItem saveState = new MenuItem("Save Game State");
        MenuItem pLoad = new MenuItem("----Load----");
        MenuItem loadMoves = new MenuItem("Load Moves");
        MenuItem loadState = new MenuItem("Load Game State");
        MenuItem nameChange = new MenuItem("--Change Name--");
        MenuItem fPlayer = new MenuItem("Player 1");
        MenuItem sPlayer = new MenuItem("Player 2");
        MenuItem setBoard = new MenuItem("Set Board");
        MenuItem lMoves = new MenuItem("--Loaded Moves--");
        MenuItem forward = new MenuItem("Step Forward");
        MenuItem backwards = new MenuItem("Step Backwards");
        
        menuBar.getMenus().addAll(file, option, edit);
        file.getItems().addAll(pSave, saveMoves,saveState, pLoad, loadMoves, loadState);
        option.getItems().addAll(nameChange, fPlayer,sPlayer);
        edit.getItems().addAll(setBoard, lMoves, forward, backwards);
        root.setTop(menuBar);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
        //save.msgBox(event.getSceneX() + " " + event.getSceneY());
       if (numClicks == 0) {
           //addSquaresToBoard(checkerBoard); CLEARS BOARD
           piece[0] = getCoord(event.getSceneY(),'x');
           piece[1] = getCoord(event.getSceneX(), 'y');
            save.msgBox(piece[0] + " " + piece[1]);
            if (piece[0] != -1 ) numClicks++;
        } else {
            toSpace[0] = getCoord(event.getSceneY(), 'x');
            toSpace[1] = getCoord(event.getSceneX(), 'y');
            //save.msgBox(toSpace[0] + " " + toSpace[1]);
            numClicks = 0;
            legalMove = Rules.isLegal(piece, toSpace);
            save.msgBox(legalMove + " ");
        }
        
    }
});
        primaryStage.show();
    }

  
    private void changeName(Label lbl[], int pos, GridPane checkerBoard){
        int player;
        String name;
        
        if (pos == 0) player = 1;
        else player = 2;
        name = "aaaaaaaaaaaaaaaaaaaaaa";
        while (name.length() >8) {
            name = save.inpBox("Player " + player + " Name: ");
        if (name.length() > 8) save.msgBox("Name too long, Maximum size 8 characters");
        };
        
        for (int i = 0; i < name.length(); i++) 
            lbl[i].setText(name.charAt(i).toString));
        for (int i=0; i < name.length(); i++)
            checkerBoard.add(lbl[i], 0, pos);
    } 
    
    private int getCoord(double var, char letter) {
        int z;
        int off;
        
        off = 0;
        if (letter == 'y') off = 0;
        if (letter == 'x') off = 85;
        
        //var = 77;
        Save save = new Save();
        
        z =     (((var - off)) < 0) ? -1
                :((var-off) <= 50) ? 0
                :((var - off) <= 100) ? 1
                :((var - off) <= 150) ? 2
                :((var - off) <= 200) ? 3
                :((var - off) <= 250) ? 4
                :((var - off) <= 300) ? 5
                :((var - off) <= 350) ? 6
                :((var - off) <= 400) ? 7
                : -1;
        //save.msgBox((var - off)+ " " + z);
        return z;
    }
    
    private void addSquaresToBoard(GridPane board) {
        Color[] squareColors = new Color[] {Color.WHITE, Color.BLACK};
        for (int row = 1; row < BOARD_SIZE+1; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle rect = new Rectangle(SQUARE_SIZE,
                        SQUARE_SIZE, squareColors[(row+col)%2]);
                rect.setMouseTransparent(true);
 
                board.add(rect, col,row);
            }
        }
    }
    
    private void addPiecesToBoard(GridPane checkerBoard, Circle[] redPieces,
            Circle[] blackPieces) {
        for (int i=0; i<NUM_PIECES; i++) {
            redPieces[i] = new Circle(SQUARE_SIZE/2-4, Color.RED);
            redPieces[i].setStroke(Color.DARKRED);
            checkerBoard.add(redPieces[i],
                    (i%(BOARD_SIZE/2) * 2 + (2*i/BOARD_SIZE)%2),
                    (BOARD_SIZE - 1 - (i*2)/BOARD_SIZE)+1);

            Circle c = new Circle(SQUARE_SIZE/2 -4, Color.LIGHTBLUE);
            c.setMouseTransparent(true);
            //Mouse events not registered 
            blackPieces[i] = c;
            blackPieces[i].setStroke(Color.DARKBLUE);
            checkerBoard.add(blackPieces[i],
                    i%(BOARD_SIZE/2) * 2 + (1 + 2*i/BOARD_SIZE)%2,
                    ((i*2)/BOARD_SIZE)+1);
        }
    }

    private void configureBoardLayout(GridPane board) {
        for (int i=0; i<BOARD_SIZE; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(SQUARE_SIZE);
            rowConstraints.setPrefHeight(SQUARE_SIZE);
            rowConstraints.setMaxHeight(SQUARE_SIZE);
            rowConstraints.setValignment(VPos.CENTER);
            board.getRowConstraints().add(rowConstraints);

            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(SQUARE_SIZE);
            colConstraints.setMaxWidth(SQUARE_SIZE);
            colConstraints.setPrefWidth(SQUARE_SIZE);
            colConstraints.setHalignment(HPos.CENTER);
            board.getColumnConstraints().add(colConstraints);
        }
    }

    public static void main(String[] args) {
        legalMove = false;
        
        //rule.Rules();
        rule = new Rules();
        launch(args);
    }
}

