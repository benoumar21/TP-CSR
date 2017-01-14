package app;

import java.io.File;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import Touteslesressources.ClientRessource;
import Touteslesressources.ClientsRessource;
import Touteslesressources.RootRessource;
import Touteslesressources.StockRessource;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	public App(Context context)
    {
        super(context);
    }
	
    @Override
    public Restlet createInboundRoot()
    {
    	File staticDirectory = new File("static/");
    	Directory directory = new Directory(getContext(), "file:///" + staticDirectory.getAbsolutePath() + "/");
    	directory.isDeeplyAccessible();
    	directory.isListingAllowed();
    	    	
    	  Router router = new Router(getContext());
          router.attach("/", RootRessource.class);
          router.attach("/static", directory);
          router.attach("/supermarche/clients", ClientsRessource.class);
          router.attach("/supermarche/clients/", ClientsRessource.class);
          router.attach("/supermarche/clients/{id}", ClientRessource.class);
          router.attach("/supermarche/stock", StockRessource.class);
        return router;
    }
}
