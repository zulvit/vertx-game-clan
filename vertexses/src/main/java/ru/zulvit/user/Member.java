package ru.zulvit.user;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import ru.zulvit.Events;
import ru.zulvit.Player;

import java.util.List;

public class Member extends AbstractVerticle {
    private final Player player;
    private String clanTitle = "not in a clan";

    public Member(Player player) {
        this.player = player;
     }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        sendRequestClanInfo();
        vertx.eventBus().<JsonArray>consumer(Events.EVENT_USER_REQUEST_CLAN_ACCEPT, event -> {
            clanTitle = (String) event.body().getList().get(0);
            System.out.println(player.getNickName() + " вступил в клан.");
        });
        vertx.eventBus().consumer(Events.EVENT_USER_KICK, event -> {
            System.out.println(player.getNickName() + " исключён.");
            clanTitle = "not in a clan";
        });
    }

    private void sendRequestClanInfo() {
        if (!clanTitle.equals("not in a clan")) {
            vertx.eventBus().<JsonArray>request(Events.EVENT_CLAN_INFO, clanTitle, event -> {
                System.out.println("Info clan: ");
                if (event.succeeded()) {
                    List<String> clanMemberInfo = event.result().body().getList();
                    System.out.println("Список людей: " + clanMemberInfo);
                }
            });
        } else {
            vertx.eventBus().<JsonArray>request(Events.EVENT_SEE_ALL_CLANS, "all", event -> {
                System.out.println("Доступные кланы:");
                if (event.succeeded()) {
                    List<String> listClan = event.result().body().getList();
                    System.out.println("Список: " + listClan);
                    if (!listClan.isEmpty()) {
                        int clanId = (int) (Math.random() * listClan.size()); //Случайно выбираем клан
                        vertx.eventBus().send(Events.EVENT_USER_TO_JOIN_CLAN, JsonArray.of(player.getNickName(), listClan.get(clanId)));
                    }
                }
            });
        }
    }
}
