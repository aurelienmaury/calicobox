package org.rtnp.calico

import org.rtnp.calico.verticles.DbVerticle
import org.rtnp.calico.verticles.FileYardVerticle
import org.rtnp.calico.verticles.SearchVerticle
import org.rtnp.calico.verticles.WebVerticle
import org.vertx.groovy.platform.Verticle
import org.vertx.java.core.impl.DefaultFutureResult

class MainVerticle extends Verticle {

    static final String BUS_NAME = 'org.rtnp.galaxie.vertx.mod.gwez'

    def start() {

        printBanner()

        [
                WebVerticle,
                DbVerticle,
                SearchVerticle,
                FileYardVerticle
        ].each { deployVerticleClass(it) }
    }

    private void deployVerticleClass(Class verticleClass) {

        String shortName = verticleClass.simpleName
        String fullName = verticleClass.canonicalName

        Map verticleConfig = (container.config[shortName] ?: [:]) as Map

        container.deployVerticle('groovy:' + fullName, verticleConfig) { DefaultFutureResult asyncRes ->

            container.logger.warn("${shortName} configuration: ${verticleConfig}")

            if (asyncRes.succeeded()) {
                container.logger.info("Verticle deploy ${shortName}: Success")
            } else {
                container.logger.error("Verticle deploy ${shortName}: Failure", asyncRes.cause())
            }
        }
    }

    private void printBanner() {
        println '___________________________________________________'
        println '    _____'
        println '   |  __ \\'
        println '   | |  \\/_      _____ ____'
        println '   | | __\\ \\ /\\ / / _ \\_  /'
        println '   | |_\\ \\\\ V  V /  __// /'
        println '    \\____/ \\_/\\_/ \\___/___|'
        println '___________________________________________________'
    }
}
