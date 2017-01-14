package database.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Servicesupermarche.Client;
import Servicesupermarche.Rayon;
import database.Database;

public class DatabaseMemoire implements Database{
	
	  /** User count (next id to give).*/
    private int clientCount_;

    /** User Hashmap. */
    Map<Integer, Client> clients_;

    public DatabaseMemoire()
    {
    	clients_ = new HashMap<Integer, Client>();
    }

	/**
     * Crée un client 
     * 
     * @param etat
     * @param listeDesCourses
     * @return
     * @throws InterruptedException
     */
	public synchronized Client restDuClient() throws InterruptedException {
		Client user = new Client(clientCount_);
        user.setIdent(clientCount_);
        clients_.put(clientCount_, user);
        Thread.sleep(100);
        clientCount_++;
        return user;
	}

	public Collection<Client> getClients() {
		 return clients_.values();
	}

	public Client getClient(int id) {
		return clients_.get(id);
	}

	@Override
	public List<Rayon> getRayons() {
		// TODO Auto-generated method stub
		return null;
	}
}
