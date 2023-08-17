package dev.danilosantos.infrasctructure.file_management;

import java.io.File;
import java.io.IOException;

public class CreateFoldersAndFiles {

    public boolean createFolderAndMemberFile() {
        boolean createFolder = new File(Routes.FOLDER_PATH).mkdir();
        System.out.println(createFolder ? "Pasta Criada no caminho:" + Routes.FOLDER_PATH
                : "Pasta existente no caminho:" + Routes.FOLDER_PATH);

        File file = new File(Routes.MEMBER_FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean createSpaceManagementFile() {
        File spaceFile = new File(Routes.SPACE_FILE_PATH);
        File spaceManagementFile = new File(Routes.SPACE_MANAGEMENT_FILE_PATH);
        if (!spaceFile.exists()) {
            try {
                spaceFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else if (!spaceManagementFile.exists()) {
            try {
                spaceManagementFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            return false;
        }
        return true;
    }
}
