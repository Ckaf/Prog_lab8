package Filling;

import DB.Command;
import GeneralTools.Answer;
import GeneralTools.Information;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;

import static Filling.MessageHandling.UserList;


/**
 * This class describes how commands work
 **/
public class AllCmd {
    public static String answer;
    static Answer answerr = new Answer();

    public static void help() {
        answer = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                " exit : завершить программу (без сохранения в файл)\n" +
                "head : вывести первый элемент коллекции\n" +
                "remove_head : вывести первый элемент коллекции и удалить его\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "remove_any_by_form_of_education formOfEducation : удалить из коллекции один элемент, значение поля formOfEducation которого эквивалентно заданному\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                "filter_greater_than_students_count studentsCount : вывести элементы, значение поля studentsCount которых больше заданного)";
        answerr.cmd = "help";
        answerr.setAnswer(answer);
    }

    public static void info(Queue<StudyGroup> StudyGroupPriorityQueue) throws NullPointerException {
        answer = "тип коллекции:PriorityQueue " + "кол-во элементов: " + StudyGroupPriorityQueue.size() + " дата инициализации: " + StudyGroupPriorityQueue.peek().getCreationDate();
        answerr.setAnswer(answer);
    }

    public static void show(Queue<StudyGroup> StudyGroupPriorityQueue) {
        answer = "";
        answerr.list = new LinkedList<String>();
        MessageHandling.StudyGroupPriorityQueue.stream().sorted(XMLReader.countComparator).forEach(student ->
                answerr.list.add(student.getId() + "," + student.getName() + "," + student.getStudentsCount() + "," + student.getexp() + "," + student.getFormOfEducation() + "," + student.getSemesterEnum() + "," + student.getAdminName()
                        + "," + student.getHeight() + "," + student.getWeight() + "," + student.getColor() + "," + student.getCoordinatesX() + "," + student.getCoordinatesY() + "," + student.getUser() + "," + student.getUserColor()));
        answerr.cmd = "show";
        answerr.setAnswer(answer);
    }

    public static void getColor(Information information) {
        HashSet<String> ColorList = new HashSet<>();
        UserList.stream().forEach(user -> {
            ColorList.add(user.UserColor);
        });
        UserList.stream().forEach(user -> {
            if (user.login.equals(information.login)) {
                if (user.UserColor != null) {
                    answerr.cmd = "getColor";
                    answerr.UserColor = user.UserColor;
                } else {
                    //генерируем цвет
                    while (true) {
                        try {

                            Random rand = new Random();
                            float r = (float) (rand.nextFloat() / 2f + 0.5);
                            float g = (float) (rand.nextFloat() / 2f + 0.5);
                            float b = (float) (rand.nextFloat() / 2f + 0.5);
                            String hex = String.format("#%02X%02X%02X",
                                    (int) (r * 255),
                                    (int) (g * 255),
                                    (int) (b * 255));
                            if (!ColorList.contains(hex)) {
                                user.UserColor = hex;
                                answerr.cmd = "getColor";
                                answerr.UserColor = user.UserColor;
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });
    }

    public static void add(String name, String count, String exp, String form, String semestr, String groupAdmin, String height, String weight, String eyeColor, String X, String Y, Queue<StudyGroup> StudyGroupPriorityQueue, Information information) throws Exception {
        Command.add(name, count, exp, form, semestr, groupAdmin, height, weight, eyeColor, X, Y, information);
        answer = "Элемент добавлен";
        answerr.cmd = "add";
        answerr.setAnswer(answer);
    }

    public static void update(Information information) throws SQLException {
        if (Command.checkLogin(information)) {
            try {
                Command.update(information.name, information.count, information.exp, information.form, information.semestr, information.groupAdmin, information.height, information.weight, information.eyeColor, information.X, information.Y, information.idstr);
                Command.readdb();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            answer = "Данные обновлены";
        } else {
            answer = "Этот элемент не пренадлежит вам, вы не можете его изменять";
            Logger.login(Level.INFO, "Попытка изменения элемента не удалась");
        }
        answerr.cmd = "update";
        answerr.setAnswer(answer);
    }


    public static void remove_by_id(Information information) throws Exception {
        if (Command.checkLogin(information)) {
            Command.remove_by_id(information);
            Command.readdb();
            answer = "Элемент удален";
        } else answer = "Вы не можете удалить этот элемент";
        answerr.cmd = "remove_by_id";
        answerr.setAnswer(answer);
    }

    public static void clear(Information information) throws Exception {
        Command.clear(information);
        Command.readdb();
        answer = "Ваши элементы удалены";
        answerr.cmd = "clear";
        answerr.setAnswer(answer);
    }

    public static void save(Queue<StudyGroup> StudyGroupPriorityQueue) throws IOException {
        XMLWriter.write(StudyGroupPriorityQueue);
        answerr.file = XMLWriter.file1;
        answerr.wrong = 2;
    }

   /* public static void execute_script(Queue<Filling.StudyGroup> StudyGroupPriorityQueue, File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
     /*   while (true) {
            try {
                fileInputStream = new FileInputStream(file_name);
                break;
            } catch (FileNotFoundException e) {
                if (Files.exists(Paths.get(file_name)) && (Files.isReadable(Paths.get(file_name)) == false))
                    System.out.println("Ошибка доступа");
                else System.out.println("Файл не найден, введите имя еще раз");
                Scanner scanner = new Scanner(System.in);
                file_name = scanner.nextLine();
                file_name = file_name.trim();
                if (file_name.equalsIgnoreCase("exit")) System.exit(0);
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
                Filling.AllCmd.help();
            }

            if (cmd.equalsIgnoreCase("info") == true) {
                Filling.AllCmd.info(StudyGroupPriorityQueue);
            }
            if (cmd.lastIndexOf("add") != -1) {
                cmd.replace(" ", "");
                String substr[] = new String[12];
                Scanner scanner = new Scanner(System.in);

                answer = "Введите имя";
                answerr.setAnswer(answer);
                substr[0] = scanner.nextLine();
                answer = "Введите номер";
                answerr.setAnswer(answer);

                substr[1] = scanner.nextLine();
                long check;
                while (true) {
                    try {
                        check = Long.parseLong(substr[1]);
                        break;
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[1] = scanner.nextLine();
                    }
                }

                answer = "Введите отчислен ли студент(yes/no)";
                answerr.setAnswer(answer);
                substr[2] = scanner.nextLine();
                substr[2] = substr[2].trim();
                while (true) {
                    if (substr[2].equalsIgnoreCase("yes")) break;
                    else {
                        if (substr[2].equalsIgnoreCase("no")) break;
                        else {
                            answer = "неправильный ввод,попробуем еще раз";
                            answerr.setAnswer(answer);
                        }
                        substr[2] = scanner.nextLine();
                    }
                }
                answer = "Введите форму обучения(full time, evening, distance)";
                answerr.setAnswer(answer);
                substr[3] = scanner.nextLine();
                substr[3] = substr[3].trim();
                while (true) {
                    if (substr[3].equalsIgnoreCase("full time")) break;
                    else {
                        if (substr[3].equalsIgnoreCase("evening")) break;
                        else {
                            if (substr[3].equalsIgnoreCase("distance")) break;
                            else {
                                answer = "неправильный ввод,попробуем еще раз";
                                answerr.setAnswer(answer);
                            }
                            substr[3] = scanner.nextLine();
                        }
                    }
                }
                answer = "Введите номер семестра";
                answerr.setAnswer(answer);
                substr[4] = scanner.nextLine();
                substr[4] = substr[4].trim();
                while (true) {
                    try {
                        int check1 = Integer.parseInt(substr[4]);
                        if (check1 >= 5 & check1 <= 8) break;
                        else {
                            answer = "неправильный ввод,попробуем еще раз";
                            answerr.setAnswer(answer);
                            substr[4] = scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[4] = scanner.nextLine();
                    }
                }
                answer = "Введите имя админа группы";
                answerr.setAnswer(answer);
                substr[5] = scanner.nextLine();
                answer = "Введите высоту";
                answerr.setAnswer(answer);
                substr[6] = scanner.nextLine();
                substr[6] = substr[6].trim();
                while (true) {
                    try {
                        Double check2 = Double.parseDouble(substr[6]);
                        break;
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[6] = scanner.nextLine();
                    }
                }
                answer = "Введите вес";
                answerr.setAnswer(answer);
                substr[7] = scanner.nextLine();
                while (true) {
                    try {
                        Integer check3 = Integer.parseInt(substr[7]);
                        break;
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[7] = scanner.nextLine();
                    }
                }
                answer = "Введите цвет глаз(red, black, brown, orange)";
                answerr.setAnswer(answer);
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
                                answer = "неправильный ввод,попробуем еще раз";
                                answerr.setAnswer(answer);
                                substr[8] = scanner.nextLine();
                            }
                        }
                    }
                }
                answer = "Введите X";
                answerr.setAnswer(answer);
                substr[9] = scanner.nextLine();
                substr[9] = substr[9].trim();
                while (true) {
                    try {
                        Float check4 = Float.parseFloat(substr[9]);
                        break;
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[9] = scanner.nextLine();
                    }
                }
                answer = "Введите Y";
                answerr.setAnswer(answer);
                substr[10] = scanner.nextLine();
                substr[10] = substr[10].trim();
                while (true) {
                    try {
                        long check5 = Long.valueOf(substr[10]);
                        break;
                    } catch (NumberFormatException e) {
                        answer = "неправильный ввод,попробуем еще раз";
                        answerr.setAnswer(answer);
                        substr[10] = scanner.nextLine();
                    }
                }

                try {
                    Filling.AllCmd.add(substr[0], substr[1], substr[2], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10], StudyGroupPriorityQueue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (cmd.equalsIgnoreCase("show") == true) Filling.AllCmd.show(StudyGroupPriorityQueue);

            if (cmd.lastIndexOf("remove_by_id") != -1) {
                String id;
                int index1 = cmd.lastIndexOf(" ");
                id = cmd.substring(index1);
                Filling.AllCmd.remove_by_id(StudyGroupPriorityQueue, id);
            }

            if (cmd.equalsIgnoreCase("clear") == true) Filling.AllCmd.clear(StudyGroupPriorityQueue);

            if (cmd.equalsIgnoreCase("save") == true) {
                Filling.AllCmd.save(StudyGroupPriorityQueue);
            }

            if (cmd.equalsIgnoreCase("head") == true) Filling.AllCmd.head(StudyGroupPriorityQueue);

            if (cmd.equalsIgnoreCase("remove_head") == true) Filling.AllCmd.remove_head(StudyGroupPriorityQueue);

            if (cmd.lastIndexOf("update") != -1) {
                String id;
                int index1 = cmd.lastIndexOf(" ");
                id = cmd.substring(index1);
                System.out.println("Введите через запятую в нужном порядке следующие данные: имя, номер, отчислен ли студент(yes/no), форма обучения(full time, evening, distance), номер сместра(5,6,7,8), имя админа группы" +
                        ", высоту, вес, цвет глаз(red, black brown, orange), X, Y");
                String substr[];
                Scanner scanner = new Scanner(System.in);
                String string = scanner.toString();
                substr = string.split(",");
                try {
                    Filling.AllCmd.update(StudyGroupPriorityQueue, id, substr[0], substr[1], substr[3], substr[4], substr[5], substr[6], substr[7], substr[8], substr[9], substr[10], substr[11]);
                } catch (Exception e) {
                    answer = "Неправильный ввод данных";
                    answerr.setAnswer(answer);
                }
            }

            if (cmd.equalsIgnoreCase("remove_lower") == true) {
                Scanner scanner = new Scanner(System.in);
                long count = scanner.nextLong();
                Filling.AllCmd.remove_lover(StudyGroupPriorityQueue, count);
            }


            if (cmd.lastIndexOf("remove_any_by_form_of_education") != -1) {
                String form;
                int index1 = cmd.lastIndexOf(" ");
                form = cmd.substring(index1 + 1);
                form = form.trim();
                Filling.User user = null;
                Filling.AllCmd.remove_any_by_form_of_education(StudyGroupPriorityQueue, form);
            }

            if (cmd.lastIndexOf("filter_starts_with_name") != -1) {
                String name;
                int index1 = cmd.lastIndexOf(" ");
                name = cmd.substring(index1 + 1);
                name = name.trim();
                Filling.AllCmd.filter_starts_with_name(StudyGroupPriorityQueue, name);
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
                        answer = "Номер введен неправильно, попробуем еще раз";
                        answerr.setAnswer(answer);
                        Scanner scanner = new Scanner(System.in);
                        count = scanner.nextLine();
                        count = count.trim();
                    }
                }
                Filling.AllCmd.filter_greater_than_students_count(StudyGroupPriorityQueue, cou);
            }

            if (cmd.lastIndexOf("execute_script") != -1) {
                //  String path;
                int index1 = cmd.lastIndexOf(" ");
                String file_name = cmd.substring(index1);
                answer = "Мы рискуем уйти в рекурсию, вы действительно хотите продолжить?(yes\\no)";
                answerr.setAnswer(answer);
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String answer = scanner.nextLine();
                    answer.trim();
                    if (answer.equalsIgnoreCase("yes") == true) {
                        File file1 = new File(file_name);
                        Filling.AllCmd.execute_script(StudyGroupPriorityQueue, file1);
                        break;
                    } else {
                        if (answer.equalsIgnoreCase("no") == true) {
                            answer = "Хороший выбор";
                            answerr.setAnswer(answer);
                            break;
                        } else {
                            answer = "Введите ответ еще раз";
                            answerr.setAnswer(answer);
                        }
                    }
                }

            }
        }

    }
*/

    public static void head(Queue<StudyGroup> StudyGroupPriorityQueue) {
        try {
            StudyGroupPriorityQueue.stream().limit(1).forEach(studyGroup ->
                    answer = "Имя: " + studyGroup.getName() + " Номер:" + studyGroup.getStudentsCount() + " " + studyGroup.getexp() + " Форма обучения: " + studyGroup.getFormOfEducation() + " Id: " + studyGroup.getId()
                            + " Рост админа: " + studyGroup.getHeight() + " Вес админа: " + studyGroup.getWeight() + " Цвет глаз админа: " + studyGroup.getColor() + " Координата X: " + studyGroup.getCoordinatesX() + " Координата Y: " + studyGroup.getCoordinatesY());
            answerr.setAnswer(answer);
        } catch (NullPointerException e) {
            answer = "Нет здесь никакого первого элемента";
            answerr.setAnswer(answer);
        }
        answerr.cmd = "head";
    }

    public static void remove_head(Information information) throws Exception {
        Command.remove_head(information);
        Command.readdb();
        answerr.setAnswer(answer);
        answerr.cmd = "remove_head";
        // Filling.MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue.stream().skip(1).collect(Collectors.toCollection(() -> new PriorityQueue<>(Filling.XMLReader.countComparator)));
    }

    public static void remove_lover(Information information) throws Exception {
        Command.remove_lower(information);
        Command.readdb();
        answer = "Элементы, принадлежащие вам, удалены";
        answerr.setAnswer(answer);
        answerr.cmd = "remove_lower";
    }


    public static void remove_any_by_form_of_education(Information information) throws Exception {
        Command.remove_any_by_form_of_education(information);
        Command.readdb();
        answer = "Элементы, принадлежащие вам, удалены";
        answerr.setAnswer(answer);
        answerr.cmd = "remove_any_by_form_of_education";
    }

    public static void filter_starts_with_name(Queue<StudyGroup> StudyGroupPriorityQueue, String name) {
        answer = "";
        StudyGroupPriorityQueue.stream().filter(student -> student.getName().trim().startsWith(name)).forEach(student ->
                answer = answer + "\n" +
                        "Имя: " + student.getName() + " Номер:" + student.getStudentsCount() + " " + student.getexp() + " Форма обучения: " + student.getFormOfEducation() + " Id: " + student.getId()
                        + " Рост админа: " + student.getHeight() + " Вес админа: " + student.getWeight() + " Цвет глаз админа: " + student.getColor() + " Координата X: " + student.getCoordinatesX() + " Координата Y: " + student.getCoordinatesY() + "\n"
        );
        answerr.setAnswer(answer);
        MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue;
        answerr.cmd = "filter_starts_with_name";
    }


    public static void filter_greater_than_students_count(Queue<StudyGroup> StudyGroupPriorityQueue, long count) {
        answer = "";

        StudyGroupPriorityQueue.stream().filter(student -> student.getStudentsCount() > count).forEach(student -> answer =
                answer + "\n" + "Имя: " + student.getName() + " Номер:" + student.getStudentsCount() + " " + student.getexp() + " Форма обучения: " + student.getFormOfEducation() + " Id: " + student.getId()
                        + " Рост админа: " + student.getHeight() + " Вес админа: " + student.getWeight() + " Цвет глаз админа: " + student.getColor() + " Координата X: " + student.getCoordinatesX() + " Координата Y: " + student.getCoordinatesY() + "\n");
        answerr.setAnswer(answer);
        answerr.cmd = "filter_greater_than_students_count";
        MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue;
    }


    public static void file() {
        if (XMLReader.flag == 1) {
            answer = "Файл не может быть обработан, программа заканчивает работу";
            answerr.setAnswer(answer);
            answerr.wrong = 1;
        } else {
            answerr.wrong = 0;
            answer = "Файл передан";
            answerr.setAnswer(answer);
        }
    }

    public static void exit() {
        answerr.wrong = 2;
    }

}




