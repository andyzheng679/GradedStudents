package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ClassroomTest {
    private Classroom classroom;
    private Student student1, student2, student3;

    @Before
    public void setUp() {
        // Initialize some students
        student1 = new Student("Leon", "Hunter", new Double[]{100.0, 95.0});
        student2 = new Student("John", "Doe", new Double[]{85.0, 80.0});
        student3 = new Student("Jane", "Smith", new Double[]{70.0, 60.0});

        // Initialize classroom with 2 students
        classroom = new Classroom(new Student[]{student1, student2, null});
    }

    @Test
    public void testGetStudents() {
        Assert.assertArrayEquals(new Student[]{student1, student2, null}, classroom.getStudents());
    }

    @Test
    public void testGetAverageExamScore() {
        Assert.assertEquals(90.0, classroom.getAverageExamScore(), 0.01);
    }

    @Test
    public void testAddStudent() {
        classroom.addStudent(student3); // Adding third student
        Assert.assertArrayEquals(new Student[]{student1, student2, student3}, classroom.getStudents());
    }

    @Test
    public void testRemoveStudent() {
        classroom.removeStudent("Leon", "Hunter");
        Assert.assertArrayEquals(new Student[]{student2, null, null}, classroom.getStudents());
    }

    @Test
    public void testGetStudentsByScore() {
        Student[] expected = {student1, student2, null}; // Assuming this order by scores and then name
        Assert.assertArrayEquals(expected, classroom.getStudentsByScore());
    }

    @Test
    public void testGetGradeBook() {
        classroom.addStudent(student3); // Ensure classroom is full
        Map<Student, String> gradeBook = classroom.getGradeBook();

        // Debugging output
        gradeBook.forEach((student, grade) -> System.out.println(student.getFirstName() + " " + student.getLastName() + ": " + grade));

        Assert.assertEquals("A", gradeBook.get(student1));
        Assert.assertEquals("A", gradeBook.get(student2));
        Assert.assertEquals("A", gradeBook.get(student3)); // Based on your grading scale
    }
}
