package Cllient;
import GeneralTools.Information;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class for the command execute_script
 */
public class ExecuteScript {
    public static void execute(String path) throws IOException, ClassNotFoundException {
        // String path;
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        FileInputStream fileInputStream = null;
        path=path.trim();
       Client client=new Client();
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
                Information execute = new Information();
                execute.cmdtype = "help";
                client.run(execute);
            }

            if (cmd.equalsIgnoreCase("info") == true) {
                Information execute = new Information();
                execute.cmdtype = "info";
                client.run(execute);
            }
            if (cmd.lastIndexOf("add") != -1) {
                cmd.replace(" ", "");
                String substr[] = new String[12];
                scanner = new Scanner(System.in);

                System.out.println("Введите имя");
                substr[0] = scanner.nextLine();
                System.out.println("Введите номер");

                substr[1] = scanner.nextLine();
                long check;
                while (true) {
                    try {
                        check = Long.parseLong(substr[1]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[1] = scanner.nextLine();
                    }
                }

                System.out.println("Введите отчислен ли студент(yes/no)");
                substr[2] = scanner.nextLine();
                substr[2] = substr[2].trim();
                while (true) {
                    if (substr[2].equalsIgnoreCase("yes"))
                        break;
                    else {
                        if (substr[2].equalsIgnoreCase("no"))
                            break;
                        else
                            System.out.println("неправильный ввод,попробуем еще раз");
                        substr[2] = scanner.nextLine();
                    }
                }
                System.out.println("Введите форму обучения(full time, evening, distance)");
                substr[3] = scanner.nextLine();
                substr[3] = substr[3].trim();
                while (true) {
                    if (substr[3].equalsIgnoreCase("full time"))
                        break;
                    else {
                        if (substr[3].equalsIgnoreCase("evening"))
                            break;
                        else {
                            if (substr[3].equalsIgnoreCase("distance"))
                                break;
                            else
                                System.out.println("неправильный ввод,попробуем еще раз");
                            substr[3] = scanner.nextLine();
                        }
                    }
                }
                System.out.println("Введите номер семестра");
                substr[4] = scanner.nextLine();
                substr[4] = substr[4].trim();
                while (true) {
                    try {
                        int check1 = Integer.parseInt(substr[4]);
                        if (check1 >= 5 & check1 <= 8)
                            break;
                        else {
                            System.out.println("неправильный ввод,попробуем еще раз");
                            substr[4] = scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[4] = scanner.nextLine();
                    }
                }
                System.out.println("Введите имя админа группы");
                substr[5] = scanner.nextLine();
                System.out.println("Введите высоту");
                substr[6] = scanner.nextLine();
                substr[6] = substr[6].trim();
                while (true) {
                    try {
                        Double check2 = Double.parseDouble(substr[6]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[6] = scanner.nextLine();
                    }
                }
                System.out.println("Введите вес");
                substr[7] = scanner.nextLine();
                while (true) {
                    try {
                        Integer check3 = Integer.parseInt(substr[7]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[7] = scanner.nextLine();
                    }
                }
                System.out.println("Введите цвет глаз(red, black, brown, orange)");
                substr[8] = scanner.nextLine();
                substr[8].trim();
                while (true) {
                    if (substr[8].equalsIgnoreCase("red"))
                        break;
                    else {
                        if (substr[8].equalsIgnoreCase("black"))
                            break;
                        else {
                            if (substr[8].equalsIgnoreCase("brown"))
                                break;
                            else {
                                if (substr[8].equalsIgnoreCase("orange"))
                                    break;
                                else
                                    System.out.println("неправильный ввод,попробуем еще раз");
                                substr[8] = scanner.nextLine();
                            }
                        }
                    }
                }
                System.out.println("Введите X");
                substr[9] = scanner.nextLine();
                substr[9] = substr[9].trim();
                while (true) {
                    try {
                        Float check4 = Float.parseFloat(substr[9]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[9] = scanner.nextLine();
                    }
                }
                System.out.println("Введите Y");
                substr[10] = scanner.nextLine();
                substr[10] = substr[10].trim();
                while (true) {
                    try {
                        long check5 = Long.valueOf(substr[10]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("неправильный ввод,попробуем еще раз");
                        substr[10] = scanner.nextLine();
                    }
                }

                try {
                    Information execute = new Information();
                    execute.cmdtype = "add";
                    execute.setParametrs(substr[0], substr[1], substr[2], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10]);
                    client.run(execute);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (cmd.equalsIgnoreCase("show") == true) {
                Information execute = new Information();
                execute.cmdtype = "show";
                client.run(execute);
            }

            if (cmd.lastIndexOf("remove_by_id") != -1) {
                String id;
                int index1 = cmd.lastIndexOf(" ");
                id = cmd.substring(index1);
                Information execute = new Information();
                execute.cmdtype = "remove_by_id";
                execute.idstr = id;
                client.run(execute);
            }

            if (cmd.equalsIgnoreCase("clear") == true) {
                Information execute = new Information();
                execute.cmdtype = "clear";
                client.run(execute);
            }
            if (cmd.equalsIgnoreCase("head") == true) {
                Information execute = new Information();
                execute.cmdtype = "head";
                client.run(execute);
            }
            if (cmd.equalsIgnoreCase("remove_head") == true) {
                Information execute = new Information();
                execute.cmdtype = "remove_head";
                client.run(execute);
            }

            if (cmd.lastIndexOf("update") != -1) {
                String id;
                int index1 = cmd.lastIndexOf(" ");
                id = cmd.substring(index1);
                System.out.println("Введите через запятую в нужном порядке следующие данные: имя, номер, отчислен ли студент(yes/no), форма обучения(full time, evening, distance), номер сместра(5,6,7,8), имя админа группы" +
                        ", высоту, вес, цвет глаз(red, black brown, orange), X, Y");
                String substr[];
                scanner = new Scanner(System.in);
                String string = scanner.toString();
                substr = string.split(",");
                try {
                    Information execute = new Information();
                    execute.cmdtype = "update";
                    execute.setParametrs(substr[0], substr[1], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10], substr[11]);
                    execute.idstr = id;
                    client.run(execute);
                } catch (Exception e) {
                    System.out.println("Неправильный ввод данных");
                }
            }

            if (cmd.equalsIgnoreCase("remove_lower") == true) {
                System.out.println("Введите номер в списке (count)");
                scanner = new Scanner(System.in);
                long count = scanner.nextLong();
                Information execute = new Information();
                execute.cmdtype = "remove_lover";
                execute.count = String.valueOf(count);
                client.run(execute);
            }


            if (cmd.lastIndexOf("remove_any_by_form_of_education") != -1) {
                String form;
                int index1 = cmd.lastIndexOf(" ");
                form = cmd.substring(index1 + 1);
                form = form.trim();
                Information execute = new Information();
                execute.cmdtype = "remove_any_by_form_of_education";
                execute.form = form;
                client.run(execute);
            }

            if (cmd.lastIndexOf("filter_starts_with_name") != -1) {
                String name;
                int index1 = cmd.lastIndexOf(" ");
                name = cmd.substring(index1 + 1);
                name = name.trim();
                Information execute = new Information();
                execute.cmdtype = "filter_starts_with_name";
                execute.name = name;
                client.run(execute);
            }

            if (cmd.lastIndexOf("filter_greater_than_students_count") != -1) {
                String count;
                int index1 = cmd.lastIndexOf(" ");
                count = cmd.substring(index1 + 1);
                count = count.trim();
                long cou = 0;
                while (true) {
                    try {
                        cou = Long.parseLong(count);
                        break;
                    } catch (Exception e) {
                        System.out.println("Номер введен неправильно, попробуем еще раз");
                        scanner = new Scanner(System.in);
                        count = scanner.nextLine();
                        count = count.trim();
                    }
                }
                Information execute = new Information();
                execute.cmdtype = "filter_greater_than_students_count";
                execute.count = String.valueOf(cou);
                client.run(execute);

            }

            if (cmd.lastIndexOf("execute_script") != -1) {
                //  String path;
                String file_name=null;
                while (true)
                    try {
                        int index1 = cmd.lastIndexOf(" ");
                        file_name = cmd.substring(index1);
                        break;
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Введите путь к файлу");
                        scanner=new Scanner(System.in);
                        String string=scanner.nextLine();
                        cmd=cmd+" "+string;
                    }

                System.out.println("Мы рискуем уйти в рекурсию, вы действительно хотите продолжить?(yes\\no)");
                while (true) {
                    scanner = new Scanner(System.in);
                    String answer = scanner.nextLine();
                    answer.trim();
                    if (answer.equalsIgnoreCase("yes") == true) {
                        ExecuteScript.execute( file_name);
                        break;
                    } else {
                        if (answer.equalsIgnoreCase("no") == true) {
                            System.out.println("Хороший выбор");
                            break;
                        } else
                            System.out.println("Введите ответ еще раз");
                    }
                }
            }
        }
    }
}
