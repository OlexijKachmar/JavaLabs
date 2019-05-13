
public class Player
{
	private  String status;
	
	public Player(ActionFigure status)
	{
		this.status = status.toString();	
	}
	
	public  String getStatus() 
	{
		return status;
	}
}
