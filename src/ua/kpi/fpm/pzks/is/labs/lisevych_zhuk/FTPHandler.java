package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.io.*;

import org.apache.commons.net.ftp.*;

public class FTPHandler {
	private FTPClient client = new FTPClient();
	private boolean login;

	public String[] getFileList(String directory){
		try {
			client.connect("ftp.dit.in.ua");
			login = client.login("student", "student");
			if(login) {	
				FTPFile[] fileList = client.listFiles("/ADBK_dump20130225");
				String[] stringList = new String[fileList.length];
				for(int i = 0; i < fileList.length; i++) {
					stringList[i] = fileList[i].getName();
				}
				return stringList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.logout();
				client.disconnect();
				System.out.println("disconnected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new String[1];
	}

	public void getFileFromFTP(String filename, String destinationFile) {
		try {
			client.connect("ftp.dit.in.ua");
			login = client.login("student", "student");
			if(login) {
				client.changeWorkingDirectory("/ADBK_dump20130225");
				File file = new File(filename);
				File dir = new File(destinationFile);
				OutputStream os = new BufferedOutputStream(new FileOutputStream(dir));
				client.retrieveFile(file.getName(), os);
				os.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.logout();
				client.disconnect();
				System.out.println("disconnected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
