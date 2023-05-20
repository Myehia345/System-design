package com.filemanagement.service;

import com.filemanagement.entity.File;
import com.filemanagement.entity.Item;
import com.filemanagement.enums.ItemType;
import com.filemanagement.repository.FileRepository;
import com.filemanagement.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
public class FileService {
    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;

    public FileService(ItemRepository itemRepository, FileRepository fileRepository) {
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public File createFile(Long folderId, MultipartFile fileData) {
        File fileEntity = new File();
        try {
            String fileName ="";
            if(fileData != null)
                fileName = StringUtils.cleanPath(fileData.getOriginalFilename());

            Item folder = itemRepository.findById(folderId).orElseThrow(()
                    -> new EntityNotFoundException("Folder not found"));

            // Check if the user has EDIT access to the folder
            // Perform necessary authorization checks

            Item file = new Item();

            file.setType(ItemType.FILE.getName());
            file.setName(fileName);
            file.setPermissionGroup(folder.getPermissionGroup());
            itemRepository.save(file);


            fileEntity.setBinary(fileData.getBytes());
            fileEntity.setItem(file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return fileRepository.save(fileEntity);
    }

}