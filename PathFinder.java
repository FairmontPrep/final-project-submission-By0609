import java.util.*;

public class PathFinder {
    public static String[][] map;
    public static ArrayList<String> answerList = new ArrayList<>();
    private static boolean[][] visited;
    private static int rows, cols;
    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void findPath() {
        rows = map.length;
        cols = map[0].length;
        visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ("1".equals(map[r][c]) && isEdge(r, c)) {
                    ArrayList<String> path = new ArrayList<>();
                    if (dfs(r, c, -1, path)) {
                        answerList = path;
                        return;
                    }
                }
            }
        }
    }

    private static boolean dfs(int r, int c, int lastDir, ArrayList<String> path) {
        if (!inBounds(r, c) || visited[r][c] || !"1".equals(map[r][c]))
            return false;

        visited[r][c] = true;
        path.add("A[" + r + "][" + c + "]");

        if (path.size() >= 2 && isEdge(r, c) && isAdjacentEdge(path.get(0), "A[" + r + "][" + c + "]") && hasTurn(path)) {
            return true;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + DIRS[dir][0];
            int nc = c + DIRS[dir][1];
            if (dfs(nr, nc, dir, path))
                return true;
        }

        path.remove(path.size() - 1);
        visited[r][c] = false;
        return false;
    }

    private static boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private static boolean isEdge(int r, int c) {
        return r == 0 || r == rows - 1 || c == 0 || c == cols - 1;
    }

    private static boolean isAdjacentEdge(String start, String end) {
        int[] a = parse(start);
        int[] b = parse(end);
        return (a[0] == 0 && b[1] == 0) || (a[0] == 0 && b[1] == cols - 1) ||
               (a[0] == rows - 1 && b[1] == 0) || (a[0] == rows - 1 && b[1] == cols - 1) ||
               (a[1] == 0 && b[0] == 0) || (a[1] == 0 && b[0] == rows - 1) ||
               (a[1] == cols - 1 && b[0] == 0) || (a[1] == cols - 1 && b[0] == rows - 1);
    }

    private static boolean hasTurn(ArrayList<String> path) {
        if (path.size() < 3) return false;
        for (int i = 2; i < path.size(); i++) {
            int[] p1 = parse(path.get(i - 2));
            int[] p2 = parse(path.get(i - 1));
            int[] p3 = parse(path.get(i));
            int dx1 = p2[0] - p1[0], dy1 = p2[1] - p1[1];
            int dx2 = p3[0] - p2[0], dy2 = p3[1] - p2[1];
            if (dx1 != dx2 || dy1 != dy2)
                return true;
        }
        return false;
    }

    private static int[] parse(String coord) {
        coord = coord.replace("A[", "").replace("]", "");
        String[] parts = coord.split("\\[|\\]|,");
        return new int[]{ Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) };
    }

    public static void printAnswerList() {
        System.out.println(answerList);
    }

    public static void printPath() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String coord = "A[" + r + "][" + c + "]";
                if (answerList.contains(coord)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
