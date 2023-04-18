import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int islands = scanner.nextInt();
        ShipTour newshiptour = new ShipTour(islands);
        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int island = scanner.nextInt();
            int island2 = scanner.nextInt();
            newshiptour.addedge(new Island(2, island), new Island(2, island2));
        }
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        newshiptour.shortestcycle(new Island(2, start), new Island(2, end));
    }

}

