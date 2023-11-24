package com.libproject.demo.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUpoadsUtil {
     public static void saveFile(MultipartFile file, String directory){
        if (file.isEmpty()) {
            System.out.println("no file");
        }
        try{
            String fileName = file.getOriginalFilename();
            Path filePath = Path.of(directory + fileName);            

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            System.out.println(e);
        }    
    }
}
