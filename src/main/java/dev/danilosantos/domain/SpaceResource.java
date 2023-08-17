package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.dao.SpaceDao;

import java.util.List;

public class SpaceResource {
    private final SpaceDao spaceDao = new SpaceDao();

    public boolean insert(Space space) {
        if (space != null) {
            spaceDao.insert(space);
            return true;
        }
        return false;
    }

    public void updateDocument(List<Space> list) {
        spaceDao.updateDocument(list);
    }

    public List<Space> getAllSpacesFromDocument() {
        List<Space> list = spaceDao.readAllSpacesFromDocument();
        if(!list.isEmpty()) {
            return list;
        }
        else {
            return null;
        }
    }
}
