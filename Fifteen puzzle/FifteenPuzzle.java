
/**
 * FifteenPuzzle maintains the internal representation of a 15 puzzle.
 * 
 * @author FRINZE LAPUZ AND ETHAN LIM
 * @version April 2019
 */
import java.util.Arrays;
import java.util.Random;

public class FifteenPuzzle 
{ 
    private      int[][] grid; // the current positions of the tiles and the space, denoted by 0..15
    private      int xspace;   // xspace,yspace are the current coordinates of the space
    private      int yspace;
    public       int size = 4; // the number of tiles across and down 
    private      int[][] goal; // the tile positions in the goal state

    // these two are public and static so that they can be used in BlueJ 
    public static int[][] close4   = {{ 1, 5, 9,13}, { 2, 6,10,14}, { 3, 7,11, 0}, { 4, 8,12,15}};
    public static int[][] example4 = {{ 5,11,14, 0}, { 9, 3,13, 7}, { 2, 8,10,12}, { 4, 1,15, 6}};

    // sets up the goal variable for a size x size puzzle 
    // create the 2D array object for goal and set its elements appropriately: 
    // 1, size+1, 2*size+1, etc. in the first array, 
    // 2, size+2, 2*size+2, etc. in the second array, etc. 
    private void setUpGoal()
    {
        // COMPLETE THIS 1
        // goal = close4;
        goal = new int[size][size];

        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                goal[i][j] = 1+j*size + i;
            }
        }

        goal[size-1][size-1] = 0;

    }

    // this constructor sets up the grid using initialGrid 
    // It also initialises the other fields
    // initialgrid is assumed to be a legal position
    public FifteenPuzzle (int[][] initialGrid)
    {
        // COMPLETE THIS 2
        grid = initialGrid;
        size = grid.length;
        setUpGoal();

        for (int i =0; i< size; i++){
            for (int j =0; j< size; j++){
                if(grid[i][j]==0){
                    xspace = i;
                    yspace = j;
                }
            }
        }

    }

    // this constructor sets up the grid by copying goal and then making random moves 
    // It also initialises the other fields
    public FifteenPuzzle (int size, boolean IsAnswer)
    {
        // COMPLETE THIS 9
        //CHANGE THIS
        /*grid = close4;
        setUpGoal();

        xspace = 2;
        yspace = 3;*/
        this.size = size;
        setUpGoal();
        grid = new int[size][size];

        for (int i =0; i< size; i++){
            for (int j =0; j< size; j++){
                grid[i][j] = goal[i][j];
            }
        }

        xspace = size-1;
        yspace = size-1; 

        if(!IsAnswer){
            Random rand = new Random();
            int n = 1000*size; //125 per size
            for (int i = 0; i<n; i++){
                int dir = rand.nextInt(4); //0 N, 1 E, 2 S, 3 W

                if (dir == 0){
                    moveTile(xspace,yspace-1);
                }
                else if (dir == 1){
                    moveTile(xspace+1,yspace);
                }
                else if (dir == 2){
                    moveTile(xspace,yspace+1);
                }
                else if (dir == 3){
                    moveTile(xspace-1,yspace);
                }

            }
        }

        
    }
    // returns the grid
    public int[][] getGrid()
    {
        return grid;
    }
    
    public int[][] getGoal(){
        return goal;
    }

    //returns size
    public int getsize(){
        return size;
    }

    // returns true iff the tile at x,y is adjacent to the space
    public boolean legalClick(int x, int y)
    {
        int x_delta = Math.abs(xspace-x);
        int y_delta = Math.abs(yspace-y);
        if (((0 <= x) && (x < size)) && ((0 <= y) && (y < size))){
            if ((x_delta == 1) && (y_delta==0)){ //difference of 1 in x-axis
                return true;
            }
            else if((y_delta == 1) && (x_delta==0)){ //difference of 1 in y-axis
                return true;
            }
        }
        return false;

    }

    // returns true iff the puzzle is in the goal state
    public boolean finished()
    {
        if (Arrays.deepEquals(goal, grid)){
            return true;    
        }
        else{
            return false;
        }
    }

    // if x,y is a legal click on the board, moves the tile at x,y; 
    // otherwise does nothing 
    public void moveTile (int x, int y) 
    {
        // COMPLETE THIS 7
        if (legalClick(x,y)){
            grid[xspace][yspace] = grid[x][y];
            grid[x][y] = 0;

            xspace = x;
            yspace = y;
        }
    }
    
    public int[][] moveTile_predict(int x, int y){
        
        int [][] matrix = new int[size][size];
        
        for (int i =0; i< size; i++){
            for (int j =0; j< size; j++){
                matrix[i][j]=grid[i][j];
            }
        }
        
        if (legalClick(x,y)){
            matrix[xspace][yspace] = matrix[x][y];
            matrix[x][y] = 0;
        }
        else {
            return null;
        }
        return matrix;
    }
    
    public int getXspace(){
        return xspace;
    }
    
    public int getYspace(){
        return yspace;
    }
    

}
