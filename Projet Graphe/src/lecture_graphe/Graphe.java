package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Graphe {
	
	private boolean[][] adj; // Matrice d'adjacence
	private int[][] poids;   // Matrice de poids des arcs
	private int nb_sommets;  // Nombre de sommets
	private int nb_arcs;     // Nombre d'arcs

	public Graphe(String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		
		nb_sommets = Integer.parseInt(scan.nextLine());
		adj = new boolean[nb_sommets][nb_sommets];
		poids = new int[nb_sommets][nb_sommets];
		nb_arcs = Integer.parseInt(scan.nextLine());
		
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
	
	public void ajoutArc(int src, int dst, int val)
	{
		adj[src][dst] = true;
		poids[src][dst] = val;
	}


	public String afficherAdj()
	{
		String result = "Matrice d'adjacence :\n";
		result += afficherColonnes();
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			result += i + aligner(i);
			for (int j = 0 ; j < nb_sommets ; ++j)
			{
				if (adj[i][j])
					result += "V    ";
				else
					result += "F    ";
			}
			result += "\n";
		}
		return result;
	}
	
	public String afficherPoids()
	{
		String result = "Valeur des arcs :\n";
		result += afficherColonnes();
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			result += i + aligner(i);
			for (int j = 0 ; j < nb_sommets ; ++j)
			{
				if (adj[i][j])
					result += poids[i][j] + aligner(poids[i][j]);
				else
					result += "*    ";
			}
			result += "\n";
		}
		return result;
	}
	
	public String afficherColonnes()
	{
		String result = "     ";
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			result += i + aligner(i);
		}
		return result + "\n";
	}
	
	public String aligner(int x)
	{
		String result = "";
		for (int k = DigitsTools.nb_digits(x) ; k < 5 ; ++k)
		{
			result += " ";
		}
		return result;
	}
	
	public String toString()
	{
		return "* Repr�sentation du graphe sous forme matricielle :\n"
				+ afficherAdj() + "\n" + afficherPoids();
	}
	
}
