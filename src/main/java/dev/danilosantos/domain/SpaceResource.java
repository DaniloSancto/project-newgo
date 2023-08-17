package dev.danilosantos.domain;

import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.dao.SpaceDao;

import java.util.List;

public class SpaceResource {
    private final SpaceDao spaceDao = new SpaceDao();

    // insere o espaço no documento
    public boolean insert(Space space) {
        if (space != null) {
            spaceDao.insert(space);
            return true;
        }
        return false;
    }

    // atualiza o documento de Espaço
    public void updateDocument(List<Space> list) {
        spaceDao.updateDocument(list);
    }

    // busca todos os Espaços do documento
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
