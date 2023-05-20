package com.filemanagement.controller;

import com.filemanagement.dto.CreateFolderRequest;
import com.filemanagement.entity.Item;
import com.filemanagement.service.FolderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaces/{spaceId}/folders")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<Item> createFolder(
            @PathVariable Long spaceId,
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody CreateFolderRequest request) {
        String user = httpHeaders.get("userName").toString();
        Item folder = folderService.createFolder(request.getName(), spaceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(folder);
    }
}