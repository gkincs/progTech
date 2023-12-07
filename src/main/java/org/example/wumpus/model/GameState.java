package org.example.wumpus.model;

public class GameState {

    public String username;

    public GameState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "username='" + username + '\'' +
                '}';
    }
}
