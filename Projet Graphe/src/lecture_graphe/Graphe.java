package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
	
	public void rang()
	{
		int[] preds = get_preds();
		int[] rangs = new int[nb_sommets];
		
		boolean not_end = true;
		int r = 0;
		List<Integer> S = new ArrayList<Integer>();
		while (not_end)
		{
			not_end = false;
			for (int i = 0 ; i < nb_sommets ; ++i)
			{
				if (preds[i] == 0)
				{
					not_end = true;
					--preds[i];
					rangs[i] = r;
					S.add(i);
				}
			}
			for (Integer i : S)
			{
				for (int j = 0 ; j < nb_sommets ; ++j)
				{
					if (adj[i][j])
						--preds[j];
				}
			}
			++r;
		}
		
		// Affichage des rangs
		for (int i = 0 ; i < nb_sommets ; ++i)
			System.out.println("Range de " + i + " : " + rangs[i] + "\n");
	}
	
	public int[] get_preds()
	{
		int[] preds = new int[nb_sommets];
		for (int j = 0 ; j < nb_sommets ; ++j)
		{
			preds[j] = 0;
			for (int i = 0 ; i < nb_sommets ; ++i)
			{
				if (adj[i][j])
					++preds[j];
			}
		}
		return preds;
	}
	
	public String toString()
	{
		return "* Représentation du graphe sous forme matricielle :\n"
				+ afficherAdj() + "\n" + afficherPoids();
	}
	
}
