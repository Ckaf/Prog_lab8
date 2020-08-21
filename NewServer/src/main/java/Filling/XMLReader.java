package Filling;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;

/**
 * Reading data from a file
 */
public class XMLReader {
    //private static final String FILENAME = "src\\com\\company\\list";
    public static String FILENAME;
    static Queue<StudyGroup> StudyGroupPriorityQueue = null;
    static User user;
    static int flag = 0;

    public static void main(ByteArrayInputStream fileInputStream) throws Exception {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        //Класс BufferedInputStream накапливает вводимые данные в специальном буфере без постоянного обращения к устройству ввода.
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = null;
        // Получение списка всех элементов Student внутри корневого элемента

        try {
            Logger.login(Level.INFO, "Принимаем пакет с файлом");
            document = builder.parse(bufferedInputStream);
            NodeList studentElements = document.getDocumentElement().getElementsByTagName("Student");


            StudyGroupPriorityQueue = new PriorityQueue<StudyGroup>(studentElements.getLength(), countComparator);
            // Перебор всех элементов Student
            for (int i = 0; i < studentElements.getLength(); i++) {
                Node student = studentElements.item(i);
                // Получение атрибутов каждого элемента
                NamedNodeMap attributes = student.getAttributes();

                // Добавление студента. Атрибут - Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
               /* StudyGroupPriorityQueue.add(new StudyGroup(StudyGroupPriorityQueue, attributes.getNamedItem("name").getNodeValue(), attributes.getNamedItem("count").getNodeValue(), attributes.getNamedItem("exp").getNodeValue(),
                        attributes.getNamedItem("form").getNodeValue(), attributes.getNamedItem("semester").getNodeValue(), attributes.getNamedItem("groupAdmin").getNodeValue(), attributes.getNamedItem("height").getNodeValue(),
                        attributes.getNamedItem("weight").getNodeValue(), attributes.getNamedItem("eyeColor").getNodeValue(),
                        attributes.getNamedItem("X").getNodeValue(), attributes.getNamedItem("Y").getNodeValue(),));
*/
                flag = 0;
            }
        } catch (Exception e) {
            flag = 1;
            Logger.login(Level.WARNING, "Файл невозможно обработать");
        }
        //заполняем данные пользователя
     //   Filling.MessageHandling.StudyGroupPriorityQueue=StudyGroupPriorityQueue;
    }


    //метод для обработки данных очереди
    public static Comparator<StudyGroup>
            countComparator = new Comparator<StudyGroup>() {

        @Override
        public int compare(StudyGroup c1, StudyGroup c2) {
            return (int) (c1.getStudentsCount() - c2.getStudentsCount());
        }
    };
}




