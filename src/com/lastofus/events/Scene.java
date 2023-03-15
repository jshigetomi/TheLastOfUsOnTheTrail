package com.lastofus.events;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


class Scene {
    private List<String> allLines;

    // Constructor that takes in the scene type and scene number.
    // uses them to initialize path and allLines.
    public Scene(String sceneType, String sceneNumber) {
        try {
            String scene = "S" + sceneNumber;
            String choices = "C" + sceneNumber;

            // assign the path to the scenes folder
            String path = "scenes/" + sceneType + "Scenes.txt";

            allLines = Files.lines(Path.of(path))
                    .filter(line -> line.startsWith(scene) || line.startsWith(choices))
                    .limit(10)
                    // get the substring of the line after the colon
                    .map(line -> line.substring(line.indexOf(":") + 1))
                    .collect(Collectors.toList());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void begin() {
        // CHECK if allLines is null
        if(allLines.isEmpty()) {
            System.out.println("Error: allLines is null");
            return;
        }
        // print each line in allLines
        allLines.forEach(System.out::println);
    }
}