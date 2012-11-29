import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Queen{
	
	static int chessBoardRowLength=8;
	static int chessBoardColLength=8;
	String picture="|_";
	String endPicture="|";
	String queenPicture="|Q";
   static  boolean grid[][]= new boolean[chessBoardRowLength][chessBoardColLength];
	
	
	public void showGrid(){
			
			for(int i=0;i<chessBoardRowLength;i++){
				for(int j=0;j<chessBoardColLength;j++){
					if(grid[i][j]){
						System.out.print("true |");
					}else{
						System.out.print("false|");
					}
				}
				System.out.println("");
			}
		
	}
	public void showGridWithQueen(){
			
			for(int i=0;i<chessBoardRowLength;i++){
				for(int j=0;j<chessBoardColLength;j++){
					if(grid[i][j]){
						System.out.print(queenPicture);
					}else{
						System.out.print(picture);
					}
				}
				System.out.println(endPicture);
			}
		
	}
	
	public void placeQueens(){
		try{
				File file = new File("chessboard.txt");
				if(!file.exists()){
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bwr = new BufferedWriter(fw);
				StringBuffer buf = new StringBuffer(); 
			
			
			for(int i=0;i<chessBoardRowLength;i++){
				for(int j=0;j<chessBoardColLength;j++){
					if(grid[i][j]){
						buf.append(queenPicture);
					}else{
						buf.append(picture);
					}
				}
				buf.append(endPicture);
				buf.append("\n");
			}
			
			bwr.write(buf.toString());
			bwr.close();
			
		}catch(IOException e){
			System.out.println(e.toString());
		}
 
	}
	
	public boolean isHorizontalSafe(boolean [][]grid,int row, int col){
		
		for(int i=0;i<chessBoardColLength;i++){
			if(grid[row][i]){
				return false;
			}
		}
		return true;
	}
	
	public boolean isVerticalSafe(boolean [][]grid, int row, int col){
		for(int i=0;i<chessBoardRowLength;i++){
			if(grid[i][col]){
				return false;
			}
		}
		return true;
	}
	
	public boolean isLeftDiagnalSafe(boolean [][]grid, int row, int col){
 		//System.out.println("isLeftDiagnalSafe at ["+row+"]["+col+"]");
		int i=0,j=0;
		i=row;
		j=col;
		while(i>0&&j<chessBoardColLength-1){
			//System.out.println("i "+i+" j "+j);
			if(grid[i-1][j+1])
				return false;
			i--;
			j++;
		}
		
		i=row;
		j=col;
		while(j>0&&i<chessBoardRowLength-1){
			if(grid[i+1][j-1])
				return false;
			j--;
			i++;
		}
		
		return true;
		
	}
	
	public boolean isRightDiagnalSafe(boolean [][]grid, int row, int col){
  		//System.out.println("isRightDiagnalSafe at ["+row+"]["+col+"]");
		int i=0,j=0;
		i=row;
		j=col;
		
		while(i<chessBoardRowLength-1 && j<chessBoardColLength-1){
			if(grid[i+1][j+1])
				return false;
			i++;
			j++;
		}
		i=row;
		j=col;
		
		while(i>0 && j>0){
			if(grid[i-1][j-1])
				return false;
				i--;
				j--;
		}
		
		return true;
		
	}
	
	public boolean isDiagonalSafe(boolean [][]grid,int row, int col){
	      
		return isLeftDiagnalSafe(grid,row,col) && 
		       isRightDiagnalSafe(grid, row, col);
 
		
	}
	 
	public boolean isSafe(boolean [][]grid, int row, int col){
		 
  return	   isHorizontalSafe(grid,row,col) && 
		  	   isVerticalSafe(grid,row,col) &&
			   isDiagonalSafe(grid, row, col);
	}
	
 
	
	public void placeQueen(boolean [][]grid,int row, int col){
		grid[row][col]=true;
	}
	
	public void removeQueen(boolean[][]grid, int row, int col){
		grid[row][col]=false;
	}
 
	
	
	public boolean solve(boolean [][]grid,int col){
	
		if(col>=chessBoardColLength){
			return true;
		}
		//System.out.println("about start at col="+col);
		
		for(int rowTry=0;rowTry<chessBoardRowLength;rowTry++){
			
			if(isSafe(grid,rowTry,col)){
			//	//System.out.println("grid ["+rowTry+"]"+"["+col+"]");
				placeQueen(grid,rowTry,col);				
				if(solve(grid,col+1)){
					return true;
				}
				removeQueen(grid,rowTry,col);
			}
 
		}
		return false;
	}
	
	public void initializeGrid(){
		for(int i=0;i<chessBoardColLength;i++){
			for(int j=0;j<chessBoardColLength;j++){
				grid[i][j]=false;
			}
		}
	}
	
	public void drawChessBoard(){
		//System.out.println(" drawing chessboard without queens");
		try{
		File file = new File("chessboard.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bwr = new BufferedWriter(fw);
		StringBuffer buf = new StringBuffer(); 
		for(int i=0;i<chessBoardRowLength;i++){
			for(int j=0;j<chessBoardColLength;j++){
				buf.append(picture);
			}
			buf.append(endPicture);
			buf.append("\n");
		}
		bwr.write(buf.toString());
		bwr.close();

	}catch(IOException e){
		System.out.println(e.toString());
	}
 }	

	public void clearBoard(){
	//System.out.println("clearing board");		
		for(int i=0;i<chessBoardRowLength;i++){
			for(int j=0;j<chessBoardColLength;j++){
				grid[i][j]=false;
			}
		}
	}

 public static void main(String []abd){
	Queen q = new Queen();
	q.drawChessBoard();
	q.clearBoard();
	////System.out.println(q.solve(grid,0)+" is hte answer");
	//q.showGrid();
	//q.showGridWithQueen();
	if(q.solve(grid,0)){
		q.placeQueens();
	}else{
		System.out.println("cannot be solved");
	}	
}
	
}