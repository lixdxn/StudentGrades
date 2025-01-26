import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class AnalyzeGrades {
    public static void analyzeGrades(List<Student> students, Map<String, List<Double>> subjectGrades, String directoryPath) throws IOException {
        List<String> result = new ArrayList<>();
        result.add("Средний балл по предметам:");

        for (Map.Entry<String, List<Double>> entry : subjectGrades.entrySet()) {
            String subject = entry.getKey();
            List<Double> grades = entry.getValue();

            double sum = 0.0;
            for (Double grade : grades) {
                sum += grade;
            }
            double average;
            if (grades.isEmpty()) {
                average = 0.0;
            } else {
                average = sum / grades.size();
            }
            result.add(subject + " - " + String.format("%.2f", average));

        }
        //    Лучший ученик
        Student bestStudent = null;
        double bestAverage = 0.0;
        for (Student student : students) {
            if (student.getAverageGrade() > bestAverage) {
                bestStudent = student;
                bestAverage = student.getAverageGrade();
            }
        }
        result.add("");
        result.add("Лучший ученик: ");
        if (bestStudent != null) {
            result.add(bestStudent.getName() + " (средний балл - " + String.format("%.2f", bestAverage) + ")");
        }

        //    Худший ученик
        Student worstStudent = null;
        double worstAverage = Double.MAX_VALUE;
        for (Student student : students) {
            if (student.getAverageGrade() < worstAverage) {
                worstStudent = student;
                worstAverage = student.getAverageGrade();
            }
        }
        result.add("");
        result.add("Худший ученик: ");
        if (worstStudent != null) {
            result.add(worstStudent.getName() + " (средний балл - " + String.format("%.2f", worstAverage) + ")");
        }
        result.add("");
        result.add("Количество учеников: " + students.size());

        System.out.println("Результты анализа:");
        for (String line : result) {
            System.out.println(line);
        }
        saveReportToFile(result, Path.of(directoryPath, "result.txt"));
    }
    private static void saveReportToFile(List<String> result, Path filePath) throws IOException {
        Files.write(filePath, result);
    }
}