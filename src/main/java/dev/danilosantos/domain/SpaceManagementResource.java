package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.SpaceManagement;
import dev.danilosantos.infrasctructure.dao.SpaceManagementDao;
import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.file_management.CreateFoldersAndFiles;
import dev.danilosantos.infrasctructure.util.DateFormat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpaceManagementResource {
    private final SpaceManagementDao spaceManagementDao = new SpaceManagementDao();
    private final List<Space> listOfSpaces = new ArrayList<>();
    private final SpaceResource spaceResource = new SpaceResource();
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

    public String registerUse(int space, String memberCardNumber, Date date,Date timeEnter, Integer timeInUse) {
        if (listOfSpaces.get(space - 1) != null && date != null && timeEnter != null && timeInUse != null) {
            SpaceManagement spaceManagement = new SpaceManagement(listOfSpaces.get(space - 1), memberCardNumber, date, timeEnter, timeInUse);
            spaceManagementDao.insert(spaceManagement);
            return "Uso registrado com sucesso!";
        } else {
            return "*ERRO: falha ao registrar uso*";
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
        return "ERRO: *Falha ao adicionar espaço*";
    }

    public Date stringToDate(String date) {
        try {
            return DateFormat.date.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date stringToTime(String time) {
        try {
            return DateFormat.time.parse(time);
        } catch (ParseException e) {
            return null;
        }
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
