
public class Branch_Bound {
	final int N=8;
	void SolveNQ(){
		int[][] board = new int[N][N];
		int[][] slashCode = new int[N][N];
		int[][] backSlashCode = new int[N][N];
		
		boolean[] rowLookUp = new boolean[N];
		boolean[] slashCodeLookUp = new boolean[2*N-1];
		boolean[] backSlashCodeLookUp = new boolean[2*N-1];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				slashCode[i][j] = i+j;
				backSlashCode[i][j] = i-j+(N-1);
			}
		}
		
		if(SolveNQRecur(board,0,slashCode,backSlashCode,rowLookUp,slashCodeLookUp,backSlashCodeLookUp)==false)
		{
			System.out.println("Solution doesn't exist");
			return;
		}
		printSolution(board);		
	}

	private void printSolution(int[][] board) {
		// TODO Auto-generated method stub
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				System.out.print(" "+board[i][j]+" ");
			System.out.println();
		}
	}

	private boolean SolveNQRecur(int[][] board, int col, int[][] slashCode, int[][] backSlashCode, boolean[] rowLookUp, boolean[] slashCodeLookUp, boolean[] backSlashCodeLookUp) {
		// TODO Auto-generated method stub
		//base case
		if(col>=N)
			return true;
		
		for(int i=0;i<N;i++)
		{
			if(isSafe(i,col,slashCode,backSlashCode,rowLookUp,slashCodeLookUp,backSlashCodeLookUp))
			{
				board[i][col]=1;
				rowLookUp[i] = true;
				slashCodeLookUp[slashCode[i][col]] = true;
				backSlashCodeLookUp[backSlashCode[i][col]] = true;
				
				if(SolveNQRecur(board, col+1, slashCode,backSlashCode,rowLookUp,slashCodeLookUp,backSlashCodeLookUp)==true)
					return true;
				//else backtrack
				board[i][col]=0;
				rowLookUp[i] = false;
				slashCodeLookUp[slashCode[i][col]] = false;
				backSlashCodeLookUp[backSlashCode[i][col]] = false;
			}
		}
		
		return false;
	}

	private boolean isSafe(int row, int col, int[][] slashCode, int[][] backSlashCode, boolean[] rowLookUp, boolean[] slashCodeLookUp, boolean[] backSlashCodeLookUp) {
		// TODO Auto-generated method stub
		if(slashCodeLookUp[slashCode[row][col]] || backSlashCodeLookUp[backSlashCode[row][col]] || rowLookUp[row])
			return false;
		return true;
	}
}
