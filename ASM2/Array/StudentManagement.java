package ASM2.Array;

import java.util.Arrays;
import java.util.Comparator;

public class StudentManagement {
    private Student[] students;
    private int studentCount; // Keeps track of the number of students in the array

    public StudentManagement() {
        students = new Student[10]; // Initial capacity of 10 students
        studentCount = 0;
    }

    public void addStudent(String id, String name, double marks) {
        if (studentCount == students.length) {
            // Resize array if it's full
            students = Arrays.copyOf(students, students.length * 2);
        }
        students[studentCount++] = new Student(id, name, marks); // Add new student and increment count
        displayStudents();
    }

    public void editStudent(String id, double marks) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                students[i].setMarks(marks);
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                // Shift elements left to remove the student
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--studentCount] = null; // Decrease count and clear last element
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No students available.");
        } else {
            System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Marks", "Rank");
            System.out.println("--------------------------------------------------------");
            for (int i = 0; i < studentCount; i++) {
                System.out.printf("%-10s %-20s %-10.2f %-15s%n",
                        students[i].getId(),
                        students[i].getName(),
                        students[i].getMarks(),
                        RankCalculator.calculateRank(students[i].getMarks())
                );
            }
        }
    }

    public void sortStudentsByMarks() {
        // Sorting students based on marks in descending order using a Comparator
        Arrays.sort(students, 0, studentCount, Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("Students sorted by marks in descending order.");
    }

    public void searchStudentById(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                System.out.println(students[i]);
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
