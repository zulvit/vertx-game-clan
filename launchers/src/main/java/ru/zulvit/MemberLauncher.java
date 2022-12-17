package ru.zulvit;

import io.vertx.core.*;

import io.vertx.core.Vertx;
import ru.zulvit.user.Member;

import java.util.List;
import java.util.Random;

public class MemberLauncher extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(), vertxResult -> {
                    final var options = new DeploymentOptions().setWorker(true);
                    vertxResult.result().deployVerticle(new Member(new Player("tesdf")), options, res -> {
                        if (res.succeeded()) {
                            System.out.println("User deployment succeeded! Id: " + res.result());
                        } else {
                            var cause = res.cause();
                            System.out.println("Deployment failed!");
                            if (cause != null) {
                                System.out.println(cause.getMessage());
                            }
                        }
                    });
                }
        );
    }
}