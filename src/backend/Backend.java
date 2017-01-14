package backend;

import database.Database;
import database.impl.DatabaseMemoire;

public class Backend {
	/** Database.*/
    private Database database_;

    public Backend()
    {
        database_ = new DatabaseMemoire();
    }

    public Database getDatabase()
    {
        return database_;
    }
}
