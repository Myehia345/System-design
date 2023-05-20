package com.filemanagement.service;

import com.filemanagement.entity.Item;
import com.filemanagement.entity.PermissionGroup;
import com.filemanagement.enums.ItemType;
import com.filemanagement.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;

@Service
public class FolderService {
    private final ItemRepository itemRepository;

    public FolderService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Item createFolder(String name, Long spaceId) {
        Item folder = new Item();
        try {
            Item space = itemRepository.findById(spaceId)
                    .orElseThrow(() -> new EntityNotFoundException("Space not found"));

            PermissionGroup  permissionGroup = space.getPermissionGroup();
            // Check if the user has EDIT access to the space
            // Perform necessary authorization checks


            folder.setType(ItemType.FOLDER.getName());
            folder.setName(name);
            folder.setPermissionGroup(permissionGroup);

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return itemRepository.save(folder);
    }
}
