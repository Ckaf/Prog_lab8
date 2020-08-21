package Filling;

import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * Class for logger
 */
public class Logger {
    static java.util.logging.Logger LOGGER= java.util.logging.Logger.getLogger("ServerLogger");
    static FileHandler fh;
    public static void login(Level level,String info)  {
    /*    try {
            fh = new FileHandler("C:\\Users\\dns\\Desktop\\итмо\\прога\\proga_lab6\\NewServer\\src\\Log");
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }
        catch (IOException e){
            System.out.println("Логирование в файл невозможно");
      }
     */
        //LOGGER.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        LOGGER.log(level,info);

    }
}
