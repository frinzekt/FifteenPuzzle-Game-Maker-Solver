;
    
    /**
     * FifteenPuzzleViewer displays a 15 puzzle and interacts with the user.
     * 
     * @author FRINZE LAPUZ AND ETHAN LIM
     * @version April 2019
     */
    
    import java.awt.event.*;
    import java.awt.*;
    import java.awt.Color;
    import java.awt.Graphics;
    import javax.swing.JFrame;
    
    public class FifteenPuzzleViewer implements MouseListener 
    {
        private FifteenPuzzle puzzle; // the internal puzzle 
        private int size;             // the size of the puzzle
        private SimpleCanvas sc;      // the canvas for display.
            
        Color[] vega = new Color[7];
        
         
        
        /**
         * Sets up the three variable fields and draws the initial puzzle
         */
        public FifteenPuzzleViewer(FifteenPuzzle puzzle) 
        {
            // COMPLETE THIS 5
            this.puzzle = puzzle;
            sc = new SimpleCanvas("Fifteen Puzzle - Frinze and Ethan", 1000,1000, Color.white);
            //sc.setFont(new Font("TimesRoman", Font.PLAIN, (800/size)/10));
            sc.addMouseListener(this); 
            
            size = this.puzzle.getsize();
            
            //Color initialize
            vega[0] = new Color(84, 84, 84);   //Grey
            vega[1] = new Color(0, 172, 226);   //Blue
            vega[2] = new Color(135, 195, 47);   //Green
            vega[3] = new Color(253, 73, 124);   //Pink
            vega[4] = new Color(177, 67, 221);   //Purple
            vega[5] = new Color(255, 165, 19);   //Orange
            vega[6] = new Color(251, 251, 251);  //Dirty White
        
            drawGrid();
    }
    
    public void mouseClicked (MouseEvent e) {
        
    } 
    public void mousePressed (MouseEvent e) {
        
        int x = e.getX() / (800/size) ; //100 Spacing Division
        int y = e.getY() / (800/size) ; //x and y are grid index
      //  System.out.print("MOUSE PRESSED - OUTSIDE IF");
        moveTile_Verify(x,y);
                
        
    } 
    
    
    public void mouseReleased(MouseEvent e) {} 
    public void mouseEntered (MouseEvent e) {} 
    public void mouseExited (MouseEvent e) {} 
    
    public void AI_moveTile(int x, int y){
        moveTile_Verify(x,y);
        sc.wait(50);
    }
    public void moveTile_Verify(int x, int y){
        if (!puzzle.finished()){
        //    System.out.print("MOUSE PRESSED - INSIDE IF");
            puzzle.moveTile(x,y);
            sc.drawRectangle(0,0,800,800,Color.white); //OVERLAY WHITE SHAPE
            drawGrid();
            if (puzzle.finished()){
                 sc.drawRectangle(50+200,150+200,350+200,250+200,(new Color(84, 84, 84,200))); //HURRAH
                 sc.drawString("Congratulations, you've completed the puzzle", 75+200,400,vega[6]);
                 
            }
        }
    }
    
    // draws the current grid on sc
    private void drawGrid()
    {
        // COMPLETE THIS 3

        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int n = puzzle.getGrid()[i][j];
                int color_indicator = 6;
                /*
                if (n >= 13){ //row 4
                    color_indicator = 4;
                }
                else if (n >= 9){ //row 3
                    color_indicator = 3;
                }
                else if (n >= 5){ //row 2
                    color_indicator = 2;
                }
                else if (n >= 1){ //row 1
                    color_indicator = 1;
                }
                */
                color_indicator = ((n-1)/(size))%5 +1;
                
                if (n==0){
                    color_indicator = 6;
                }
                
                drawTile(i,j,n, vega[color_indicator]);
            }
        }
        
        
    }
    
    // draws the tile at x,y in colour c at the appropriate spot on sc
    private void drawTile(int x, int y, int n, Color c)
    {   
        // COMPLETE THIS 4
        int length = 94; //CHANGE LENGTH HERE
        int shadow = 7;
        int spacing = 5; //CHANGE SPACING HERE
        
        length = (800/size)*94/100;
        shadow = (800/size)*7/100;
        spacing = (800/size)*5/100;
        
        
        int x1 = spacing *(x+1) + length * x;
        int y1 = spacing *(y+1) + length * y;
        
        int x2 = x1 + length;
        int y2 = y1 + length;
        
        int xave = (x1+x2 -shadow)/2;
        int yave = (y1+y2 -shadow)/2;
        
        sc.drawRectangle(x1 + shadow,y1 + shadow,x2,y2,vega[0]); //SHADOW
        sc.drawRectangle(x1,y1,x2 - shadow,y2 - shadow,c); //rectangle
        
        
        if (n!= 0){
            sc.drawString(n,xave,yave,vega[6]);
        }   
    }
    
    public FifteenPuzzle getPuzzle(){
        return puzzle;
    }
    
}
