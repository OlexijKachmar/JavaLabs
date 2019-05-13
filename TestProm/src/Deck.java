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
     *  * Впорядкування колоди за мастями та значеннями 
    * Порядок сотрування: 
    * Спочатку всі карти з мастю HEARTS, потім DIAMONDS, CLUBS, SPADES 
    * для кожної масті порядок наступний: Ace,King,Queen,Jack,10,9,8,7,6 
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
    
    //Повертає true у випадку коли в колоді ще доступні карти
    public boolean hasNext()
    {
    	if(length>0)
    	{
    		return true;
    	}
    	return false;
    }
    
    //"Виймає" одну карту з колоди, коли буде видано всі 36 карт повертає null
    //Карти виймаються з "вершини" колоди. Наприклад перший виклик видасть SPADES 6 потім
    //SPADES 7, ..., CLUBS 6, ..., CLUBS Ace і так далі до HEARTS Ace
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
