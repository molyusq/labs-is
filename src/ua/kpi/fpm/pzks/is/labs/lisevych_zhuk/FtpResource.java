package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.net.ftp.*;

@Path("ftp")
public class FtpResource {
	
	
	@GET()
	@Produces("text/plain")
	public String sayHello() {
	    return "Hello World!";
	}
}
