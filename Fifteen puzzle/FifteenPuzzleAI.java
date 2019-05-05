
/**
 * Write a description of class FifteenPuzzleAI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Arrays;
import java.util.Random;
public class FifteenPuzzleAI
{
    // instance variables - replace the example below with your own
    private int x;
    private FifteenPuzzleViewer puzzle_viewer;
    private FifteenPuzzle puzzle;

    private int[][] grid;
    private int[][] goal;

    /**
     * Constructor for objects of class FifteenPuzzleAI
     */
    public FifteenPuzzleAI(FifteenPuzzleViewer puzzle_viewer)
    {
        // initialise instance variables
        this.puzzle_viewer = puzzle_viewer;
        this.puzzle = puzzle_viewer.getPuzzle();

        grid = puzzle.getGrid();
        goal = puzzle.getGoal();

        //solve();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void solve(){
        int [] x_move = {0,1,0,-1}; // UP, RIGHT, DOWN, LEFT
        int [] y_move = {-1,0,1,0};
        int banned_move = 10000;
        int movement_index =0;
        int loop_breaker = 0;
        int previous_move = 0;
        int size = puzzle.getsize();

        int [][] old_matrix = new int[size][size];
        int effort = 0;
        while(!puzzle.finished()){
            int[] cost = {10000,10000,10000,10000};  // UP, RIGHT, DOWN, LEFT
            //effort++;
            /*if ((effort-1)%12==0){
                if(Arrays.deepEquals(old_matrix,grid)){
                    Random rand = new Random();
                    movement_index = rand.nextInt(4);
                    puzzle_viewer.AI_moveTile(puzzle.getXspace()+x_move[movement_index],puzzle.getYspace()+y_move[movement_index]);
                }
                for (int i =0; i<size; i++){
                    for (int j =0; j<size; j++){
                        old_matrix[i][j]=grid[i][j];
                    }
                }

            }*/
            for (int i=0; i<4; i++){
                if(i!=banned_move){
                    int[][] current_matrix = puzzle.moveTile_predict(puzzle.getXspace()+x_move[i],puzzle.getYspace()+y_move[i]);

                    if(current_matrix !=null){
                        cost[i]=effort + difference(goal,current_matrix);
                    }
                    else{
                        cost[i]=10000;
                    } 
                }

            }

            movement_index = Min(cost);
            if(previous_move != movement_index){
                loop_breaker++;
                if (loop_breaker==5){
                    Random rand = new Random();
                    movement_index = rand.nextInt(4);
                    loop_breaker=0;
                }
                
            }
            else{
                loop_breaker=0;
            }
            previous_move = movement_index;
            banned_move = movement_index;
            if (movement_index<2){
                banned_move +=2;
            }
            else{
                banned_move -=2;
            }
            puzzle_viewer.AI_moveTile(puzzle.getXspace()+x_move[movement_index],puzzle.getYspace()+y_move[movement_index]);

        }
    }

    private int difference(int[][]goal, int[][] matrix){
        int dif=0;

        for (int i=0; i<goal.length; i++){
            for(int j=0; j<goal[0].length; j++){
                if(goal[i][j] != matrix[i][j]){
                    dif++;
                }
            }
        }
        dif = dif -1; //empty space does not count
        return dif;
    }

    private int Min (int[] num){
        int index = 0;

        for (int i=1; i<num.length; i++){
            if(num[i]<num[index]){
                index = i;
            }
        }
        return index;
    }

    
}
