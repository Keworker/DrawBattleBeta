package com.samsung.drawbattle;

public class PlayerInGame {
    private String nickName;
    private byte numOfAvatar;
    private byte score;
    private int index;

    public PlayerInGame(String nickName, byte numOfAvatar, int index) {
        this.nickName = nickName + "";
        this.numOfAvatar = numOfAvatar;
        this.index = index;
    }

    public PlayerInGame(String nickName, byte numOfAvatar, int index, byte score) {
        this.nickName = nickName + "";
        this.numOfAvatar = numOfAvatar;
        this.index = index;
        this.score = score;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte scored) {
        this.score = (byte) ((byte) (score * scored) / 2);
    }
}
