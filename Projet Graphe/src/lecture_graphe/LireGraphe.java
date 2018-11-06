package lecture_graphe;

import java.io.FileNotFoundException;

public class LireGraphe {

	public static void main(String[] args) {
		if (args.length != 1)
		{
			throw new IllegalArgumentException("Un seul argument attendu");
		}
		
		try {
			Graphe g1 = new Graphe(args[0]);
			System.out.println(g1);
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e.getMessage());
		}
	}

}
