//package ru.zulvit;
//
//import io.vertx.core.DeploymentOptions;
//import io.vertx.core.Vertx;
//import ru.zulvit.admin.Admin;
//
//public class AdminLauncher {
//    public static void main(String[] args) {
//        final var vertx = Vertx.vertx();
//        final var options = new DeploymentOptions().setWorker(true);
//        vertx.deployVerticle(new Controller(), options);
//        vertx.deployVerticle(new Admin(new Player("sd"), null), options);
//    }
//}