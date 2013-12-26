package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/res")
public class ResourceServices implements Serializable {
	private static final long serialVersionUID = 1L;

	@GET
	@Path("vestibules")
	@Produces({ MediaType.APPLICATION_JSON })
	public VestibuleName[] vestibules() {
		File f = new File(".");
		System.out.println(System.getProperty("my.project.dir"));
		List<VestibuleName> names = new ArrayList<VestibuleName>();
		FileReader reader = null;
		try {
			reader = new FileReader(new File(System.getProperty("my.project.dir")+"/res/nodes.txt"));
			BufferedReader br = new BufferedReader(reader);
			String row = br.readLine();
			while (row != null) {
				String[] rowComponents = row.trim().split(";");
				names.add(new VestibuleName(rowComponents[0], rowComponents[1]));
				row = br.readLine();
			}
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		VestibuleName[] result = new VestibuleName[names.size()];
		names.toArray(result);
		return result;
	}
}
