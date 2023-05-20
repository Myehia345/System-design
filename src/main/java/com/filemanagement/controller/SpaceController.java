package com.filemanagement.controller;

import com.filemanagement.dto.CreateSpaceRequest;
import com.filemanagement.entity.Item;
import com.filemanagement.service.SpaceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping
    public ResponseEntity<Item> createSpace(@RequestBody CreateSpaceRequest request,
                                            @RequestHeader HttpHeaders httpHeaders) {
        Item space = spaceService.createSpace(request.getName(), request.getGroupName());
        return ResponseEntity.status(HttpStatus.CREATED).body(space);
    }
}