package com.weichai.ranqi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weichai.ranqi.utils.Result;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error().message("上传文件为空");
        }

        // 处理文件保存逻辑
        String fileName = file.getOriginalFilename();
        String filePath = "path/to/save/directory/";

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return Result.ok().message("文件上传成功").data("fileName", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error().message("文件上传失败");
        }
    }
}
