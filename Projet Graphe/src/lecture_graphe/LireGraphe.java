package lecture_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LireGraphe {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1)
		{
			throw new IllegalArgumentException("Un seul argument attendu");
		}
		
		Scanner scan = new Scanner(new File(args[0]));
		
		while (scan.hasNext())
		{
			scan.nextLine();
		}
	}

}
