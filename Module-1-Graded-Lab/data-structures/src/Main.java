
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentInfoSystem system = new StudentInfoSystem();
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. View Students");
            System.out.println("4. View Exam Schedule");
            System.out.println("5. View next exam");
            System.out.println("6. View previous exam");
            System.out.println("7. Undo Last Action");
            System.out.println("8. Exit");
            System.out.println("Enter your choice:");
            int choice = -1;
            try {
                choice = keyboard.nextInt();
                keyboard.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                keyboard.nextLine(); // Clear invalid input
                continue; // Skip the rest of the loop iteration
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter student name:");
                    String name = keyboard.nextLine();
                    System.out.println("Enter student number:");
                    int number = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Enter number of subjects:");
                    int subjectCount = keyboard.nextInt();
                    keyboard.nextLine();
                    String[] subjects = new String[subjectCount];
                    for (int i = 0; i < subjectCount; i++) {
                        System.out.println("Enter subject Name " + (i + 1) + ":");
                        subjects[i] = keyboard.nextLine();
                    }
                    ExamSchedule examSchedule = new ExamSchedule();
                    System.out.println("Enter number of exams:");
                    int examCount = keyboard.nextInt();
                    keyboard.nextLine();
                    for (int i = 0; i < examCount; i++) {
                        System.out.println("Enter exam (Date - Time - Location):");
                        examSchedule.addExam(keyboard.nextLine());
                    }
                    Student student = new Student(name, number, subjects, examSchedule);
                     system.addStudent(student);
                    break;
                case 2:
                    System.out.println("Enter student number to remove:");
                    int studentNumber = keyboard.nextInt();
                    system.removeStudent(studentNumber);
                    break;
                case 3:
                    system.viewStudents();
                    break;
                case 4:
                    System.out.println("Enter student number to view exam schedule:");
                    studentNumber = keyboard.nextInt();
                    system.viewExamSchedule(studentNumber);
                    break;
                case 5:
                    System.out.println("Enter student number to view to the next exam:");
                    studentNumber = keyboard.nextInt();
                    system.navigateNextExam(studentNumber);
                    break;
                case 6:
                    System.out.println("Enter student number to view to the previous exam:");
                    studentNumber = keyboard.nextInt();
                    system.navigatePreviousExam(studentNumber);
                    break;
                case 7:
                    system.undoLastAction();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
