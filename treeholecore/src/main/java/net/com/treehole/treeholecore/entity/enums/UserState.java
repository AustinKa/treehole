package net.com.treehole.treeholecore.entity.enums;

/**
 *description 用户状态枚举 0 下线，1在线，2隐身，3离开，4勿扰
 *@className UserState
 *@author guixiao
 *@date 2022/6/29 21:50
 */
public enum UserState {

    OFF_LINE(0,"离线"),
    ON_LINE(1,"在线"),
    INVISIBLE(2,"在线");

    private int state;
    private String description;

    UserState(int state, String description) {
        this.state = state;
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
