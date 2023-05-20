package com.filemanagement.controller;

import com.filemanagement.dto.CreateFileRequest;
import com.filemanagement.entity.File;
import com.filemanagement.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/folders/{folderId}/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<File> createFile(
            @PathVariable Long folderId,
            @RequestHeader HttpHeaders httpHeaders,
            @RequestParam("fileData") MultipartFile fileData) {
        File file = fileService.createFile(folderId, fileData);
        return ResponseEntity.status(HttpStatus.CREATED).body(file);
    }
}
