package database;

import java.util.Collection;
import java.util.List;

import Servicesupermarche.Client;
import Servicesupermarche.Rayon;

public interface Database {
	Client restDuClient()  throws InterruptedException;

   Collection<Client> getClients();

   Client getClient(int id);

   List<Rayon> getRayons();


}
