package io.zipcoder;

import java.util.*;
import java.util.stream.Collectors;

public class Classroom {
    private Student[] students;

    public Classroom(int maxNumberOfStudents) {
        this.students = new Student[maxNumberOfStudents];
    }

    public Classroom(Student[] students) {
        this.students = students;
    }

    public Classroom() {
        this.students = new Student[30];
    }

    public Student[] getStudents() {
        return students;
    }

    public double getAverageExamScore() {
        double sumAvgScore = 0;
        int validCount = 0; // Count of non-null students
        for (Student student : students) {
            if (student != null) { // Check if the student is not null
                sumAvgScore += student.getAverageExamScore();
                validCount++; // Increment count only for non-null students
            }
        }
        return validCount == 0 ? 0 : sumAvgScore / validCount; // Prevent division by zero
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) { // Check for an empty seat
                students[i] = student; // Add the new student to the empty seat
                return;
            }
        }
        System.out.println("Full");
    }

    public void removeStudent(String firstName, String lastName) {
        boolean found = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getFirstName().equals(firstName) && students[i].getLastName().equals(lastName)) {
                students[i] = null;
                found = true;
            }
            if (found == true && i < students.length - 1) {
                students[i] = students[i + 1]; //fill null with next stu
                students[i + 1] = null; //set next stu to null
            }
        }
    }
    //bool var to false, if student is found set to tru
    //for loop, value at i index != null, and fname and lname is both equal, set student[i] to null, bool to true
    //outside if statement, if found = true && i is not at the end of index, move students forward

    public Student[] getStudentsByScore() {
        // Convert the array to a list for easier processing
        List<Student> studentList = Arrays.stream(students)
                .filter(Objects::nonNull) // Filter out null values first
                .sorted(Comparator.comparing(Student::getAverageExamScore).reversed() // Sort by average score descending
                        .thenComparing(Student::getLastName) // Then by last name ascending
                        .thenComparing(Student::getFirstName)) // Then by first name ascending
                .collect(Collectors.toList());

        //stream is like a conveyor belt of elements that you can perform operations on,
        //such as sorting or filtering. It's part of Java's way of handling collections of items

        // Convert back to an array to match the return type
        Student[] sortedStudents = new Student[students.length];
        return studentList.toArray(sortedStudents);
    }

    public Map<Student, String> getGradeBook() {
        // Create a map to store the student-grade pairs
        Map<Student, String> gradeBook = new HashMap<>();

        // Ensure there are no null students
        Student[] nonNullStudents = Arrays.stream(students).filter(java.util.Objects::nonNull).toArray(Student[]::new);

        // Sort the students by their average scores in descending order
        Arrays.sort(nonNullStudents, Comparator.comparingDouble(Student::getAverageExamScore).reversed());

        // Calculate percentile thresholds
        int totalStudents = nonNullStudents.length;
        int aCutoff = (int) Math.ceil(0.90 * totalStudents);
        int bCutoff = (int) Math.ceil(0.70 * totalStudents);
        int cCutoff = (int) Math.ceil(0.50 * totalStudents);
        int dCutoff = (int) Math.ceil(0.11 * totalStudents);

        // Assign letter grades based on percentiles
        for (int i = 0; i < totalStudents; i++) {
            String grade;
            if (i < aCutoff) grade = "A";
            else if (i < bCutoff) grade = "B";
            else if (i < cCutoff) grade = "C";
            else if (i < dCutoff) grade = "D";
            else grade = "F";

            gradeBook.put(nonNullStudents[i], grade);
        }

        return gradeBook;
    }
}
