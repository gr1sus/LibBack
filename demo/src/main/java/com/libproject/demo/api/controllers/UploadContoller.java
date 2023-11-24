package com.libproject.demo.api.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
    
    private static final String UPLOAD_DIR_BOOKS = "demo/public/books/";
    private static final String UPLOAD_DIR_IMAGE_BOOKS = "demo/public/imageBooks/";
    private static final String UPLOAD_DIR_IMAGE_AUTHOR = "demo/public/imageAuthor/";
    private static final String UPLOAD_DIR_IMAGE_USER = "demo/public/imageUser/";


    @GetMapping("/books/{filePath}")
    public @ResponseBody void getBook(HttpServletRequest request, HttpServletResponse response, @PathVariable String filePath){
        response.reset();
        response.setBufferSize(2048 * 20);
        response.setContentType("application/pdf");

        try {
            File file = new File(UPLOAD_DIR_BOOKS + filePath);
            FileInputStream input = new FileInputStream(file);
            response.getOutputStream().write(input.readAllBytes());
            input.close();
        } catch (IOException e) {
            // Do something
        }
    }

    @GetMapping("/imageBooks/{filePath}")
    public @ResponseBody void getImageBook(HttpServletRequest request, HttpServletResponse response, @PathVariable String filePath){
        response.reset();
        response.setBufferSize(2048 * 20);
        response.setContentType("image/jpg");


        try {
            File file = new File(UPLOAD_DIR_IMAGE_BOOKS + filePath);
            FileInputStream input = new FileInputStream(file);
            response.getOutputStream().write(input.readAllBytes());
            input.close();
        } catch (IOException e) {
            // Do something
        }
    }

    @GetMapping("/imageAuthor/{filePath}")
    public @ResponseBody void getImageAuthor(HttpServletRequest request, HttpServletResponse response, @PathVariable String filePath){
        response.reset();
        response.setBufferSize(2048 * 20);
        response.setContentType("image/jpg");


        try {
            File file = new File(UPLOAD_DIR_IMAGE_AUTHOR + filePath);
            FileInputStream input = new FileInputStream(file);
            response.getOutputStream().write(input.readAllBytes());
            input.close();
        } catch (IOException e) {
            // Do something
        }
    }

    @GetMapping("/imageUser/{filePath}")
    public @ResponseBody void getImageUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String filePath){
        response.reset();
        response.setBufferSize(2048 * 20);
        response.setContentType("image/jpg");


        try {
            File file = new File(UPLOAD_DIR_IMAGE_USER + filePath);
            FileInputStream input = new FileInputStream(file);
            response.getOutputStream().write(input.readAllBytes());
            input.close();
        } catch (IOException e) {
            // Do something
        }
    }


}
