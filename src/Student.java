import java.util.HashMap;
import java.util.Map;

public class Student {
    public final String name;
    public final Map<String, Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public void addGrade(String subject, double grade){
        if (grades.containsKey(subject)) {
            throw new IllegalArgumentException("Повторная запись оценки для предмета: " + subject);
        }
        grades.put(subject, grade);
    }

    public double getAverageGrade() {
        return grades.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

}
