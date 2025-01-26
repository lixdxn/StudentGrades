import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckingFiles {
    public static void checkingFiles(String directoryPath) throws IOException {
        Map<String, List<Double>> subjectGrades = new HashMap<>();
        List<Student> students = new ArrayList<>();

        File folder = new File(directoryPath);
        if (!folder.isDirectory()) {
            System.out.println("Путь неверный.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.startsWith("student") && !name.equals("report.txt"));
        if (files == null || files.length == 0) {
            System.out.println("В папке нет файлов формата .txt");
            return;
        }

        for (File file : files) {
            Student student = ProcessFile.processFile(file.toPath(), subjectGrades);
            students.add(student);
        }

        AnalyzeGrades.analyzeGrades(students, subjectGrades, directoryPath);
    }
}
