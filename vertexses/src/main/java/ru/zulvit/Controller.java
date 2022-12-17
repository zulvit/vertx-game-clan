package ru.zulvit;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class Controller extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().<JsonObject>consumer(Events.EVENT_CREATE_CLAN, event -> {
            System.out.println("Запрос к созданию клана принят контроллером");
            vertx.sharedData().<String, Clan>getAsyncMap(Data.MAP_CLANS, map -> {
                if (map.succeeded()) {
                    map.result().get(event.body().getString(Data.MAP_CLAN_NAME), result -> {
                        map.result().put(event.body().getString(Data.MAP_CLAN_NAME),
                                new Clan.Factory().build(
                                        event.body().getString(Data.MAP_CLAN_NAME),
                                        event.body().getInteger(Data.MAP_CLAN_MAX_PEOPLE),
                                        event.body().getJsonArray(Data.USER_CLAN_MEMBERS).getList()));
                    });
                }
            });
        });

        vertx.eventBus().<JsonObject>consumer(Events.EVENT_USER_TO_JOIN_CLAN, event -> {
            System.out.println("Запрос к вступлению нового участника принят");
            vertx.sharedData().<String, Clan>getAsyncMap(Data.MAP_CLANS, map -> {
                map.result().get(event.body().getString(Data.USER), resultClan -> {
                    final List<Player> playerList = resultClan.result().getMembers();
                    map.result().put(Data.USER, new Clan(resultClan.result().getClanName(), resultClan.result().getCountMaxMembers(), playerList));
                });
            });
        });
    }
}
