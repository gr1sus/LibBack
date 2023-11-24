package com.libproject.demo.api.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/uploads/")
@RequiredArgsConstructor
public class UploadContoller {
    
    
    private static final String UPLOAD_DIR = "demo/public/";




       @GetMapping("{fileType}/{filePath}")
    public @ResponseBody void getPicture(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileType, @PathVariable String filePath){
        response.reset();
        response.setBufferSize(2048 * 20);
        
        try {
            File file = new File(UPLOAD_DIR + fileType + "/" + filePath);

            //mimetype
            String mimeType = Files.probeContentType(file.toPath());
            response.setContentType(mimeType);

            FileInputStream input = new FileInputStream(file);
            response.getOutputStream().write(input.readAllBytes());
            input.close();
        } catch (IOException e) {
            // Do something
        }
    }

    




   

}
