public class Exam {
    int examId;
    String subjectId;
    String date;
    String time;

    public Exam(int id, String subjectId, String date, String time) {
        this.examId = id;
        this.subjectId = subjectId;
        this.date = date;
        this.time = time;
    }
    public int getExamId() {
        return examId;
    }
    public String toString(){

        return examId + "\t" + date + "\t" + time;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
