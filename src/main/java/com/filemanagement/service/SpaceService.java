package com.filemanagement.service;

import com.filemanagement.entity.Item;
import com.filemanagement.entity.PermissionGroup;
import com.filemanagement.enums.ItemType;
import com.filemanagement.repository.ItemRepository;
import com.filemanagement.repository.PermissionGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpaceService {
    private final ItemRepository itemRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    public SpaceService(ItemRepository itemRepository, PermissionGroupRepository permissionGroupRepository) {
        this.itemRepository = itemRepository;
        this.permissionGroupRepository = permissionGroupRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Item createSpace(String name, String groupName) {
        Item space = new Item();
        try {
            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setGroupName(groupName);
            permissionGroupRepository.save(permissionGroup);


            space.setType(ItemType.SPACE.getName());
            space.setName(name);
            space.setPermissionGroup(permissionGroup);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return itemRepository.save(space);
    }
}