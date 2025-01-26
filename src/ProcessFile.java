import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessFile {
    public static Student processFile(Path filePath, Map<String, List<Double>> subjectGrades) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        if (lines.isEmpty()) {
            throw new IOException("Файл пуст: " + filePath.getFileName());
        }

        String name = lines.get(0).trim();
        if (!name.matches("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$")) {
            throw new IOException("Некорректное имя в файле: " + filePath.getFileName());
        }

        Student student = new Student(name);

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("-");
            if (parts.length != 2) continue;

            String subject = parts[0].trim();
            double grade;
            try {
                grade = Double.parseDouble(parts[1].trim());
                if (grade < 1 || grade > 5) {
                    throw new IllegalArgumentException("Некорректная оценка: " + grade);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в файле " + filePath.getFileName() + ": " + e.getMessage());
                continue;
            }

            student.addGrade(subject, grade);

            subjectGrades.putIfAbsent(subject, new ArrayList<>());
            subjectGrades.get(subject).add(grade);
        }

        return student;
    }
}
