package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graphe {
	
	private int[][] graphe;

	public Graphe(String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		
		int nb_sommets = Integer.parseInt(scan.nextLine());
		graphe = new int[nb_sommets][nb_sommets];
		
		int nb_arcs = Integer.parseInt(scan.nextLine());
		
		for (int i = 0 ; i < nb_arcs ; ++i)
		{
			int src = scan.nextInt();
			int dst = scan.nextInt();
			int val = scan.nextInt();
			ajoutArc(src, dst, val);
			scan.nextLine();
		}
		
		scan.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
