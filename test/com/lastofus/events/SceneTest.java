package com.lastofus.events;

import org.junit.Test;
import static org.junit.Assert.*;

public class SceneTest {
    @Test
    public void begin_ShouldPromptForUserInput() {
        Scene s1 = new Scene("Scene1");
        s1.begin();
    }
}