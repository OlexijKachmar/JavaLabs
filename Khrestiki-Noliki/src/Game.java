import java.util.Scanner;

enum ActionFigure
{
	NOUGHT,
	CROSS
}

public class Game
{
	public static void main(String[] args)
	{
		int x1,x2,y1,y2;
		Player player1 =  new Player(ActionFigure.CROSS);
		Player player2 =  new Player(ActionFigure.NOUGHT);
		var in = new Scanner(System.in);
		while(true)
		{
			
			x1 = in.nextInt();
			y1 = in.nextInt();
			Referee.fill(x1, y1, player1);
			if(Referee.Win())
			{
				System.out.println("WON player 1");
			}
			x2 = in.nextInt();
			y2 = in.nextInt();
			Referee.fill(x2, y2, player2);
			if(Referee.Win())
			{
				System.out.println("WON player 2");
			}
			if(Referee.checkZones()<2)
			{
				System.out.println("Draw");
			}
					
	}
}
}
