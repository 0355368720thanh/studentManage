package ASM2.List;

import java.util.InputMismatchException;
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
            try {
                System.out.println("\nStudent Management Menu (List):");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Display Students");
                System.out.println("5. Sort Students by Marks");
                System.out.println("6. Sort Students by Name");
                System.out.println("7. Search Student by ID");
                System.out.println("8. Search Student by Name");
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
                    case 6 -> studentManager.sortStudentsByName();
                    case 7 -> searchStudentById();
                    case 8 -> searchStudentByName();
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addStudent() {
        try {
            String id;
            while (true) {
                System.out.print("Enter Student ID (numeric only): ");
                id = scanner.nextLine();
                if (id.matches("\\d+")) {  // ID chỉ chứa số
                    break;
                } else {
                    System.out.println("Invalid ID. Please enter numeric characters only.");
                }
            }

            String name;
            while (true) {
                System.out.print("Enter Student Name (letters only): ");
                name = scanner.nextLine();
                if (name.matches("[a-zA-Z ]+")) {  // Tên chỉ chứa chữ cái và khoảng trắng
                    break;
                } else {
                    System.out.println("Invalid name. Please enter letters only.");
                }
            }

            double marks;
            while (true) {
                try {
                    System.out.print("Enter Marks (0-10): ");
                    marks = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (marks >= 0 && marks <= 10) {  // Kiểm tra điểm trong khoảng từ 0 đến 10
                        break;
                    } else {
                        System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid marks. Please enter a numeric value.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            studentManager.addStudent(id, name, marks);
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the student: " + e.getMessage());
        }
    }

    private void editStudent() {
        try {
            System.out.print("Enter Student ID to edit (numeric only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                System.out.println("Invalid ID. Please enter numeric characters only.");
                return;
            }

            double marks;
            while (true) {
                try {
                    System.out.print("Enter new Marks (0-10): ");
                    marks = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (marks >= 0 && marks <= 10) {  // Kiểm tra điểm trong khoảng từ 0 đến 10
                        break;
                    } else {
                        System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid marks. Please enter a numeric value.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            if (studentManager.editStudent(id, marks)) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while editing the student: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete (numeric only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                System.out.println("Invalid ID. Please enter numeric characters only.");
                return;
            }
            if (studentManager.deleteStudent(id)) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the student: " + e.getMessage());
        }
    }

    private void searchStudentById() {
        try {
            System.out.print("Enter Student ID to search (numeric only): ");
            String id = scanner.nextLine();
            if (!id.matches("\\d+")) {
                System.out.println("Invalid ID. Please enter numeric characters only.");
                return;
            }
            if (!studentManager.searchStudentById(id)) {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching for the student: " + e.getMessage());
        }
    }
    private void searchStudentByName() {
        try {
            System.out.print("Enter Student Name to search: ");
            String name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {  // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
                if (!studentManager.searchStudentByName(name)) {
                    System.out.println("Student with name \"" + name + "\" not found.");
                }
            } else {
                System.out.println("Invalid name. Please enter letters only.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching for the student: " + e.getMessage());
        }
    }


}
