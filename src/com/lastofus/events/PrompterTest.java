package com.lastofus.events;

import com.apps.util.Prompter;

import java.util.Scanner;

class PrompterTest {
    public static void main(String[] args) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String input = prompter.prompt("Enter a letter: ", "A|B|C|D","\nEnter valid letter!\n");
        System.out.println(input);
    }
}