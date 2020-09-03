package Cllient;

import GeneralTools.Information;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.SendCommand;
import sample.WorkController;
import sample.tools.ExecuteAlert;
import sample.tools.MessageAlert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class for the command execute_script
 */
public class ExecuteScript {
    public static TableView<Students> tab;
    public static void execute(String path, String login, String password, TableView<Students> table) throws IOException, ClassNotFoundException {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        FileInputStream fileInputStream = null;
        path = path.trim();
        Client client = new Client();
        while (true) {
            try {
                fileInputStream = new FileInputStream(path);
                Information execute_script = new Information();
                //   execute_script.cmdtype = "execute_script";
                //  execute_script.file=new File(path);
                break;
            } catch (FileNotFoundException e) {
                if (Files.exists(Paths.get(path)) && (Files.isReadable(Paths.get(path)) == false))
                    System.out.println("Ошибка доступа");
                else
                    System.out.println("Файл не найден, введите имя еще раз");
                scanner = new Scanner(System.in);
                path = scanner.nextLine();
                path = path.trim();
                if (path.equalsIgnoreCase("exit"))
                    System.exit(0);
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String cmd = s;
            cmd = cmd.trim();
            if (cmd.equalsIgnoreCase("exit") == true) break;

            if (cmd.equalsIgnoreCase("help") == true) {
                SendCommand.help();
            }

            if (cmd.equalsIgnoreCase("info") == true) {
                SendCommand.info();
            }
            if (cmd.lastIndexOf("add") != -1) {
                SendCommand.add(table);
            }

            if (cmd.equalsIgnoreCase("show") == true) {
                SendCommand.show(table);
            }

            if (cmd.lastIndexOf("remove_by_id") != -1) {
                SendCommand.remove_by_id(table);
            }

            if (cmd.equalsIgnoreCase("clear") == true) {
                SendCommand.clear(table);
            }
            if (cmd.equalsIgnoreCase("head") == true) {
                SendCommand.head();
            }
            if (cmd.equalsIgnoreCase("remove_head") == true) {
                SendCommand.remove_head(table);
            }

            if (cmd.lastIndexOf("update") != -1) {
                SendCommand.update(table);
            }

            if (cmd.equalsIgnoreCase("remove_lower") == true) {
                SendCommand.remove_lower(table);
            }


            if (cmd.lastIndexOf("remove_any_by_form_of_education") != -1) {
                SendCommand.remove_any_by_form_education(table);
            }

            if (cmd.lastIndexOf("filter_starts_with_name") != -1) {
                SendCommand.filter_strats_with_name(table);
            }

            if (cmd.lastIndexOf("filter_greater_than_students_count") != -1) {
                SendCommand.filter_greater_than_students_count(table);

            }

            if (cmd.lastIndexOf("execute_script") != -1) {
                tab=table;
                SendCommand.execute_1(new Stage(),table);
            }
        }
    }
}

