
CheckersGame.java is the main class.

#To Do:
- Add a rule to rules class,
    - if a jump is avaiable move is only legal if it is a jump.
    - if you jump a piece and no more jumps are avaiable with said piece end the game.

- Sync GUI with Rules representation of the board
    - Write a function to be able to translate array coordinates back to GUI coordinates
    - Draw rectangles of the apporiate color over spaces that become empty
          - Color of the square will be constant
          

    - Draw pieces on new square
    - Make something to indicate if a piece was kinged
        - if a piece was kinged, the color of that piece will be darker
        - Ex: red -> Dark red, LightBlue -> DarkBlue
    *note* each square is 50 by 50
    *note* the board is 400 x 400
    *note* the y value of the coordinates are displayed by 85, so if you want to add anything add 85*


- Add Click events for menu items
- Link menu items to the appopriate functions in the save class
- Finish writting functions in the save class
    - Finish save moves function
    - Add something to translate load moves into changes on the board
        -Ask the user which move out of maxMoves they want to start from
        -Make file indicator at top indicating file type for first line 
            - SS = saved state, SM = saved moves
            - if SS is loaded with load saved move option = "Invalid File Type!";
            

- Add some way to prompt the user if they want to reset the game upon completion
- Warn the user that a move will end the game
    - Pointless though, if there is a jump available they have to take it 
