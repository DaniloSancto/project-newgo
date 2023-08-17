package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.dao.SpaceDao;

import java.util.List;

public class SpaceResource {
    SpaceDao spaceDao = new SpaceDao();

    public boolean insert(Space space) {
        if (space != null) {
            spaceDao.insert(space);
            return true;
        }
        return false;
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
