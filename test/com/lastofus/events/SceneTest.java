package com.lastofus.events;

import com.apps.util.Console;
import org.junit.Test;
import static org.junit.Assert.*;

public class SceneTest {
    @Test
    public void begin_ShouldPromptForUserInput() {
        Scene s1 = new Scene("5");
        Console.clear();
        System.out.println();
        s1.begin();
    }
}