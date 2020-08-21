package Filling;

import DB.Command;
import DB.Connect;

public class Main {

    public static void main(String[] args) throws Exception {
        Server.connect(8000);
        Connect.connection();
        Command.readdb();
        Server.run();
    }
}
