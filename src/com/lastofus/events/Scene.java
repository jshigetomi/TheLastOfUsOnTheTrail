package com.lastofus.events;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import com.apps.util.Console;


class Scene {
    private List<String> allLines;
    private String prompt;
    private String[] choices;

    public Scene(String sceneType, String sceneNumber) {
        try {
            String scene = "S" + sceneNumber;
            String choices = "C" + sceneNumber;
            String path = "scenes/" + sceneType + "Scenes.txt";

            allLines = Files.lines(Path.of(path))
                    .filter(line -> line.startsWith(scene) || line.startsWith(choices))
                    .limit(5)
                    .map(line -> line.substring(4))
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