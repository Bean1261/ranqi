package com.weichai.ranqi.controllers;

import com.weichai.ranqi.entity.SafetyLedger;
import com.weichai.ranqi.service.SafetyLedgerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ledger")
public class SafetyLedgerController {

    private final SafetyLedgerService service;

    @Value("${upload.path:/var/uploads}")
    private String uploadPath;

    public SafetyLedgerController(SafetyLedgerService service) {
        this.service = service;
    }
//    /**
//     * 图片获取接口
//     * 根据路径读取图片并返回给前端
//     *
//     * @param path 图片路径
//     * @return 图片内容
//     */
//    @GetMapping("/image")
//    public ResponseEntity<byte[]> getImage(@RequestParam("path") String path) {
//        try {
//            // 构建文件路径
//            Path filePath = Paths.get(uploadPath, path).normalize();
//            File imageFile = filePath.toFile();
//
//            // 检查文件是否存在
//            if (!imageFile.exists()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//
//            // 读取图片文件内容
//            byte[] imageBytes = Files.readAllBytes(filePath);
//
//            // 获取文件扩展名并设置响应的 MIME 类型
//            String fileExtension = getFileExtension(imageFile);
//            MediaType mediaType = getMediaType(fileExtension);
//
//            return ResponseEntity.ok()
//                    .contentType(mediaType)  // 设置正确的 MIME 类型
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageFile.getName() + "\"")
//                    .body(imageBytes);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    /**
//     * 获取文件的扩展名
//     * @param file 文件
//     * @return 文件扩展名
//     */
//    private String getFileExtension(File file) {
//        String fileName = file.getName();
//        int index = fileName.lastIndexOf(".");
//        if (index > 0) {
//            return fileName.substring(index + 1);
//        }
//        return "";
//    }
//
//    /**
//     * 根据文件扩展名返回合适的 MIME 类型
//     * @param extension 文件扩展名
//     * @return MIME 类型
//     */
//    private MediaType getMediaType(String extension) {
//        switch (extension.toLowerCase()) {
//            case "jpg":
//            case "jpeg":
//                return MediaType.IMAGE_JPEG;
//            case "png":
//                return MediaType.IMAGE_PNG;
//            case "gif":
//                return MediaType.IMAGE_GIF;
////            case "bmp":
////                return MediaType.IMAGE_BMP;
//            default:
//                return MediaType.APPLICATION_OCTET_STREAM; // 默认二进制流
//        }
//    }


    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 文件的访问路径或错误信息
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 1. 检查上传文件是否为空
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty. Please upload a valid file.");
            }

            // 2. 获取上传文件的原始文件名
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                return ResponseEntity.badRequest().body("Invalid file format. Please upload a file with a valid extension.");
            }

            // 3. 提取文件扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if (extension.isEmpty()) {
                return ResponseEntity.badRequest().body("File extension is missing. Please provide a valid file.");
            }

            // 4. 创建按日期分类的子目录
            String dateDir = LocalDate.now().toString(); // 当前日期
            File uploadDir = new File(uploadPath, dateDir); // 日期子目录
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdirs();
                if (!dirCreated) {
                    return ResponseEntity.status(500).body("Failed to create directory: " + uploadDir.getAbsolutePath());
                }
            }

            // 5. 使用 UUID 生成唯一文件名
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // 6. 拼接文件完整存储路径
            File destFile = new File(uploadDir, uniqueFilename);

            // 7. 保存文件到目标路径
            file.transferTo(destFile);

            // 8. 构造文件的访问路径
            String accessPath = "url:/upload/" + dateDir + "/" + uniqueFilename;
            return ResponseEntity.ok(accessPath); // 返回文件的访问路径

        } catch (IOException e) {
            // 捕获文件保存过程中的异常，返回错误信息
            return ResponseEntity.status(500).body("File upload failed. Error: " + e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常
            return ResponseEntity.status(500).body("Unexpected error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/save")
    public ResponseEntity<SafetyLedger> saveLedger(@RequestBody SafetyLedger ledger) {
        SafetyLedger savedLedger = service.saveLedger(ledger);
        return ResponseEntity.ok(savedLedger);
    }

    /**
     * 查询隐患数据
     *
     * @param keyword 关键字（可选）
     * @return 符合条件的隐患列表
     */
    @GetMapping
    public ResponseEntity<List<SafetyLedger>> getLedgers(@RequestParam(value = "keyword", required = false) String keyword) {
        List<SafetyLedger> ledgers;
        if (keyword == null || keyword.isEmpty()) {
            ledgers = service.getAllLedgers();
        } else {
            ledgers = service.searchLedgers(keyword);
        }
        return ResponseEntity.ok(ledgers);
    }

    /**
     * 根据 ID 删除隐患数据
     *
     * @param id 隐患记录 ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLedger(@PathVariable Long id) {
        boolean deleted = service.deleteLedgerById(id);
        if (deleted) {
            return ResponseEntity.ok("Ledger deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Ledger not found.");
        }
    }

    /**
     * 根据 ID 获取单条隐患数据
     *
     * @param id 隐患记录 ID
     * @return 隐患详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<SafetyLedger> getLedgerById(@PathVariable Long id) {
        Optional<SafetyLedger> ledger = service.getLedgerById(id);
        return ledger.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}