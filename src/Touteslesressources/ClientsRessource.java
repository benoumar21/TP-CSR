package Touteslesressources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import Servicesupermarche.Client;
import backend.Backend;

public class ClientsRessource extends ServerResource{
	 /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public ClientsRessource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     * Returns the list of all the clients
     *
     * @return  JSON representation of the clients
     * @throws JSONException
     */
    @Get("json")
    public Representation obtenirClient() throws JSONException
    {
        Collection<Client> clients = backend_.getDatabase().getClients();
        Collection<JSONObject> jsonUsers = new ArrayList<JSONObject>();

        for (Client client : clients)
        {
            JSONObject current = new JSONObject();
            current.put("id", client.getIdent());
            current.put("url", getReference().toString() + client.getIdent());
            jsonUsers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonUsers);
        JsonRepresentation result = new JsonRepresentation(jsonArray);
        result.setIndenting(true);
        return result;
    }

    /**
     * Create a user with the data present in the json representation
     * 
     * @param json representation of the user to create
     * @return JSON representation of the newly created user
     * @throws JSONException
     */
    @Post("json")
    public Representation creerUnUsers(JsonRepresentation representation)
        throws Exception
    {
        // Save the user
        Client client = backend_.getDatabase().restDuClient();

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("id", client.getIdent());
        resultObject.put("etat", client.getStatus());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }
}
