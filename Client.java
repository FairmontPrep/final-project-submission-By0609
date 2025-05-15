import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> test_array_2 = new ArrayList<>();
        test_array_2.add(new ArrayList<>(Arrays.asList("1", " ", " ", "1", " ", " ", " ", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList("!!!", "1", "1", "1", " ", " ", " ", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "1", "1", " ", "1", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList("?", " ", "d", "?", "1", "1", " ", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "1", " ", "1", "1111 ", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "1", "1", "1", "000000", " ")));
        test_array_2.add(new ArrayList<>(Arrays.asList(" ", " ", " ", "1", "a", " ", " ", " "))); 
        test_array_2.add(new ArrayList<>(Arrays.asList("1", " ", " ", "1", "1", "1", "1", "1")));

        int rows = test_array_2.size();
        int cols = test_array_2.get(0).size();
        String[][] grid = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = test_array_2.get(r).get(c);
            }
        }

        PathFinder.map = grid;
        PathFinder.findPath();

        System.out.println("Answer list: " + PathFinder.answerList);
        PathFinder.printPath();
    }
}
