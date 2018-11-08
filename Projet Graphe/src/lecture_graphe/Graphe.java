package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Graphe {
	
	protected boolean[][] adj; // Matrice d'adjacence
	protected int[][] poids;   // Matrice de poids des arcs
	protected int nb_sommets;  // Nombre de sommets
	protected int nb_arcs;     // Nombre d'arcs

	public Graphe() {};
	
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
	
	public void circuit()
	{
        System.out.println("* Détection de circuit");
        System.out.println("* Méthode d’élimination des points d’entrée");
        
        CopieGraphe mat = new CopieGraphe(this);
        /*return*/ mat.is_circuit();
	}
	
    
	public void rang()
	{
		System.out.println("* Calcul des rangs");
		System.out.println("* Méthode d'élimination des points d'entrée\n");
		
		int[] preds = get_preds();
		int[] rangs = new int[nb_sommets];
		
		boolean not_end = true;
		int r = 0;
		while (not_end)
		{
			not_end = false;
			List<Integer> S = new ArrayList<Integer>();
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
			
			if (not_end)
			{
				System.out.println("Rang courant = " + r);
				System.out.println("Points d'entrée :");
				
				for (Integer i : S)
				{
					System.out.print(i + " ");
					
					for (int j = 0 ; j < nb_sommets ; ++j)
					{
						if (adj[i][j])
							--preds[j];
					}
				}
				
				System.out.println("\n");
				++r;
			}
		}
		
		// Affichage des rangs
		System.out.println("Rangs calculés :");
		for (int i = 0 ; i < nb_sommets ; ++i)
			System.out.println("Rang de " + i + " : " + rangs[i]);
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
	
	public boolean[][] copieAdj()
	{
		boolean[][] mat = new boolean[nb_sommets][nb_sommets];
		
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			for (int j = 0 ; j < nb_sommets ; ++j)
				mat[i][j] = adj[i][j];
		}
		
		return mat;
	}
	
	public int[][] copiePoids()
	{
		int[][] mat = new int[nb_sommets][nb_sommets];
		
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			for (int j = 0 ; j < nb_sommets ; ++j)
				mat[i][j] = poids[i][j];
		}
		
		return mat;
	}
	
}
