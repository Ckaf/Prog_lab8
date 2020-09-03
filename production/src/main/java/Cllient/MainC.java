package Cllient;


import GeneralTools.Information;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The class processes input data
 */
public class MainC {
    public static String FILENAME;
    public static int port = 8000;
    public static String host = "localhost";
    private static FileInputStream fileInputStream;
    public static void main() throws Exception {
        Client client = new Client();
        client.connect(host, port);
        Scanner scanner = new Scanner(System.in);
        Information infconnect=new Information();
        System.out.println("Вы хотите авторизоваться или войти?(reg\\aut)");
        String regtype=scanner.nextLine();
        if (regtype.equalsIgnoreCase("reg"))infconnect.regtype="reg";
        else if(regtype.equalsIgnoreCase("aut"))infconnect.regtype="aut";
        else {
            System.out.println("Тип авторизации введен неверно, программа завершает работу");
            System.exit(0);
        }
        System.out.println("Введите логин");
        String login=scanner.nextLine();
        System.out.println("Введите пароль");
        String pass=scanner.nextLine();

        infconnect.cmdtype="connect";
        infconnect.login=login;
        infconnect.pass=pass;
        client.run(infconnect);
        Scanner scanner1 = new Scanner(System.in);
        String cmd = "";
        while (cmd.equalsIgnoreCase("exit") != true) {
            cmd = scanner1.nextLine();
            cmd = cmd.trim();
            if (cmd.equalsIgnoreCase("help") == true) {
                Information help = new Information();
                help.cmdtype = "help";
                help.login=login;
                help.pass=pass;
                client.run(help);
            } else {
                if (cmd.equalsIgnoreCase("info") == true) {
                    Information info = new Information();
                    info.cmdtype = "info";
                    info.login=login;
                    info.pass=pass;
                    client.run(info);
                } else {
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
                            if (substr[2].equalsIgnoreCase("yes")) break;
                            else {
                                if (substr[2].equalsIgnoreCase("no")) break;
                                else System.out.println("неправильный ввод,попробуем еще раз");
                                substr[2] = scanner.nextLine();
                            }
                        }
                        System.out.println("Введите форму обучения(full time, evening, distance)");
                        substr[3] = scanner.nextLine();
                        substr[3] = substr[3].trim();
                        while (true) {
                            if (substr[3].equalsIgnoreCase("full time")) break;
                            else {
                                if (substr[3].equalsIgnoreCase("evening")) break;
                                else {
                                    if (substr[3].equalsIgnoreCase("distance")) break;
                                    else System.out.println("неправильный ввод,попробуем еще раз");
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
                                if (check1 >= 5 & check1 <= 8) break;
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
                            if (substr[8].equalsIgnoreCase("red")) break;
                            else {
                                if (substr[8].equalsIgnoreCase("black")) break;
                                else {
                                    if (substr[8].equalsIgnoreCase("brown")) break;
                                    else {
                                        if (substr[8].equalsIgnoreCase("orange")) break;
                                        else System.out.println("неправильный ввод,попробуем еще раз");
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
                                Information add = new Information();
                                add.cmdtype = "add";
                                //   add.arrayList.addAll(Arrays.asList(substr));
                                add.setParametrs(substr[0], substr[1], substr[2], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10]);
                                add.login=login;
                                add.pass=pass;
                                client.run(add);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("неправильный ввод,попробуем еще раз");
                                substr[10] = scanner.nextLine();
                            }
                        }


                    } else {
                        if (cmd.equalsIgnoreCase("show") == true) {
                            Information show = new Information();
                            show.cmdtype = "show";
                            show.login=login;
                            show.pass=pass;
                            client.run(show);
                        } else {
                            if (cmd.lastIndexOf("remove_by_id") != -1) {
                                String id;
                                int index1 = cmd.lastIndexOf(" ");
                                id = cmd.substring(index1 + 1);
                                id = id.trim();
                                while (true) {
                                    try {
                                        Integer check = Integer.valueOf(id);
                                        Information remove_by_id = new Information();
                                        remove_by_id.cmdtype = "remove_by_id";
                                        // remove_by_id.arrayList.add(id);
                                        remove_by_id.idstr = id;
                                        remove_by_id.login=login;
                                        remove_by_id.pass=pass;
                                        client.run(remove_by_id);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Введите id");
                                        id = scanner1.nextLine();
                                    }
                                }
                            } else {
                                if (cmd.equalsIgnoreCase("clear") == true) {
                                    Information clear = new Information();
                                    clear.cmdtype = "clear";
                                    clear.login=login;
                                    clear.pass=pass;
                                    client.run(clear);
                                } else {
                                    if (cmd.equalsIgnoreCase("head") == true) {
                                        Information head = new Information();
                                        head.cmdtype = "head";
                                        head.login=login;
                                        head.pass=pass;
                                        client.run(head);
                                    } else {
                                        if (cmd.equalsIgnoreCase("remove_head") == true) {
                                            Information remove_head = new Information();
                                            remove_head.cmdtype = "remove_head";
                                            remove_head.login=login;
                                            remove_head.pass=pass;
                                            client.run(remove_head);
                                        } else {
                                            if (cmd.lastIndexOf("update") != -1) {
                                                String id = null;
                                                int index1 = cmd.lastIndexOf(" ");
                                                try {
                                                    id = cmd.substring(index1);
                                                    id = id.trim();
                                                } catch (StringIndexOutOfBoundsException e) {
                                                    System.out.println("Введите id");
                                                    while (true) {
                                                        try {

                                                            id = scanner1.nextLine();
                                                            Integer check = Integer.parseInt(id);
                                                            break;
                                                        } catch (NumberFormatException ex) {
                                                            System.out.println("Неправильный ввод, попробуем еще раз");
                                                        }
                                                    }
                                                } catch (NumberFormatException e2) {
                                                    System.out.println("Неправильный ввод, попробуем еще раз");
                                                }

                                                scanner = new Scanner(System.in);
                                                //  String string = scanner.toString();
                                                String substr[] = new String[12];
                                                //  Scanner scanner = new Scanner(System.in);

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
                                                    if (substr[2].equalsIgnoreCase("yes")) break;
                                                    else {
                                                        if (substr[2].equalsIgnoreCase("no")) break;
                                                        else
                                                            System.out.println("неправильный ввод,попробуем еще раз");
                                                        substr[2] = scanner.nextLine();
                                                    }
                                                }
                                                System.out.println("Введите форму обучения(full time, evening, distance)");
                                                substr[3] = scanner.nextLine();
                                                substr[3] = substr[3].trim();
                                                while (true) {
                                                    if (substr[3].equalsIgnoreCase("full time")) break;
                                                    else {
                                                        if (substr[3].equalsIgnoreCase("evening")) break;
                                                        else {
                                                            if (substr[3].equalsIgnoreCase("distance")) break;
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
                                                        if (check1 >= 5 & check1 <= 8) break;
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
                                                    if (substr[8].equalsIgnoreCase("red")) break;
                                                    else {
                                                        if (substr[8].equalsIgnoreCase("black")) break;
                                                        else {
                                                            if (substr[8].equalsIgnoreCase("brown")) break;
                                                            else {
                                                                if (substr[8].equalsIgnoreCase("orange")) break;
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
                                                Information update = new Information();
                                                update.cmdtype = "update";
                                                update.idstr = id;
                                                update.setParametrs(substr[0], substr[1], substr[2], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10]);
                                                update.login=login;
                                                update.pass=pass;
                                                client.run(update);
                                            } else {
                                                if (cmd.lastIndexOf("remove_lower") != -1) {
                                                    long count = 1;
                                                    try {
                                                        cmd = cmd.trim();
                                                        int index1 = cmd.lastIndexOf(" ");
                                                        count = Long.parseLong(cmd.substring(index1 + 1));
                                                    } catch (Exception e) {
                                                        System.out.println("Введите номер в списке (count)");
                                                        scanner = new Scanner(System.in);
                                                        String cou = scanner.nextLine();
                                                        cmd = cmd + " " + cou;
                                                    }
                                                    Information remove_lover = new Information();
                                                    remove_lover.cmdtype = "remove_lover";
                                                    // remove_lover.arrayList.add(count);
                                                    remove_lover.count = String.valueOf(count);
                                                    remove_lover.login=login;
                                                    remove_lover.pass=pass;
                                                    client.run(remove_lover);
                                                } else {
                                                    if (cmd.lastIndexOf("remove_any_by_form_of_education") != -1) {
                                                        String form;
                                                        int index1 = cmd.lastIndexOf(" ");
                                                        form = cmd.substring(index1 + 1);
                                                        while (true) {
                                                            if (form.lastIndexOf("time") == -1 && form.equalsIgnoreCase("distance") == false && form.equalsIgnoreCase("evening") == false) {
                                                                System.out.println("Введите форму обучения(full time,distance,evening)");
                                                                scanner = new Scanner(System.in);
                                                                form = scanner.nextLine();
                                                                form = form.trim();
                                                            } else break;
                                                        }
                                                        Information remove_any_by_form_of_education = new Information();
                                                        remove_any_by_form_of_education.cmdtype = "remove_any_by_form_of_education";
                                                        // remove_any_by_form_of_education.arrayList.add(form);
                                                        remove_any_by_form_of_education.form = form;
                                                        remove_any_by_form_of_education.login=login;
                                                        remove_any_by_form_of_education.pass=pass;
                                                        client.run(remove_any_by_form_of_education);
                                                    } else {
                                                        if (cmd.lastIndexOf("filter_starts_with_name") != -1) {
                                                            String name;
                                                            int index1 = cmd.lastIndexOf(" ");
                                                            name = cmd.substring(index1 + 1);
                                                            Information filter_starts_with_name = new Information();
                                                            filter_starts_with_name.cmdtype = "filter_starts_with_name";
                                                            filter_starts_with_name.name = name;
                                                            filter_starts_with_name.login=login;
                                                            filter_starts_with_name.pass=pass;
                                                            client.run(filter_starts_with_name);
                                                        } else {
                                                            if (cmd.lastIndexOf("filter_greater_than_students_count") != -1) {
                                                                String count;
                                                                int index1 = cmd.lastIndexOf(" ");
                                                                count = cmd.substring(index1 + 1);
                                                                while (true) {
                                                                    try {
                                                                        long cou = Long.parseLong(count);
                                                                        Information filter_greater_than_students_count = new Information();
                                                                        filter_greater_than_students_count.cmdtype = "filter_greater_than_students_count";
                                                                        filter_greater_than_students_count.count = String.valueOf(cou);
                                                                        filter_greater_than_students_count.login=login;
                                                                        filter_greater_than_students_count.pass=pass;
                                                                        client.run(filter_greater_than_students_count);
                                                                        break;
                                                                    } catch (NumberFormatException e) {
                                                                        System.out.println("Введите номер");
                                                                        scanner = new Scanner(System.in);
                                                                        count = scanner.nextLine();
                                                                    }
                                                                }
                                                            } else {
                                                                if (cmd.lastIndexOf("execute_script") != -1) {
                                                                    String path;
                                                                    while (true)
                                                                        try {
                                                                            int index1 = cmd.lastIndexOf(" ");
                                                                            path = cmd.substring(index1);

                                                                            // execute_script.arrayList.add(path);
                                                                            break;
                                                                        } catch (StringIndexOutOfBoundsException e) {
                                                                            System.out.println("Введите имя файла");
                                                                            cmd = " " + scanner1.nextLine();
                                                                        }
                                                                    while (true) {
                                                                        try {
                                                                            path = path.trim();
                                                                            fileInputStream = new FileInputStream(path);
                                                                            // Information execute_script = new Information();
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
                                                                        cmd = s;
                                                                        cmd = cmd.trim();
                                                                        if (cmd.equalsIgnoreCase("exit") == true) break;

                                                                        if (cmd.equalsIgnoreCase("help") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "help";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }

                                                                        if (cmd.equalsIgnoreCase("info") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "info";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
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
                                                                                execute.login=login;
                                                                                execute.pass=pass;
                                                                                client.run(execute);
                                                                            } catch (Exception e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        }

                                                                        if (cmd.equalsIgnoreCase("show") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "show";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }

                                                                        if (cmd.lastIndexOf("remove_by_id") != -1) {
                                                                            String id;
                                                                            int index1 = cmd.lastIndexOf(" ");
                                                                            id = cmd.substring(index1);
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "remove_by_id";
                                                                            execute.idstr = id;
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }

                                                                        if (cmd.equalsIgnoreCase("clear") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "clear";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }

                                                                        if (cmd.equalsIgnoreCase("head") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "head";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }
                                                                        if (cmd.equalsIgnoreCase("remove_head") == true) {
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "remove_head";
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }

                                                                        if (cmd.lastIndexOf("update") != -1) {
                                                                            int id;
                                                                            while (true) {
                                                                                try {
                                                                                    cmd = cmd.trim();
                                                                                    int index1 = cmd.lastIndexOf(" ");
                                                                                    String idstr = cmd.substring(index1).trim();
                                                                                    id = Integer.parseInt(idstr);
                                                                                    break;
                                                                                } catch (Exception e) {
                                                                                    System.out.println("Введите id");
                                                                                    scanner = new Scanner(System.in);
                                                                                    String stringid = scanner.nextLine();
                                                                                    cmd = cmd + " " + stringid;
                                                                                }

                                                                            }
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
                                                                                execute.idstr = String.valueOf(id);
                                                                                execute.login=login;
                                                                                execute.pass=pass;
                                                                                client.run(execute);
                                                                            } catch (Exception e) {
                                                                                System.out.println("Неправильный ввод данных");
                                                                            }
                                                                        }

                                                                        if (cmd.lastIndexOf("remove_lower") != -1) {
                                                                            long count = 1;
                                                                            try {
                                                                                cmd = cmd.trim();
                                                                                int index1 = cmd.lastIndexOf(" ");
                                                                                count = Long.parseLong(cmd.substring(index1 + 1));
                                                                            } catch (Exception e) {
                                                                                System.out.println("Введите номер в списке (count)");
                                                                                scanner = new Scanner(System.in);
                                                                                String cou = scanner.nextLine();
                                                                                cmd = cmd + " " + cou;
                                                                            }
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "remove_lover";
                                                                            execute.count = String.valueOf(count);
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);
                                                                        }


                                                                        if (cmd.lastIndexOf("remove_any_by_form_of_education") != -1) {
                                                                            String form = null;
                                                                            while (true) {
                                                                                try {
                                                                                    cmd = cmd.trim();
                                                                                    int index1 = cmd.lastIndexOf(" ");
                                                                                    form = cmd.substring(index1 + 1);
                                                                                    if (form.equalsIgnoreCase("time"))
                                                                                        break;
                                                                                    else {
                                                                                        if (form.equalsIgnoreCase("evening"))
                                                                                            break;
                                                                                        else {
                                                                                            if (form.equalsIgnoreCase("distance"))
                                                                                                break;
                                                                                            else throw new Exception();
                                                                                        }
                                                                                    }
                                                                                } catch (Exception e) {
                                                                                    System.out.println("Введите форму обучения(full time\\evening\\distance)");
                                                                                    form = scanner.nextLine();
                                                                                    cmd = cmd + " " + form;
                                                                                }
                                                                            }
                                                                            form = form.trim();
                                                                            Information execute = new Information();
                                                                            execute.cmdtype = "remove_any_by_form_of_education";
                                                                            execute.form = form;
                                                                            execute.login=login;
                                                                            execute.pass=pass;
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
                                                                            execute.login=login;
                                                                            execute.pass=pass;
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
                                                                            execute.login=login;
                                                                            execute.pass=pass;
                                                                            client.run(execute);

                                                                        }

                                                                        if (cmd.lastIndexOf("execute_script") != -1) {
                                                                            //  String path;
                                                                            String file_name = null;
                                                                            while (true) {
                                                                                try {
                                                                                    int index1 = cmd.lastIndexOf(" ");
                                                                                    file_name = cmd.substring(index1);
                                                                                    break;
                                                                                } catch (StringIndexOutOfBoundsException e) {
                                                                                    System.out.println("Введите путь к файлу");
                                                                                    scanner = new Scanner(System.in);
                                                                                    String string = scanner.nextLine();
                                                                                    cmd = cmd + " " + string;
                                                                                }
                                                                            }
                                                                            System.out.println("Мы рискуем уйти в рекурсию, вы действительно хотите продолжить?(yes\\no)");
                                                                            while (true) {
                                                                                scanner = new Scanner(System.in);
                                                                                String answer = scanner.nextLine();
                                                                                answer.trim();
                                                                                if (answer.equalsIgnoreCase("yes") == true) {
                                                                               //     ExecuteScript.execute(file_name,table);
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
                                                                } else {
                                                                    if (cmd.equalsIgnoreCase("exit") != true)
                                                                        System.out.println("Команда введена неправильно");
                                                                }

                                                            }
                                                        }

                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (cmd.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}



