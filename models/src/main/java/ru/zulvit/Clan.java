package ru.zulvit;

public class Clan {
    private final String title;
    private final int maxMember;
    private final Role role;

    private final User[] user;

    public Clan(String title, int maxMember, Role role) {
        this.title = title;
        this.maxMember = maxMember;
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Clan{" +
                "title='" + title + '\'' +
                ", maxHuman=" + maxMember +
                ", role=" + role +
                '}';
    }
}
