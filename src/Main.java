import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке: ");
        String directoryPath = scanner.nextLine();
        CheckingFiles.checkingFiles(directoryPath);
    }
}