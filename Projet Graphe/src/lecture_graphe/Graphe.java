package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ListIterator;
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
	
	public boolean circuit()
	{
        System.out.println("* Détection de circuit");
        System.out.println("* Méthode d’élimination des points d’entrée");
        
        CopieGraphe mat = new CopieGraphe(this);
        return mat.is_circuit();
	}
	
    
	public ArrayList<ArrayList<Integer>> rang()
	{
		System.out.println("* Calcul des rangs");
		System.out.println("* Méthode d'élimination des points d'entrée\n");
		
		int[] preds = get_preds();
		ArrayList<ArrayList<Integer>> liste_rangs = new ArrayList<ArrayList<Integer>>();
		
		boolean not_end = true;
		int r = 0;
		while (not_end)
		{
			not_end = false;
			ArrayList<Integer> S = new ArrayList<Integer>();
			for (int i = 0 ; i < nb_sommets ; ++i)
			{
				if (preds[i] == 0)
				{
					not_end = true;
					--preds[i];
					S.add(i);
				}
			}
			
			if (not_end)
			{
				System.out.println("Rang courant = " + r);
				System.out.println("Points d'entrée :");
				
				liste_rangs.add(S);
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
		//for (int i = 0 ; i < nb_sommets ; ++i)
		//	System.out.println("Rang de " + i + " : " + rangs[i]);
		for (int i = 0 ; i < liste_rangs.size() ; ++i)
		{
			System.out.println("Sommets de rang " + i + " : " + liste_rangs.get(i));
		}
		System.out.println();
		return liste_rangs;
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
	

    public boolean valIdentique(){
    	
    	System.out.print("Propriété des valeurs identiques : ");
    	
        boolean val=false;
        for(int i=0;i<nb_sommets;i++){
            int x=0;
            int[] test=new int[nb_sommets];
            for(int j=0;j<nb_sommets;j++){
                if (adj[i][j]){
                	if (!val)
                	{
                		x=poids[i][j];
                		val = true;
                	}
                	else
                	{
                		if (x!=poids[i][j])
                		{
                			System.out.println("non");
                			return false;
                		}
                	}
                }
            }
            val = false;
        }
        System.out.println("oui");
        return true;
    }
    
    
    public boolean valNegatif(){
    	
    	System.out.print("Propriété des valeurs positives : ");
    	
        for (int i=0;i<nb_sommets;i++){
            for (int j=0;j<nb_sommets;j++){
                if (poids[i][j]<0){
                	System.out.println("non");
                    return false;
                }
            }
        }
        System.out.println("oui");
        return true;
    }
    
    public int nbEntree(){
        int nbentre=0;
        for (int i=0;i<nb_sommets;i++){
            int prec=0;
            int suiv=0;
            for (int j=0;j<nb_sommets;j++){
                if (adj[i][j]){
                    suiv+=1;
                }
                if (adj[j][i]){
                    prec+=1;
                }
            }
            if ((suiv>0) && (prec==0)){
                nbentre+=1;
                //System.out.println("Point d'entrée "+ i);
            }
        }
        return(nbentre);
    }
    
    public int nbSortie(){
        int nbsortie=0;
        for (int i=0;i<nb_sommets;i++){
            int prec=0;
            int suiv=0;
            for (int j=0;j<nb_sommets;j++){
                if (adj[i][j]){
                    suiv+=1;
                }
                if (adj[j][i]){
                    prec+=1;
                }
            }
            if ((suiv==0) && (prec>0)){
                nbsortie+=1;
                //System.out.println("Point d'entrée "+ i);
            }
        }
        return(nbsortie);
    }
    
    
    public boolean ordonnancement(){
    	
		System.out.println("* Vérification de l'ordonnancement du graphe");
    	
        boolean ord=false;
        if ((this.valIdentique()==true)&&(this.valNegatif()==true)){
        	System.out.print("Propriété du point d'entré unique : ");
        	if (this.nbEntree()==1){
        		System.out.println("oui");
        		System.out.print("Propriété du point de sortie unique : ");
        		if (this.nbSortie()==1){
        			System.out.println("oui");
		            System.out.println("Il s'agit d'un graphe d’ordonnancement.");
		            ord=true;
        		}
        		else
        			System.out.println("non");
        	}
        	else
        		System.out.println("non");
        }
        else{
            System.out.println("Il ne s'agit pas d'un graphe d’ordonnancement.");
        }
        System.out.println();
        return(ord);
    }
	
	/*public void calendrierTot ()
	{
		for (int i = 0 ; i < nb_sommets ; ++i)
		{
			int date = dateTot(i);
			System.out.println("Sommet " + i + " : " + date);
		}
	}
	
	public int dateTot(int sommet)
	{
		ArrayList<Integer> S = new ArrayList<Integer>();
		
		for (int j = 0 ; j < nb_sommets ; ++j)
		{
			if (adj[sommet][j])
				S.add(dateTot(j) + poids[sommet][j]);
		}
		//System.out.println(sommet + " : " + S);
		if (S.isEmpty())
			return 0;
		return Collections.min(S);
	}*/
    
    public void calendrier(ArrayList<ArrayList<Integer>> rangs)
    {
    	
		System.out.println("* Calcul des dates et des marges");
    	
    	int[] tot = calendrierTot(rangs);
    	int[] tard = calendrierTard(rangs, tot[nb_sommets-1]);
    	int marge[] = margeTotale(tot, tard);

    	System.out.println("           date au plus tot | date au plus tard | marge");
    	for (int i = 0 ; i < nb_sommets ; ++i)
    	{
    		System.out.print("Tâche " + i);
    		if (DigitsTools.nb_digits(i) == 1) { System.out.print(" "); }
    		System.out.print(" |         " + tot[i]);
    		if (DigitsTools.nb_digits(tot[i]) == 1) { System.out.print(" "); }
    		System.out.print("       |         " + tard[i]);
    		if (DigitsTools.nb_digits(tard[i]) == 1) { System.out.print(" "); }
    		System.out.println("        |   " + marge[i]);
    	}
    	
    	System.out.println();
    	
    }
    
    public int[] calendrierTot(ArrayList<ArrayList<Integer>> rangs)
    {
    	int[] dates = new int[nb_sommets];
    	for (ArrayList<Integer> S : rangs)
    	{
    		for (int j : S)
    		{
	    		for (int i = 0 ; i < nb_sommets ; ++i)
	    		{
	    			if (adj[i][j])
	    				dates[j] = Integer.max(dates[j], poids[i][j] + dates[i]);
	    		}
    		}
    	}
    	return dates;
    }
    
    public int[] calendrierTard(ArrayList<ArrayList<Integer>> rangs, int dateFin)
    {
    	int[] dates = new int[nb_sommets];
    	for (int i = 0 ; i < nb_sommets ; ++i)
    		dates[i] = dateFin;
    	ListIterator<ArrayList<Integer>> iterator = rangs.listIterator(rangs.size() - 1);
    	while (iterator.hasPrevious())
    	{
    		for (int i : iterator.previous())
    		{
	    		for (int j = 0 ; j < nb_sommets ; ++j)
	    		{
	    			if (adj[i][j])
	    				dates[i] = Integer.min(dates[i], dates[j] - poids[i][j]);
	    		}
    		}
    	}
    	return dates;
    }
    
    public int[] margeTotale(int[] tot, int[] tard)
    {
    	int[] marge = new int[nb_sommets];
    	for (int i = 0 ; i < nb_sommets ; ++i)
    		marge[i] = tard[i] - tot[i];
    	return marge;
    }
	
}
