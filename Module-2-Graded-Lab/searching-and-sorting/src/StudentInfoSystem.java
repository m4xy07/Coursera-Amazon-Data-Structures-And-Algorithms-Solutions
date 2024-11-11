import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class StudentInfoSystem {

    private Database db = new Database();

    /*******************************************************
     *
     *  METHODS THAT NEED IMPROVEMENT
     *
     *******************************************************/

    public List<Subject> sortSubjectsByName(){

        List<Subject> subjectsByName =
                (List<Subject>) db.subjects.clone();

        /* TODO 6: Implement the bubble sort algorithm to sort the
                   Subject objects by name.*/
        int n = subjectsByName.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                Subject currentReg = (Subject) subjectsByName.get(j);
                Subject nextReg = (Subject) subjectsByName.get(j + 1);
                if (currentReg.name.compareTo(nextReg.name) > 0) {
                    subjectsByName.set(j, nextReg);
                    subjectsByName.set(j + 1, currentReg);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return subjectsByName;
    }

    public List<Student> sortStudentsByName(){
        List<Student> students = (List<Student>) db.students.clone();

        /* TODO 18: Implement the insertion sort algorithm to sort the
                   Student objects by name.*/
        int studentCount = students.size();
        for (int unsorted_Indx = 1; unsorted_Indx < studentCount; unsorted_Indx++) {
            Student unsortedStudent =
                    (Student)students.get(unsorted_Indx);
            int sorted_Indx = unsorted_Indx - 1;
            while (sorted_Indx >= 0) {
                Student sortedStudent =
                        (Student)students.get(sorted_Indx);

                if (sortedStudent.getName().compareTo(unsortedStudent.getName()) > 0) {
                    students.set(sorted_Indx + 1, sortedStudent);
                    sorted_Indx--; // Move to the next element to the left
                } else {
                    break;
                }
            }
            students.set(sorted_Indx + 1, unsortedStudent);
        }
        return students;
    }

    public Student findStudent(Integer studentNumber) {
        List<Student> students = this.sortStudentsByNumber();

        /* TODO 28: Replace the linear search for the student with a
                   binary search. */
        int left = 0; // Start at the beginning of the list
        int right = students.size() - 1; // Start at the end of the list
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparitor = students.get(mid).studentNumber.compareTo(studentNumber);
            if (comparitor == 0){
                return students.get(mid); // its a match!
            }else if (comparitor < 0){
                left = mid + 1; // Ignore the left half and look in the right half
            }else {
                right = mid - 1; // Ignore the right half and look in the left half
            }
        }
        return null; // If we never find the license

    }

    /*******************************************************
     *
     *  METHODS THAT DO NOT NEED IMPROVEMENT
     *
     *******************************************************/
    public Student findStudent(String name){
        List<Student> students = this.sortStudentsByName();
        int left = 0; // Start at the beginning of the list
        int right = students.size() - 1; // Start at the end of the list
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparitor = students.get(mid).name.compareTo(name);
            if (comparitor == 0){
                return students.get(mid); // its a match!
            }else if (comparitor < 0){
                left = mid + 1; // Ignore the left half and look in the right half
            }else {
                right = mid - 1; // Ignore the right half and look in the left half
            }
        }
        return null; // If we never find the license
    }

    public List<Exam> findExams(String subjectId){
        List<Exam> sortedExams = this.sortExamsBySubjectId();
        ArrayList<Exam> foundExams = new ArrayList<>();
        for (Exam exam : sortedExams) {
            if (exam.getSubjectId().equals(subjectId)){
                foundExams.add(exam);
                if(foundExams.size() == 2){
                    break;
                }
            }
        }
        return foundExams;
    }

    public Exam findExam(int examId, List<Exam> inExams) {
        for (Exam exam : inExams) {
            if (exam.examId == examId) {
                return exam;
            }
        }
        return null;
    }

    /* Implement using linear search */
    public Subject findSubject(String subjectId){
        List<Subject> subjects = this.sortSubjectsById();
        for (Subject subject : subjects) {
            if(subject.id.equals(subjectId)){
                return subject;
            }
        }
        return null;

    }
    public List<Student> sortStudentsByNumber() {
        List<Student> studentsById =
                (List<Student>) db.students.clone();
        int n = studentsById.size();
        for (int subArrSize = 1; subArrSize < n; subArrSize *= 2) {
            for (int leftStart = 0; leftStart < n - subArrSize; leftStart += 2 * subArrSize) {
                int rightStart = leftStart + subArrSize;
                int rightEndExcluded = Math.min(leftStart + 2 * subArrSize, n);
                ArrayList<Student> leftArray = new ArrayList<>(studentsById.subList(leftStart, rightStart));
                ArrayList<Student> rightArray = new ArrayList<>(studentsById.subList(rightStart, rightEndExcluded));
                merge(studentsById, leftStart, leftArray, rightArray);
            }
        }
        return studentsById;
    }
    private void merge(List<Student> studentsById, int mergeIndex, ArrayList leftArray, ArrayList rightArray) {
        int left_Indx = 0, right_Indx = 0;
        Student leftVal;
        Student rightVal;
        while (left_Indx < leftArray.size() && right_Indx < rightArray.size()) {
            leftVal = (Student) leftArray.get(left_Indx);
            rightVal = (Student) rightArray.get(right_Indx);
            if (leftVal.studentNumber <= rightVal.studentNumber) {
                studentsById.set(mergeIndex, leftVal);
                left_Indx++;
            } else {
                studentsById.set(mergeIndex, rightVal);
                right_Indx++;
            }
            mergeIndex++;
        }
        while (left_Indx < leftArray.size()) {
            leftVal = (Student) leftArray.get(left_Indx);
            studentsById.set(mergeIndex, leftVal);
            left_Indx++;
            mergeIndex++;
        }
        while (right_Indx < rightArray.size()) {
            rightVal = (Student) rightArray.get(right_Indx);
            studentsById.set(mergeIndex, rightVal);
            right_Indx++;
            mergeIndex++;
        }
    }
    public List<Subject> sortSubjectsById(){
        TreeMap<String, Subject> subjectMap = new TreeMap<>();
        for(Subject subject : db.subjects){
            subjectMap.put(subject.id, subject);
        }
        List<Subject> valueList = new ArrayList<>(subjectMap.values());
        return valueList;
    }
    public List<Exam> sortExamsBySubjectId(){
        List<Exam> examsToSort = (List<Exam>) db.exams.clone();
        int regCount = examsToSort.size();
        for (int unsorted_Indx = 1; unsorted_Indx < regCount; unsorted_Indx++) {
            Exam unsortedReg =
                    (Exam)examsToSort.get(unsorted_Indx);
            int sorted_Indx = unsorted_Indx - 1;
            while (sorted_Indx >= 0) {
                Exam sortedReg =
                        (Exam)examsToSort.get(sorted_Indx);
                if (sortedReg.subjectId.compareTo(unsortedReg.subjectId) > 0) {
                    examsToSort.set(sorted_Indx + 1, sortedReg);
                    sorted_Indx--; // Move to the next element to the left
                } else {
                    break;
                }
            }
            examsToSort.set(sorted_Indx + 1, unsortedReg);
        }
        return examsToSort;
    }
    public void addStudent(Student student) {
        db.students.add(student);
    }
    public void removeStudent(Student student) {
        db.students.remove(student);
    }
    public int newStudentNumber() {
        List<Student> students = this.sortStudentsByNumber();
        Student lastStudent = students.get(students.size() - 1);
        int lastId = lastStudent.getStudentNumber();
        return lastId++;
    }
    public static void main(String [] args){
        StudentInfoSystem studentInfoSystem = new StudentInfoSystem();

        /* TODO 8: Uncomment the SORT SUBJECTS BY NAME code block */
         System.out.println("\n\nSORT SUBJECTS BY NAME\n");
         List<Subject> subjects = studentInfoSystem.sortSubjectsByName();
         for (Subject subject : subjects) {
         System.out.println(subject);
         }

        /* TODO 20: Uncomment the SORT STUDENTS BY NAME code block. */
        System.out.println("\n\nSORT STUDENTS BY NAME\n");
        List<Student> students = studentInfoSystem.sortStudentsByName();
        for (Student student : students) {
            System.out.println(student);
        }

    }
}
