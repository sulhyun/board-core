package com.boardcore.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtilsV2 {
	
	public static String uploadFile(String uploadPath, MultipartFile file) throws IOException {
		UUID uid = UUID.randomUUID();
		String originalName = file.getOriginalFilename();			
		
		String savedName = uid.toString() + "_" + originalName;
        String savedPath = calcPath(uploadPath);

        File target = new File(uploadPath + savedPath, savedName);
        file.transferTo(target);
        
        String uploadFileName = getFileName(savedPath, savedName);

        return uploadFileName;
    }

    private static String calcPath(String uploadPath) {
        LocalDate now = LocalDate.now();
        String datePath = now.format(DateTimeFormatter.ofPattern("/yyyy/MM/dd"));

        File dir = new File(uploadPath + datePath);
        if (!dir.exists()) {
            dir.mkdirs(); 
        }

        return datePath;
    }
    
    private static String getFileName(String path, String fileName) {
		String filePath = path + File.separator + fileName;
        return filePath.replace(File.separatorChar, '/');
	}

    public static void deleteFile(String uploadPath, String fileName) {
        if (fileName == null) return;

        String path = fileName.replace('/', File.separatorChar);
        File file = new File(uploadPath + path);

        if (file.exists()) {
            file.delete();
        }
    }
    
}
