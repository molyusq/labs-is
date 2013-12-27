package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.rest;

import java.io.Serializable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.SqliteHandler;

@Path("/sqlite")
public class SqliteServices implements Serializable {
	private static final long serialVersionUID = 1L;

	@GET
	@Path("nums")
	@Produces({ MediaType.APPLICATION_JSON })
	public String[] getCardNumbers(
			@DefaultValue("base.db3") @QueryParam("db") String db) {
		String path = System.getProperty("my.project.dir") + "/runtime/" + db;
		SqliteHandler handler = SqliteHandler.getInstance();
		return handler.getCardNumbers(path);
	}

}
