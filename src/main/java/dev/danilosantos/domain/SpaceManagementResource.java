package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

import java.util.ArrayList;
import java.util.List;

public class SpaceManagementResource {
    SpaceManagement spaceManagement = new SpaceManagement();
    SpaceResource spaceResource = new SpaceResource();
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    public SpaceManagementResource() {
        if (!createFoldersAndFiles.createSpaceFile()) {
            spaceManagement.getSpaces().addAll(baseSpaces());
        } else {
            updateDocument(baseSpaces());
        }
        if (!createFoldersAndFiles.createSpaceManagementFile()) {
            if (getAllSpacesFromDocument() != null) {
                spaceManagement.getSpaces().addAll(getAllSpacesFromDocument());
            }
        }

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

    public void updateDocument(List<Space> list) {
        spaceResource.updateDocument(list);
    }

    private List<Space> getAllSpacesFromDocument() {
        return spaceResource.getAllSpacesFromDocument();
    }

    public List<Space> getAllSpaces() {
        return spaceManagement.getSpaces();
    }

    private List<Space> baseSpaces() {
        List<Space> list = new ArrayList<>();
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de futebol indoor"));
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de vôlei de praia"));
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de beach tennis"));
        list.add(new Space(SpaceCategory.ESPORTES, "campos de golfe 1"));
        list.add(new Space(SpaceCategory.ESPORTES, "campos de golfe 2"));
        list.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 1"));
        list.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 2"));
        list.add(new Space(SpaceCategory.RECREACAO, "lago com pedalinhos"));
        list.add(new Space(SpaceCategory.RECREACAO, "jardim botânico"));
        list.add(new Space(SpaceCategory.ESPORTES, "academia"));
        list.add(new Space(SpaceCategory.RELAXAMENTO, "spá"));
        list.add(new Space(SpaceCategory.RECREACAO, "área para churrasco"));
        list.add(new Space(SpaceCategory.RECREACAO, "parque infantil"));
        return list;
    }
}
