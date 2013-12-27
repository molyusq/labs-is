package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.io.*;

import org.apache.commons.net.ftp.*;

public class FTPHandler {
	private FTPClient client = new FTPClient();
	private boolean login;

	public String[] getFileList(String directory) {
		try {
			client.connect("ftp.dit.in.ua");
			login = client.login("student", "student");
			if (login) {
				FTPFile[] fileList = client.listFiles(directory);
				String[] stringList = new String[fileList.length];
				for (int i = 0; i < fileList.length; i++) {
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

	public void getFileFromFTP(String filename, String destinationDirectory) {
		try {
			client.connect("ftp.dit.in.ua");
			login = client.login("student", "student");
			if (login) {
				client.setFileType(FTP.BINARY_FILE_TYPE);
				File dir = new File(destinationDirectory + "/" + filename);
				if (dir.exists()) {
					return;
				}
				FileOutputStream fos = new FileOutputStream(dir);
				OutputStream os = new BufferedOutputStream(fos);
				client.retrieveFile("/ADBK_dump20130225/" + filename, os);
				os.close();
				fos.close();
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
	}
}
