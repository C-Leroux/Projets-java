package lecture_graphe;

public class DigitsTools {

	public static int nb_digits(int x)
	{
		int res = 1;
		
		if (x == 0)
			return res;
		if (x < 0)
		{
			x = -x;
			++res;
		}
		
		return (int) (res + Math.log10(x));
	}
}
