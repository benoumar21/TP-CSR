package Touteslesressources;

import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class RootRessource extends ServerResource {

	@Get
	public Representation getRoot() {
		return new FileRepresentation("templates/index.html", MediaType.TEXT_HTML); 
	}
}