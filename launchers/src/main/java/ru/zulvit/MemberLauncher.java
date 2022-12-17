package ru.zulvit;

import io.vertx.core.*;

import io.vertx.core.Vertx;
import ru.zulvit.user.Member;

import java.util.List;
import java.util.Random;

public class MemberLauncher extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx.clusteredVertx(
                new VertxOptions(),
                vertxResult -> {
                    final var vertx = vertxResult.result();
                    final var options = new DeploymentOptions().setWorker(true).setInstances(5);
                    vertx.sharedData().getCounter("counterPlayers", counter -> {
                        if (counter.succeeded()) {
                            counter.result().incrementAndGet(number -> {
                                String nickname = "member#" + number.result();
                                vertx.deployVerticle(new Member(new Player(nickname)), options);
                                vertx.sharedData().<String, List<String>>getAsyncMap("players", map -> {
                                    map.result().get("info", getResult -> {
                                        final var newList = getResult.result();
                                        newList.add(nickname);
                                        map.result().put("info", newList);
                                    });
                                });
                            });
                        }
                    });
                });
    }
}