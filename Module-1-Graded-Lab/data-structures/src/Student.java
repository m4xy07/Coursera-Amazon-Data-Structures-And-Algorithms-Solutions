
public class Student {
        String name;
        int studentNumber;
        String[] subjects;
    ExamSchedule examSchedule;

        public Student(String name, int studentNumber, String[] subjects, ExamSchedule examSchedule) {
            this.name = name;
            this.studentNumber = studentNumber;
            this.subjects = subjects;
            this.examSchedule = examSchedule;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public ExamSchedule getExamSchedule() {
        return examSchedule;
    }

    public void setExamSchedule(ExamSchedule examSchedule) {
        this.examSchedule = examSchedule;
    }
}
