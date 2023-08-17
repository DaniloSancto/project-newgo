package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

public class SpaceManagementResource {
    SpaceManagement spaceManagement = new SpaceManagement();
    SpaceResource spaceResource = new SpaceResource();
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    public SpaceManagementResource() {
        createFoldersAndFiles.createSpaceManagementFile();
    }

    public String insertNewSpace(SpaceCategory category, String name) {
        if (category != null && name != null) {
            Space space = new Space(category, name);
            spaceManagement.getSpaces().add(space);
            if (spaceResource.insert(space)) {
                return "Espaço: " + space.getName() + " adicionado com sucesso!";
            }
        }
        return "Falha ao adicionar espaço";
    }
}
