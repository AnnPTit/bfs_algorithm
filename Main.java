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
        Queue<String> myQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();
        // kiểm tra trống start và end
        if (start == null || end == null) {
            System.out.println("Thiếu điểm bắt đầu hoặc kết thúc !");
            return;
        }
        if (start.equals(end)) {
            System.out.println("Điểm bắt đầu là điểm kết thúc!");
            return;
        }
        // Khởi tạo queue với trạng thái bắt đầu
        myQueue.add(start);
        System.out.println(map);
        while (!myQueue.isEmpty()) {
            String item = myQueue.poll();
            System.out.println("Đã lấy: " + item);
            System.out.println("Đỉnh kề : " + map.get(item));
            if (end.equals(item)) {
                System.out.println("Điểm kết thúc là : " + item);
                // tính toán đường đi
                printPath(parent);
                return;
            }
            if (visited.contains(item)) {
                continue;
            }
            // nếu không phải -> lấy các đỉnh kề thêm vào queue
            List<String> neighbors = map.get(item);
            if (neighbors == null) {
                System.out.println("Đỉnh không có đỉnh kề ! ");
                continue;
            } else {
                myQueue.addAll(neighbors);
                neighbors.stream()
                        .filter(i -> parent.get(i) == null).
                        forEach(i -> parent.put(i, item));
            }
            visited.add(item);
            System.out.println("Đỉnh đã xét : " + visited);
            System.out.println("Queue : " + myQueue);
            System.out.println("Đỉnh cha : " + parent);
            System.out.println("\n\n");
        }
        System.out.println("\n\n\n");
    }

    private static void printPath(Map<String, String> parent) {
        List<String> path = new ArrayList<>();
        String curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(path);

        if (path.get(0).equals(start)) {
            System.out.println("Đường đi là: " + String.join("->", path));
        } else {
            System.out.println("Không thể truy vết đường đi về điểm bắt đầu.");
        }
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
