package ASM2.List;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    private List<Student> students; // Using List instead of array

    public StudentManagement() {
        students = new ArrayList<>(); // Initialize with an ArrayList
    }

    public void addStudent(String id, String name, double marks) {
    var newStudent =    students.add(new Student(id, name, marks)); // Add new student
      if(newStudent)  displayStudents(); // Show updated list of students
    }

    public boolean editStudent(String id, double marks) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setMarks(marks);
                System.out.println("Student updated.");
                return false;
            }
        }
        System.out.println("Student not found.");
        return false;
    }

    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i); // Remove student by index
                System.out.println("Student deleted.");
                return false;
            }
        }
        System.out.println("Student not found.");
        return false;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Marks", "Rank");
            System.out.println("--------------------------------------------------------");
            for (Student student : students) {
                System.out.printf("%-10s %-20s %-10.2f %-15s%n",
                        student.getId(),
                        student.getName(),
                        student.getMarks(),
                        RankCalculator.calculateRank(student.getMarks())
                );
            }
        }
    }
    // Bubble sort for sorting students by mark
    public void sortStudentsByMarks() {
        // Sorting students based on marks in descending order using a Comparator
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) { // Descending order
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by marks.");
        displayStudents();
    }
    // Insertion sort for sorting students by name (ascending order)
    public void sortStudentsByName() {
        for (int i = 1; i < students.size(); i++) {
            Student key = students.get(i);
            int j = i - 1;

            // Compare names (case-insensitive)
            while (j >= 0 && students.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, key);
        }
        System.out.println("Students sorted by name."); 
        displayStudents();
    }

    public boolean searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return false;
            }
        }
        System.out.println("Student not found.");
        return false;
    }
    public boolean searchStudentByName(String name) {
        // Sắp xếp trước khi tìm kiếm
        sortStudentsByName();
        int left = 0, right = students.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midName = students.get(mid).getName();
            int comparison = midName.compareToIgnoreCase(name);

            if (comparison == 0) { // Tên khớp
                System.out.println("Student found: " + students.get(mid));
                return true;
            } else if (comparison < 0) { // Tên cần tìm lớn hơn
                left = mid + 1;
            } else { // Tên cần tìm nhỏ hơn
                right = mid - 1;
            }
        }
        System.out.println("Student not found with name: " + name);
        return false;
    }


}
