package lecture_graphe;

public class CopieGraphe extends Graphe {

	public CopieGraphe(Graphe g) {
		this.nb_sommets = g.nb_sommets;
		this.nb_arcs = g.nb_arcs;
		this.adj = g.copieAdj();
		this.poids = g.copiePoids();
	}
	
	public boolean is_circuit(){
        boolean est_circuit=false;
        int nb_entree = nbEntree();
        while(nb_entree > 0)
        {
        	int[] tab_circuit=new int[nb_sommets];
            int compteur=0;
        	int pas_circuit=0;
            int[] pas_circuit1=new int[nb_sommets];
            int compteur1=0;
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
	            if (prec>0){
	                est_circuit = true;
	                tab_circuit[compteur]=i;
	                compteur++;
	            }
	            else{
	            	pas_circuit+=1;
	                pas_circuit1[compteur1]=i;
	                compteur1++;
	            }
	        }
        
	        this.point_entre(nb_entree);
	        if (est_circuit)
	            	this.newgraphe(tab_circuit,pas_circuit1,compteur1);
            nb_entree = nbEntree();
        }
        point_entre(nb_entree);
        System.out.println();
        if (isEmpty()){
        	System.out.println("Le graphe ne contient pas de circuit.\n");
        	return false;
        }
        else{
        	System.out.println("Le graphe contient au moins un circuit.\n");
        	return true;
        }
    }
	
	public boolean isEmpty()
	{
		boolean vide = true;
		int i = 0;
		while (i < nb_sommets && vide)
		{
			int j = 0;
			while (j < nb_sommets && vide)
			{
				if (adj[i][j])
					vide = false;
				++j;
			}
			++i;
		}
		return vide;
	}
    
    
    public void point_entre(int entree){
        System.out.println("\nPoint d'entrée :");
        if (entree==0){
            System.out.println("Aucun.");
        }
        else{
            for (int i=0;i<nb_sommets;i++){
                int prec=0;
                int suiv=0;
                int x=0;
                for (int j=0;j<nb_sommets;j++){
                    if (adj[i][j]){
                        suiv+=1;
                    }
                    if (adj[j][i]){
                        prec+=1;
                    }
                }
                if ((suiv>0) && (prec==0)){
                    System.out.printf(i+"\t");
                }
            }
        }
    }
    
    public void newgraphe(int[] tab,int[] tab1, int comp){
        System.out.println("\nSuppression des points d'entrée");
        System.out.println("Sommets restant :");
        for(int i=0;i<comp;i++){
            for(int j=0;j<nb_sommets;j++)
            {
                 adj[tab1[i]][j]=false;
                 adj[j][tab1[i]]=false;
            }
                 
        }
        for(int i=0;i<nb_sommets - comp;i++){
            System.out.print(tab[i]+"\t");
        }
        //System.out.println(this);
    }

}
