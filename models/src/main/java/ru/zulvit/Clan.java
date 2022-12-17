package ru.zulvit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Clan implements Serializable {
    public static final int MAX_AMOUNT_PERSON_IN_CLAN = 50;

    @JsonProperty("clanName")
    private final String clanName;
    @JsonProperty("countMaxMembers")
    private final int countMaxMembers;
    @JsonProperty("members")
    private List<Player> members;

    @JsonCreator
    public Clan(@JsonProperty("clanName") String clanName,
                @JsonProperty("countMaxMembers") int countMaxMembers) {
        this.clanName = clanName;
        this.countMaxMembers = countMaxMembers;
    }

    public String getClanName() {
        return clanName;
    }

    public int getCountMaxMembers() {
        return countMaxMembers;
    }

    public List<Player> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Clan{" +
                "clanName='" + clanName + '\'' +
                ", countMaxMembers=" + countMaxMembers +
                ", members=" + members +
                '}';
    }

    public static final class Factory {
        public Clan build(String name, int maxPerson) {
            if (name == null) {
                return new Clan(generateRandomName(), maxPerson);
            } else {
                return new Clan(name, maxPerson);
            }
        }
    }

    public static String generateRandomName() {
        return "clan#" + ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    }
}
