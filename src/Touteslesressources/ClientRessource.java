package Touteslesressources;

import java.util.Map;


import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import Servicesupermarche.Client;
import Servicesupermarche.Produit;
import backend.Backend;

public class ClientRessource extends ServerResource{
	
    private Backend backend_;

   
    private Client client_;

   
    public ClientRessource() 
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    
    @Get("json")
    public Representation getInfoClient() throws Exception
    {
        String clientIdString = (String) getRequest().getAttributes().get("id");       
        int userId = Integer.valueOf(clientIdString);
        client_ = backend_.getDatabase().getClient(userId);
        JSONObject userObject = new JSONObject();
        
        if(client_ == null){
        	 userObject.put("404", "L'id de ce client n'est pas dans mes données !");
        } else {
        	 Map<Produit, Integer> listeCourses = client_.getListeDeCourses();     
             
             userObject.put("ident", client_.getIdent());
             userObject.put("liste", listeCourses);
             userObject.put("status", client_.getStatus());
        }
        
        JsonRepresentation result = new JsonRepresentation(userObject);
        result.setIndenting(true);
        return result;
    } 
}
