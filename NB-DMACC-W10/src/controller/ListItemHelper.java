package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

public class ListItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleShoppingList");
	
	public void cleanUp(){	//close the EntityManagerFactory plugin
		emfactory.close();
	}
	
	public void insertItem(ListItem li){	//recieves the Entity and Entity input, then writes it to the EntityManager
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void deleteItem(ListItem toDelete) {			//removes the inputted entity from the sql list
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.store = :selectedStore and li.item = :selectedItem", ListItem.class);
		typedQuery.setParameter("selectedStore", toDelete.getStore());	//finds a row with the 2 parameters
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		typedQuery.setMaxResults(1);									//deletes only the first result
		ListItem result = typedQuery.getSingleResult();
		em.remove(result);
		
		em.getTransaction().commit();			//saves the change to the table
		em.close();
		
	}
	
	public List<ListItem> searchForItemByItem(String itemName) {	//self-explanatory
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.item = :selectedItem", ListItem.class);
		typedQuery.setParameter("selectedItem", itemName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListItem> searchForItemByStore(String storeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.store = :selectedStore", ListItem.class);
		typedQuery.setParameter("selectedStore", storeName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public ListItem searchForItemById(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, id);
		em.close();
		return found;
	}

	public List<ListItem> showAllItems(){		//sql query for all the data from the table
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li", ListItem.class);
		List<ListItem> allItems = typedQuery.getResultList();
		em.close();
		return allItems;
	}

	public void updateItem(ListItem toEdit) {		//the entity that the function receives, is mixed with the entry on the table
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	
}
