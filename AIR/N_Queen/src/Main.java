import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Select option: 1.BackTracking\n2.Branch and Bound");
		Scanner sc = new Scanner(System.in);
		int opt = sc.nextInt();
		if(opt==1)
		{
			BackTrack bt = new BackTrack();
			bt.SolveNQ();
		}
		else
		{
			Branch_Bound bb = new Branch_Bound();
			bb.SolveNQ();
		}
		
		sc.close();
	}

}
