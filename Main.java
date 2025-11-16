import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final String INPUT_FILE = "input.txt";
    private static String start;
    private static String end;
    private static Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("\n\n\nThuật toán BFS : ");
        readFile();
        System.out.println(map);
        System.out.println("\n\n\n");
    }

    private static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Bắt đầu")) {
                    String[] startPoint = line.split(":", 2);  // tách 1 lần
                    if (startPoint.length > 1) {
                        start = startPoint[1].trim();
                        System.out.println("Điểm bắt đầu là: " + start);
                    }
                }

                else if (line.startsWith("Kết thúc")) {
                    String[] endPoint = line.split(":", 2);
                    if (endPoint.length > 1) {
                        end = endPoint[1].trim();
                        System.out.println("Điểm kết thúc là: " + end);
                    }
                }

                else if (line.startsWith("Đường đi")) {
                    String[] way = line.split(":", 2);

                    if (way.length > 1) {
                        String path = way[1].trim();
                        String[] node = path.split("->", 2);

                        if (node.length == 2) {
                            String key = safeTrim(node[0]);
                            String child = safeTrim(node[1]);
                            map.computeIfAbsent(key, k -> new ArrayList<>()).add(child);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String safeTrim(String s) {
        return (s == null) ? "" : s.trim();
    }
}
