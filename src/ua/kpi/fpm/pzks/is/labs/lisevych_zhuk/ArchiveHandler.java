package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.io.*;
import java.util.List;

import de.innosystec.unrar.*;
import de.innosystec.unrar.rarfile.*;

public class ArchiveHandler {

	public String decompressFile(String filename, String dir) {
		String result = "";
		Archive a = null;
		try {
			File file = new File(filename);
			try {
				a = new Archive(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (a != null) {
				List<FileHeader> headers = a.getFileHeaders();
				for (FileHeader fh : headers) {
					try {
						File out = new File(dir
								+ "/"
								+ fh.getFileNameString().trim()
										.replace("\\", "").toLowerCase());
						FileOutputStream fos = new FileOutputStream(out);
						a.extractFile(fh, fos);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				result = headers.get(0).getFileNameString().trim()
						.replace("\\", "").toLowerCase();

			}
		} finally {
			try {
				a.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}
}
