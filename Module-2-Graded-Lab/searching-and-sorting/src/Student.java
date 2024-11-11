import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    Integer studentNumber;
    String name;
    ArrayList<Subject> subjects = new ArrayList<>();

    HashMap<String, Exam> examsMap = new HashMap<>();

    public Student(String name, int studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public String toString(){
        return studentNumber + "\t" + name;
    }

    public String getName() {
        return name;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubjects(ArrayList<Subject> subjects) {
        this.subjects.addAll(subjects);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
    public void addExam(Exam exam) {
        examsMap.put(exam.subjectId, exam);
    }
    public void addExams(List<Exam> exams) {
        for (Exam exam : exams) {
            addExam(exam);
        }
    }
    public List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        for(Exam exam : examsMap.values()) {
            exams.add(exam);
        }
        return exams;
    }

}
