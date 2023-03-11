package com.lastofus.events;

import com.lastofus.player.Player;

import java.util.ArrayList;

interface Event {
    void begin();
    void updatePlayer(Player currentPlayer);
    void end();
}
