package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity						//declares the java program as a persist object
@Table(name="items")		//declares a table name to manipulate, I found that you have to make it on the server first.
public class ListItem {
	@Id						//the next variable will be the primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//the program will autogenerate the primay key
	@Column(name="ID")								//the first column will be called ID
	private int id;									//the instance variable that goes along with the ID column
	@Column(name="STORE")
	private String store;
	@Column(name="ITEM")
	private String item;

	public ListItem() {							//a requirement of an Entity is to have a constructor with no args
		// TODO Auto-generated constructor stub
	}

	public ListItem(String store, String item) {//when args are wanted for the program, a second constructor is made
		this.store = store;						//the parameter vars are saved as the private entity vars
		this.item = item;
	}

	public int getId() {						// vv CRUD
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override						//overrides the basic variable.toString function, so it should be marked
	public String toString() {
		return "ListItem [id=" + id + ", store=" + store + ", item=" + item + "]";
	}
	
	public String returnItemDetails() {
		return store + ": " + item;
	}

}
