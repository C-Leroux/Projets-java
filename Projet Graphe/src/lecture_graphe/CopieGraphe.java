package lecture_graphe;

public class CopieGraphe extends Graphe {

	public CopieGraphe(Graphe g) {
		this.nb_sommets = g.nb_sommets;
		this.nb_arcs = g.nb_arcs;
		this.adj = g.copieAdj();
		this.poids = g.copiePoids();
	}
	
	public void is_circuit(){
        int est_circuit=0;
        int[] tab_circuit=new int[nb_sommets];
        int compteur=0;
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
            if ((suiv>0) && (prec>0)){
                est_circuit+=1;
                tab_circuit[compteur]=i;
                compteur++;
            }
        }
        this.point_entre(nbEntree());
        if (est_circuit>0){
            nb_sommets=compteur;
            this.newgraphe(tab_circuit,compteur);
            //return true;
        }
        this.point_entre(this.nbEntree());
        //return false;
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
    
    public void newgraphe(int[] tab, int comp){
        System.out.println("\nSuppression des points d'entrée");
        System.out.println("Sommets restant :");
        boolean[][] newmat=new boolean[comp][comp];
        for(int i=0;i<comp;i++){
            System.out.print(tab[i]+"\t");
            for(int j=0;j<comp;j++){
                newmat[i][j]=adj[tab[i]][tab[j]];
            }
        }
        adj=newmat;
    }

}
