import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 5; // Размер поля
    private static final int MINES = 3; // Количество мин
    private static char[][] field = new char[SIZE][SIZE];
    private static boolean[][] mines = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        initializeField();
        placeMines();
        calculateHints();
        playGame();
    }

    private static void initializeField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = '?'; // Изначально все клетки закрыты
            }
        }
    }

    private static void placeMines() {
        Random random = new Random();
        int count = 0;
        while (count < MINES) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (!mines[row][col]) {
                mines[row][col] = true; // Устанавливаем мину
                count++;
            }
        }
    }

    private static void calculateHints() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mines[i][j]) continue; // Если это мина, пропускаем
                int mineCount = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int newRow = i + x;
                        int newCol = j + y;
                        if (isInBounds(newRow, newCol) && mines[newRow][newCol]) {
                            mineCount++; // Считаем количество мин вокруг
                        }
                    }
                }
                field[i][j] = (char) ('0' + mineCount); // Устанавливаем количество мин
            }
        }
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE; // Проверяем границы поля
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printField();
            System.out.print("Введите строку и столбец (например, 1 2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (mines[row][col]) {
                System.out.println("Вы наткнулись на мину! Игра окончена.");
                break;
            } else {
                field[row][col] = field[row][col]; // Открываем клетку
            }
        }
        scanner.close();
    }

    private static void printField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " "); // Выводим текущее состояние поля
            }
            System.out.println();
        }
    }
}