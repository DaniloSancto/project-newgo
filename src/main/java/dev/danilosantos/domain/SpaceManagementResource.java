package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

public class SpaceManagementResource {
    SpaceManagement spaceManagement;
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    public SpaceManagementResource() {
        createFoldersAndFiles.createSpaceManagementFile();
    }

    public String insertNewSpace(SpaceCategory category, String name) {
        if (category != null && name != null) {
            spaceManagement.getSpaces().add(new Space(category, name));
            return "Espaço adicionado com sucesso!";
        }
        return "Falha ao adicionar espaço";
    }
}
