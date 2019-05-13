public class Deck 
{
	int length = 0;
	public Card[] cards = new Card[36];
	public Rank[] ranks = Rank.values();
	public Suit[] suits = Suit.values();
	public Deck()
	{
		
		for(int i = 0; i<cards.length; i++)
		{
    	cards[i]= new Card(ranks[i % 9],suits[i % 4]); 
		}
	}
	
    public void shuffle()
    {
    	for(int i = cards.length - 1; i>=0; i--)
		{
    		cards[i]= new Card(ranks[i % 9],suits[i % 4]);
		}
    }
    /*for(int i = cards.length - 1;i>=0;i--)
    	{
        	cards[i]= new Card(Rank.values[i % 9],Suit.values[i % 4]); 
    	}
     *  * ������������� ������ �� ������� �� ���������� 
    * ������� ����������: 
    * �������� �� ����� � ����� HEARTS, ���� DIAMONDS, CLUBS, SPADES 
    * ��� ����� ���� ������� ���������: Ace,King,Queen,Jack,10,9,8,7,6 
    */
    public void order()
    {	
    	for(int i = 0;i<suits.length;i++)
    	{
    		for(int j = 0;j<ranks.length;j++)
    		{
        	cards[length] = new Card(ranks[j],suits[i]); 
        	length++;
        	}
    		
    	}
    }
    
    //������� true � ������� ���� � ����� �� ������� �����
    public boolean hasNext()
    {
    	if(length>0)
    	{
    		return true;
    	}
    	return false;
    }
    
    //"�����" ���� ����� � ������, ���� ���� ������ �� 36 ���� ������� null
    //����� ���������� � "�������" ������. ��������� ������ ������ ������� SPADES 6 ����
    //SPADES 7, ..., CLUBS 6, ..., CLUBS Ace � ��� ��� �� HEARTS Ace
    public Card drawOne()
    {
    	if(hasNext())
    	{
    		length--;
    		return cards[length];
    	}
    	else
    	{
    		return null;
    	}
    }
    
}
