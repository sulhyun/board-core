package com.boardcore.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtilsV2 {
	
	public static String uploadFile(String uploadPath, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalName = file.getOriginalFilename();
        String savedName = UUID.randomUUID().toString() + "_" + originalName;
        
        // 날짜별 경로 계산 (예: /2026/09/11)
        String datePath = calcPath(uploadPath);

        // 저장할 실제 파일 객체 생성
        File target = new File(uploadPath + datePath, savedName);

        // [핵심] transferTo를 사용하여 메모리 낭비 없이 직접 파일 저장
        file.transferTo(target);

        // DB에 저장할 웹 경로 반환 (/2026/09/11/UUID_파일명)
        return (datePath + "/" + savedName).replace(File.separatorChar, '/');
    }

    private static String calcPath(String uploadPath) {
        // LocalDate를 사용하여 날짜 경로를 간결하게 생성
        LocalDate now = LocalDate.now();
        String datePath = now.format(DateTimeFormatter.ofPattern("/yyyy/MM/dd"));

        // File.mkdirs()를 사용하면 하위 폴더까지 한 번에 생성됨
        File dir = new File(uploadPath + datePath);
        if (!dir.exists()) {
            dir.mkdirs(); 
        }

        return datePath;
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
