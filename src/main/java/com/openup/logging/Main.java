package com.openup.logging;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by benwa on 30/05/17.
 * <p>
 * Project under the Apache v 2 license
 */
public class Main {

    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Application started successfully");
        LOGGER.warn("Could not find any native epoll version. Default to sub-optimal NIO implementation. If possible consider binding the one corresponding to your platform to enhance performance");
        while (true) {
            Thread.sleep(1000);
            int logCount = RANDOM.nextInt(1000);
            for (int i = 0; i < logCount; i++) {
                if (getProba() < 0.8) {
                    logDebug();
                } else if (getProba() < 0.6) {
                    logInfo();
                } else if (getProba() < 0.6) {
                    logWarn();
                } else logError();
            }
        }
    }

    private static void logError() {
        if (getProba() < 0.6)
            LOGGER.error("Error code, IndexOutOfBoundException. Did you ensure the size of the list before reading it?");
        else if (getProba() < 0.6)
            LOGGER.error("Timeout issuing queries with database at 127.0.0.1. Is your database overwhelm?");
        else if (getProba() < 0.6)
            LOGGER.error("Failed parsing JSON at char 18: unproperly closed JSON string. Could not index. {\"field\":\"userProvidedValue\", \"}");
        else if (getProba() < 0.9)
            LOGGER.error("Can not process request: cause by NullPointerException", generateCoolStackTrace(10));
        else
            LOGGER.error("32k long radical is too long to be indexed with Lucene. Insert in ELasticSearch failed for message " + UUID.randomUUID(), generateCoolStackTrace(14));
    }

    private static void logWarn() {
        LOGGER.warn("Storage space on disk is not optimal. Please consider having at least 4GB of free disk space");
    }

    private static void logInfo() {
        LOGGER.info("A client " + UUID.randomUUID() + " just connected to the service");
    }

    private static void logDebug() {
        LOGGER.debug("Meaningless information", generateCoolStackTrace(50));
    }

    static Exception generateCoolStackTrace(int depth) {
        if (depth == 0) {
            return new Exception("Needless information");
        }
        return generateCoolStackTrace(depth - 1);
    }

    private static double getProba() {
        return Double.valueOf(RANDOM.nextInt(1000)) / 1000.;
    }
}
