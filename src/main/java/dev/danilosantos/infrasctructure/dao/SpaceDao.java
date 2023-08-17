package dev.danilosantos.infrasctructure.dao;

import com.google.gson.Gson;
import dev.danilosantos.infrasctructure.Space;
import dev.danilosantos.infrasctructure.file_management.Routes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceDao {
    Gson gson = new Gson();

    public void insert(Space space) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.SPACE_FILE_PATH, true))) {
            writer.write(gson.toJson(space));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<Space> readAllSpacesFromDocument() {
        List<Space> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Routes.SPACE_FILE_PATH))) {
            String line = reader.readLine();

            while (line != null) {
                Space space = gson.fromJson(line, Space.class);
                list.add(space);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public void updateDocument(List<Space> spaces) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Routes.SPACE_FILE_PATH))) {

            for (Space entity : spaces) {
                writer.write(gson.toJson(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
