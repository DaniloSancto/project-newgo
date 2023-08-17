package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;

import java.util.ArrayList;
import java.util.List;

public class SpaceManagementResource {
    List<Space> listOfSpaces = new ArrayList<>();
    SpaceManagement spaceManagement = new SpaceManagement();
    SpaceResource spaceResource = new SpaceResource();
    CreateFoldersAndFiles createFoldersAndFiles = new CreateFoldersAndFiles();

    public SpaceManagementResource() {
        if (createFoldersAndFiles.createSpaceFile()) {
            updateDocument(baseSpaces());
        }
        if (!createFoldersAndFiles.createSpaceManagementFile()) {
            if (getAllSpacesFromDocument() != null) {
                listOfSpaces.addAll(getAllSpacesFromDocument());
            }
        }

    }

    public String insertNewSpace(SpaceCategory category, String name, Integer maxCapacity) {
        if (category != null && name != null) {
            Space space = new Space(category, name, maxCapacity);
            listOfSpaces.add(space);
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
        return listOfSpaces;
    }

    private List<Space> baseSpaces() {
        List<Space> list = new ArrayList<>();
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de futebol indoor", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de vôlei de praia", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "quadra de beach tennis", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "campos de golfe 1", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "campos de golfe 2", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 1", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 2", 25));
        list.add(new Space(SpaceCategory.RECREACAO, "lago com pedalinhos", 25));
        list.add(new Space(SpaceCategory.RECREACAO, "jardim botânico", 25));
        list.add(new Space(SpaceCategory.ESPORTES, "academia", 25));
        list.add(new Space(SpaceCategory.RELAXAMENTO, "spá", 25));
        list.add(new Space(SpaceCategory.RECREACAO, "área para churrasco", 25));
        list.add(new Space(SpaceCategory.RECREACAO, "parque infantil", 25));
        return list;
    }
}
