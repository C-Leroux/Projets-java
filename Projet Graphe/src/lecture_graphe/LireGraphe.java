package lecture_graphe;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LireGraphe {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrez le nom du graphe à utiliser, ou \" fin \" pour sortir du programme :");
		String filename = scan.nextLine();
		
		while (filename.compareTo("fin") != 0)
		{
			try {
				Graphe g1 = new Graphe(filename + ".txt");
				System.out.println(g1); //Question 2
				g1.circuit(); //Question 3
				//g1.rang(); //Question 4
				g1.calendrierTot();
			}
			catch (FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("Entrez le nom du graphe à utiliser :");
			filename = scan.nextLine();
		}
		
		System.out.println("Fin du programme");
	}

}