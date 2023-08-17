package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.dao.SpaceDao;

public class SpaceResource {
    SpaceDao spaceDao = new SpaceDao();

    public boolean insert(Space space) {
        if (space != null) {
            spaceDao.insert(space);
            return true;
        }
        return false;
    }
}
