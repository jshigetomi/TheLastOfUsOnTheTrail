package com.lastofus.events;

import com.apps.util.Prompter;

import java.util.Locale;
import java.util.Scanner;

class Scene {
    private String message;

    Scene(String newMessage) {
        this.message = newMessage;
    }

    public void begin() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String input = prompter.prompt("Enter a letter: ", "A|B|C|D","\nEnter valid letter!\n");
        System.out.println(input);
    }
}