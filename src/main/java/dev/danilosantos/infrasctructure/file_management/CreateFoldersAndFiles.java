package dev.danilosantos.infrasctructure.file_management;

import java.io.File;
import java.io.IOException;

public class CreateFoldersAndFiles {

    public boolean create() {
        boolean createFolder = new File(Routes.FOLDER_PATH).mkdir();
        System.out.println(createFolder ? "Pasta Criada no caminho:" + Routes.FOLDER_PATH
                : "Pasta existente no caminho:" + Routes.FOLDER_PATH);

        File file = new File(Routes.MEMBER_FILE_PATH);
        return true;
    }

}
