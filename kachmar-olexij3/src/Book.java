import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable
{
	private ArrayList<String> authors;
	public  ArrayList<String> getAuthors()
	{	return authors;		}
	public  void setAuthors(ArrayList<String> Authors)
	{	 authors = (ArrayList<String>)Authors.clone();		}
	
	private String widawniztwo;
	public String getWidawniztwo()
	{	return widawniztwo;		}
	public void setWidawniztwo(String Widawniztwo)
	{	 widawniztwo = Widawniztwo;		}
	private String isbn;
	public String getIsbn()
	{	return isbn;	}
	public void setIsbn(String Isbn)
	{	 isbn = Isbn;	}
	
	private String name;
	public String getName()
	{	return name;	}
	public void setName(String Name)
	{name = Name;}
	
	private String genre;
	public String getGenre()
	{	return genre;	}
	public void setGenre(String Genre)
	{	 genre = Genre;	}
	
	
	private String date;
	public String getDate()
	{ 	return date;	}
	public void setDate(String Date)
	{ 	 date = Date;	}
	
	
}