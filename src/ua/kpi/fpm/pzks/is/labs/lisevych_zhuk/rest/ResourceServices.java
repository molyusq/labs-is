package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.FTPHandler;

@Path("/res")
public class ResourceServices implements Serializable {
	private static final long serialVersionUID = 1L;

	@GET
	@Path("vestibules")
	@Produces({ MediaType.APPLICATION_JSON })
	public VestibuleName[] vestibules() {
		System.out.println(System.getProperty("my.project.dir"));
		List<VestibuleName> names = new ArrayList<VestibuleName>();
		FileReader reader = null;
		try {
			reader = new FileReader(new File(
					System.getProperty("my.project.dir") + "/res/nodes.txt"));
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

	@GET
	@Path("scanners")
	@Produces({ MediaType.APPLICATION_JSON })
	public String[] scannerNames(
			@DefaultValue("") @QueryParam("vestibule") String vestibule) {
		FTPHandler handler = new FTPHandler();
		String[] allFiles = handler.getFileList("/ADBK_dump20130225");
		List<String> files = new ArrayList<String>();
		for (String f : allFiles) {
			if (f.substring(5, 10).toLowerCase()
					.equals(vestibule.toLowerCase())) {
				files.add(f);
			}
		}
		String[] result = new String[files.size()];
		files.toArray(result);
		return result;
	}

	@GET
	@Path("loadfile")
	public String loadFile(@DefaultValue("") @QueryParam("file") String file) {
		try {
			new FTPHandler().getFileFromFTP(file,
					System.getProperty("my.project.dir") + "/runtime");
			File f = new File(System.getProperty("my.project.dir")
					+ "/runtime/" + file);
			BasicFileAttributes attributes = Files.readAttributes(f.toPath(),
					BasicFileAttributes.class);
			FileTime creationTime = attributes.creationTime();
			return creationTime.toString().substring(0, 10) + " "
					+ creationTime.toString().substring(11, 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("unzip")
	public String unzipFile(@DefaultValue("") @QueryParam("file") String file) {
		return "unzipped";
	}

}
