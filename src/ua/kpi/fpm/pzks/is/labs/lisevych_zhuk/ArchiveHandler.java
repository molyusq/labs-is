package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.io.*;
import java.util.List;

import de.innosystec.unrar.*;
import de.innosystec.unrar.rarfile.*;

public class ArchiveHandler {
	
	public void decompressFile(String filename) {
		File file = new File(filename);
		Archive a = null;
		try {
			a = new Archive(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(a != null) {
			List<FileHeader> headers = a.getFileHeaders();
			System.out.println(headers.toString());
			for(FileHeader fh : headers) {
				try {
				File out = new File(fh.getFileNameString().trim());
				FileOutputStream fos = new FileOutputStream(out);
				a.extractFile(fh, fos);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
