
public class Referee 
{
	 private static String[][] field =  new String[3][3];
	
	static int zones = 9;
	
	public static int checkZones()
	{
		return zones;
	}
	
	public static void fill(int x,int y,Player player)
	{
		field[x][y] = player.getStatus();
		zones--;
	}
	
	public static boolean Win()
	{
		if((field[1][1] == "CROSS"||field[1][1] == "NOUGHT")&&((field[0][0] == field[1][1] && field[1][1] == field[2][2]) || (field[0][2] == field[1][1] && field[1][1] == field[2][0])))
		{
			return true;
		}
		for(int i = 0;i<3;i++)
		{
			if((field[i][i] == "CROSS"||field[i][i] == "NOUGHT")&&((field[i][0] == field[i][1] && field[i][1] == field[i][2]) || (field[0][i] == field[1][i] && field[1][i] == field[2][i])))
			{
				return true;
			}
		}
		
		return false;
	}
}
