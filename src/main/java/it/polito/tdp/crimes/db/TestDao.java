package it.polito.tdp.crimes.db;

import it.polito.tdp.crimes.model.Adiacenze;
import it.polito.tdp.crimes.model.Event;

public class TestDao {

	public static void main(String[] args) {
		EventsDao dao = new EventsDao();
		
		  for(Adiacenze e : dao.getAdiacenze("drug-alchol", 1))
			  System.out.println(e.getA1());
		 
		/*
		 * for(Integer e : dao.listAllDate()) System.out.println(e);
		 */
	}

}
