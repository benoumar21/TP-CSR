package Touteslesressources;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import Servicesupermarche.Rayon;
import backend.Backend;

public class StockRessource extends ServerResource{
	
	
	 /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public StockRessource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }
	/**
     * Returns the user matching the id given in the URI
     * 
     * @return JSON representation of a user
     * @throws JSONException
     */
    @Get("json")
    public Representation getRayons() throws Exception
    {
        List<Rayon> listeRayons = backend_.getDatabase().getRayons();

        JSONObject userObject = new JSONObject();
        userObject.put("rayons", listeRayons);

        JsonRepresentation result = new JsonRepresentation(userObject);
        result.setIndenting(true);
        return result;
    }
}