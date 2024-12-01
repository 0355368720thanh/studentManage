package ASM2.Array;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private StudentManagement studentManager;

    public UserInterface() {
        scanner = new Scanner(System.in);
        studentManager = new StudentManagement();
    }

    public void start() {
        while (true) {
            System.out.println("\nStudent Management Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Search Student by ID");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> studentManager.displayStudents();
                case 5 -> studentManager.sortStudentsByMarks();
                case 6 -> searchStudentById();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        studentManager.addStudent(id, name, marks);
    }

    private void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        System.out.print("Enter new Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        studentManager.editStudent(id, marks);
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        studentManager.deleteStudent(id);
    }

    private void searchStudentById() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();
        studentManager.searchStudentById(id);
    }
}
