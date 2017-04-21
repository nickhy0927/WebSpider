package org.spring.common.annotation.scan;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.spring.common.annotation.Table;
import org.spring.common.annotation.interfaces.IWhat;

public class ScanPackager {
	
	public static void scanPackage(String iPackage, IWhat what) {
		String path = iPackage.replace(".", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		try {
			if (url != null && url.toString().startsWith("file")) {
				String filePath = URLDecoder.decode(url.getFile(), "utf-8");
				File dir = new File(filePath);
				List<File> fileList = new ArrayList<File>();
				fetchFileList(dir, fileList);
				for (File f : fileList) {
					String fileName = f.getAbsolutePath();
					if (fileName.endsWith(".class")) {
						String nosuffixFileName = fileName.substring(8 + fileName.lastIndexOf("classes"),
								fileName.indexOf(".class"));
						String filePackage = nosuffixFileName.replaceAll("\\\\", ".");
						Class<?> entityClass = Class.forName(filePackage);
						/** 获取表名信息 */
						if (entityClass.isAnnotationPresent(Table.class)) {
							what.execute(f, entityClass);
						}
					} else {
						what.execute(f, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fetchFileList(File dir, List<File> fileList) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				fetchFileList(f, fileList);
			}
		} else {
			fileList.add(dir);
		}
	}
}
