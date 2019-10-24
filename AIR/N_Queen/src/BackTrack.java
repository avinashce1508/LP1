
public class BackTrack {

	final int N=8;
	void SolveNQ(){
		int[][] board = new int[N][N];
		
		if(SolveNQRecur(board,0)==false)
		{
			System.out.println("Solution doesn't exist");
			return;
		}
		printSolution(board);		
	}

	private void printSolution(int[][] board) {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				System.out.print(" "+board[i][j]+" ");
			System.out.println();
		}
	}

	private boolean SolveNQRecur(int[][] board, int col) {
		//base case
		if(col>=N)
			return true;
		
		for(int i=0;i<N;i++)
		{
			if(isSafe(board,i,col))
			{
				board[i][col]=1;
				
				if(SolveNQRecur(board, col+1)==true)
					return true;
				//else backtrack
				board[i][col]=0;
			}
		}
		
		return false;
	}

	private boolean isSafe(int[][] board, int row, int col) {
		
		int i,j;
		//row on left side
		for(i=0;i<col;i++)
			if(board[row][i]==1)
				return false;
		//upper diagonal
		for(i=row,j=col;i>=0 && j>=0;i--,j--)
			if(board[i][j]==1)
				return false;
		
		//lower diagonal
		for(i=row,j=col;i<N && j>=0;i++,j--)
			if(board[i][j]==1)
				return false;
		
		return true;
	}
}
