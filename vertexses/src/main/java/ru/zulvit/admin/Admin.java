package ru.zulvit.admin;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import ru.zulvit.Clan;
import ru.zulvit.Data;
import ru.zulvit.Events;
import ru.zulvit.Player;
import ru.zulvit.moderator.Moderator;

import static ru.zulvit.Clan.MAX_AMOUNT_PERSON_IN_CLAN;

public class Admin extends Moderator {

    public Admin(Player player) {
        super(player);
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.executeBlocking(completion -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put(Data.MAP_CLAN_NAME, Clan.generateRandomName());
            jsonObject.put(Data.MAP_CLAN_MAX_PEOPLE, MAX_AMOUNT_PERSON_IN_CLAN);
            vertx.eventBus().<JsonObject>request(Events.EVENT_CREATE_CLAN, jsonObject);
            completion.complete();
        });
        System.out.println("Отправлен запрос на создание клана");
        super.start();
    }
}
