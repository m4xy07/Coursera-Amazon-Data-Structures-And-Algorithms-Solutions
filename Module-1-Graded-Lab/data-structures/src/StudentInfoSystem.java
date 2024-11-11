
import java.util.ArrayList;

import java.util.Stack;

public class StudentInfoSystem {
    ArrayList<Student> students;
    Stack<String> actions;

    public StudentInfoSystem() {
        students = new ArrayList<>();
        actions = new Stack<>();
    }

    public boolean addStudent(Student student) {
       for (Student s: students) {
           if(s.getStudentNumber() == student.getStudentNumber())
           {
               System.out.println("Student with number " + student.getStudentNumber() + " already exists.");
               return false; // Exit the method if a duplicate is found
           }
       }
        students.add(student);
        actions.push("Added student: " + student.name);
        System.out.println("Student added: " + student.name);
        return true;
    }



    public void removeStudent(int studentNumber) {
        Student toRemove = null;
        for (Student student : students) {
            if (student.studentNumber == studentNumber) {
                toRemove = student;
                break;
            }
        }

        if (toRemove != null) {
            students.remove(toRemove);
            actions.push("Removed student: " + toRemove.name);
            System.out.println("Student removed: " + toRemove.name);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            System.out.println("Student Details:");
            System.out.println("Student Name: " + student.name);
            System.out.println("Student Number: " + student.studentNumber);

            if (student.subjects != null && student.subjects.length > 0) {
                System.out.println("Subjects:");
                for (String subject : student.subjects) {
                    System.out.println(" " + subject);
                }
            } else {
                System.out.println("No subjects available.");
            }
            if (student.examSchedule != null) {
                System.out.println("exam schedule : ");
                student.examSchedule.printSchedule();
            } else {
                System.out.println("No exam schedule available.");
            }
            System.out.println();
        }
    }

    public void viewExamSchedule(int studentNumber) {
        for (Student student : students) {
            if (student.studentNumber == studentNumber) {
                System.out.println("Exam Schedule for " + student.name + ":");
                student.examSchedule.printSchedule();
                return;
            }
        }
        System.out.println("Student not found.");
    }
    public void undoLastAction() {
        if (!actions.isEmpty()) {
            String lastAction = actions.pop();
            System.out.println("Undoing: " + lastAction);

            if (lastAction.startsWith("Added student: ")) {
                String studentName = lastAction.substring(15);
                Student toRemove = null;
                for (Student student : students) {
                    if (student.name.equals(studentName)) {
                        toRemove = student;
                        break;
                    }
                }
                if (toRemove != null) {
                    students.remove(toRemove);
                    System.out.println("Student " + studentName + " has been removed.");
                } else {
                    System.out.println("Student not found.");
                }
            } else if (lastAction.startsWith("Removed student: ")) {
                // Implement logic to re-add the student, if data is available
                System.out.println("Cannot undo removal, student data not saved.");
            }
        } else {
            System.out.println("No actions to undo.");
        }
    }

    public void navigateNextExam(int studentNumber) {
        for (Student student : students) {
            if (student.studentNumber == studentNumber) {
                if (student.examSchedule.current == null) {
                    student.examSchedule.resetCurrent();
                }
                System.out.println("Next Exam: " + student.examSchedule.nextExam());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void navigatePreviousExam(int studentNumber) {
        for (Student student : students) {
            if (student.studentNumber == studentNumber) {
                if (student.examSchedule.current == null) {
                    student.examSchedule.tail = student.examSchedule.tail;
                }
                System.out.println("Previous Exam: " + student.examSchedule.previousExam());
                return;
            }
        }
        System.out.println("Student not found.");
    }
}