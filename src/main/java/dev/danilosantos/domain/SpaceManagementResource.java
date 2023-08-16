package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

public class SpaceManagementResource {

    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    public SpaceManagementResource() {
        createFoldersAndFiles.createSpaceManagementFile();
    }

}
