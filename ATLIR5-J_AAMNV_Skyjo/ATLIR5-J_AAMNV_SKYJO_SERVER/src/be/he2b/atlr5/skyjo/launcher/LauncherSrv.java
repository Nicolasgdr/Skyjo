package be.he2b.atlr5.skyjo.launcher;

import be.he2b.atlr5.skyjo.server.motherServer.PrimaryServer;

/**
 * Main function for the server
 */
public class LauncherSrv {
    public static void main(String[] args) {
        var primaryServer = new PrimaryServer("Application -Mother Server-");
        primaryServer.start();
    }
}
