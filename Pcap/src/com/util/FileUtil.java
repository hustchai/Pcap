package com.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 验证文件是否存在
	 * @param file 文件路径
	 * @return 是否存在
	 */
	public static boolean checkIsExist(File file) {
		
		if(file.exists()) {
			return true;
		}
		return false;
	}

}
