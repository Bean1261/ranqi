package com.weichai.ranqi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    private static final String UPLOADED_FOLDER = System.getProperty("user.dir") + "/upload/";

    @PostMapping("/upload")
    public String upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return "上传失败，文件为空";
        }
        System.out.println("文件大小：" + file.getSize());
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件名：" + file.getOriginalFilename());
        System.out.println("当前工作目录：" + System.getProperty("user.dir"));
        saveFile(file);
        return "上传成功";
    }

    private void saveFile(MultipartFile file) throws IOException {
        File upDir = new File(UPLOADED_FOLDER);
        if (!upDir.exists()) {
            upDir.mkdir();
        }
        File destinationFile = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        file.transferTo(destinationFile);
    }
}
