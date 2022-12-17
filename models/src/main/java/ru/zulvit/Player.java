package ru.zulvit;

public class Player {
    private final String nickName;
    private boolean isClan;


    public boolean isClan() {
        return isClan;
    }

    public void setClan(boolean clan) {
        isClan = clan;
    }

    public Player(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
