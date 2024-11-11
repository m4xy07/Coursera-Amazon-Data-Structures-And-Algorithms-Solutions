
public class ExamSchedule {
    class Node {
        String exam;
        Node next;
        Node previous;

        public Node(String exam) {
            this.exam = exam;
            this.next = null;
            this.previous = null;
        }
    }

    Node head;
    Node tail;
    Node current; // To keep track of the current position

    public ExamSchedule() {
        head = null;
        tail = null;
        current = null;
    }

    public void addExam(String exam) {
        Node newNode = new Node(exam);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    public void printSchedule() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.exam);
            temp = temp.next;
        }
    }

    public void printScheduleReverse() {
        Node temp = tail;
        while (temp != null) {
            System.out.println(temp.exam);
            temp = temp.previous;
        }
    }

    public void resetCurrent() {
        current = head;
    }

    public String nextExam() {
        if (current == null) {
            return "No more exams or list is empty.";
        }
        String exam = current.exam;
        current = current.next;
        return exam;
    }

    public String previousExam() {
        if (current == null) {
            return "Start of the list or no exams scheduled.";
        }

        // Move current pointer to the previous exam and return its details
        current = current.previous;
        if (current != null) {
            return current.exam;
        } else {
            return "No previous exams available.";
        }
    }
}

