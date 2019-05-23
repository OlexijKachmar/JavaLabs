package application;



import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;




public class Main extends Application 
{
	public static void main(String[] args) {

		Application.launch(args);
	}

	private static String file = "f.xml";
	private static String shablonForDate = "\\d{2}[.]\\d{2}[.]\\d{4}";
	private static String shablonForIsbn = "\\d+-\\d+-\\d+-\\d+-\\d+";

	public static void addProps(TableView<Book> table,String name,String getter,double division)
	{
		TableColumn<Book, String> new_column = new TableColumn<Book, String>(name);
		new_column.setCellValueFactory(new PropertyValueFactory<Book, String>(getter));
		new_column.prefWidthProperty().bind(table.widthProperty().divide(division));
		table.getColumns().add(new_column);
	}
	public static void toStr(ObservableList<Book> books)
	{
		Book el;String s = "";
		for(int i = 0;i<books.size();i++)
		{
			el = books.get(i);
			s = "Date " + el.getDate() + "\n" + "Isbn " + el.getIsbn() + "\n" + "Genre " + el.getGenre()+ "\n"+ "Name of Book" 
					+ el.getName() + "\n" + "Widawniztwo " + el.getWidawniztwo() + "\n" + "Authors ";
			for(int k = 0;k<el.getAuthors().size();k++)
			{
				s += el.getAuthors().get(k) + " ";
			}
			System.out.println(s);
		}
	}

	@Override
	public void start(Stage stage) 
	{
		ObservableList<Book> books = FXCollections.observableArrayList();
		TableView<Book> table = new TableView();
		table.prefHeight(1000);
		table.prefWidth(800);
		//	final FileChooser fileChooser = new FileChooser();
		//     fileChooser.setTitle("Select XML file");
		//    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));



		Main.addProps(table, "ISBN", "isbn",7);
		Main.addProps(table, "Name", "name",6.5);
		Main.addProps(table, "Genre", "genre",6.5);
		Main.addProps(table, "Widawniztwo", "widawniztwo",6.5);
		Main.addProps(table, "Date", "date",6.5);
		Main.addProps(table, "Authors", "authors",4.2);


		ScrollPane sp = new ScrollPane();
		Button adder = new Button("Add");
		Button deleter = new Button("Delete");
		Button serializer = new Button("Serialize(XML)");
		Button deserializer = new Button("Deserialize(XML)");
		VBox hb= new VBox(adder,deleter,deserializer,serializer);

		String shablonForDate = "\\d{2}[.]\\d{2}[.]\\d{4}";
		String shablonForIsbn = "\\d+-\\d+-\\d+-\\d+-\\d+";

		adder.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				Label wrongInput = new Label();

				Button accept = new Button("Accept");
				TextField isbnAdd = new TextField();
				TextField nameAdd = new TextField();
				TextField dateAdd = new TextField();
				TextField genreAdd = new TextField();
				TextField widawniztwoAdd = new TextField();
				TextField authorsAdd = new TextField();
				HBox isbn_hb = new HBox(new Label("ISBN: "),isbnAdd);
				HBox name_hb = new HBox(new Label("Name: "),nameAdd);
				HBox date_hb = new HBox(new Label("Date: "),dateAdd);
				HBox genre_hb = new HBox(new Label("Genre: "),genreAdd);
				HBox widawniztwo_hb = new HBox(new Label("Widawniztwo: "),widawniztwoAdd);
				HBox authors_hb = new HBox(new Label("Authors: "),authorsAdd);
				hb.getChildren().clear();
				hb.getChildren().addAll(isbn_hb,name_hb,genre_hb,date_hb,widawniztwo_hb,authors_hb,accept,wrongInput);
				accept.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) 
					{
						Book b = new Book();
						ArrayList<String> authors = new ArrayList<String>();
						if(dateAdd.getText().matches(shablonForDate) && isbnAdd.getText().matches(shablonForIsbn))
						{
							b.setDate(dateAdd.getText());
							b.setGenre(genreAdd.getText());
							b.setName(nameAdd.getText());
							b.setIsbn(isbnAdd.getText());
							b.setWidawniztwo(widawniztwoAdd.getText());
							for(String author : authorsAdd.getText().split("  "))
							{
								authors.add(author);
							}
							b.setAuthors(authors);
							table.getItems().add(b);
						}
						else
						{
							wrongInput.setText("Wrong Date or isbn");
						}
					
						//dateAdd.clear();
						genreAdd.clear();
						widawniztwoAdd.clear();
						nameAdd.clear();
						isbnAdd.clear();
						authorsAdd.clear();
						hb.getChildren().clear();
						table.refresh();
						books.add(b);
						hb.getChildren().addAll(adder,deleter,deserializer,serializer,wrongInput);
					}
				});

			}
		}); 

		deleter.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				books.clear();
				ObservableList<Book> elementToDelete,myList;
				myList = table.getItems();
				elementToDelete = table.getSelectionModel().getSelectedItems();
				elementToDelete.forEach(myList::remove);
				for(int i = 0;i<myList.size();i++)
				{
					books.add(myList.get(i));
				}
			}
		});


		serializer.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				if (file != null) 
				{
					try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file))))
					{
						encoder.writeObject((int)books.size());
						Iterator it = books.iterator();
						while(it.hasNext())
						{
							encoder.writeObject(it.next());
						}
						books.clear();
					} catch (FileNotFoundException ex) {
						System.err.println("‘айл не знайдено!");
					}

				}
			}
		});
		deserializer.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				XMLDecoder decoder;
				try {
					decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
					int size = (int)decoder.readObject();
					for(int i = 0; i < size; i++)
					{
						books.add((Book) decoder.readObject());
					}
					toStr(books);
					System.out.println("«читано!");
					decoder.close();
				} catch (FileNotFoundException e) 
				{
					System.out.println("‘айл не знайдено");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		GridPane root = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints(700);
		column1.setHgrow(Priority.ALWAYS);
		root.getColumnConstraints().add(column1);
		ColumnConstraints column2 = new ColumnConstraints(400);
		column2.setHgrow(Priority.ALWAYS);
		root.getColumnConstraints().add(column2);
		root.setColumnIndex(table, 0);
		sp.setContent(hb);
		root.setColumnIndex(sp, 1);

		root.getChildren().addAll(table, sp);

		Scene scene = new Scene(root,1035, 650);
		stage.setScene(scene);

		stage.setTitle("JavaFx App");

		stage.show();
	}
}
