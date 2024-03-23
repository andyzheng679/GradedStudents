package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class StudentTest {
    @Test
    public void testConstructor() {
        // Given
        String expectedFirstName = "Leon";
        String expectedLastName = "Hunter";
        Double[] expectedScores = {100.0, 95.0};

        // When
        Student student = new Student(expectedFirstName, expectedLastName, expectedScores);

        // Then
        Assert.assertEquals(expectedFirstName, student.getFirstName());
        Assert.assertEquals(expectedLastName, student.getLastName());
        Assert.assertArrayEquals(expectedScores, student.getExamScores().toArray(new Double[0]));
    }

    @Test
    public void testSetFirstName() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{});
        String expected = "Leo";

        // When
        student.setFirstName(expected);

        // Then
        Assert.assertEquals(expected, student.getFirstName());
    }

    @Test
    public void testSetLastName() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{});
        String expected = "Hunt";

        // When
        student.setLastName(expected);

        // Then
        Assert.assertEquals(expected, student.getLastName());
    }

    @Test
    public void testGetNumberOfExamsTaken() {
        // Given
        Double[] scores = {100.0, 95.0};
        Student student = new Student("Leon", "Hunter", scores);

        // Then
        Assert.assertEquals(2, student.getNumberOfExamsTaken());
    }

    @Test
    public void testGetExamScoresString() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{100.0, 95.0});

        // Then
        String expected = "Exam 1:100.0\nExam 2:95.0\n";
        Assert.assertEquals(expected, student.getExamScoresString());
    }

    @Test
    public void testAddExamScore() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{});
        double expectedScore = 100.0;

        // When
        student.addExamScore(expectedScore);

        // Then
        Assert.assertTrue(student.getExamScores().contains(expectedScore));
    }

    @Test
    public void testSetExamScore() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{100.0});
        double expectedScore = 150.0;

        student.setExamScore(1, expectedScore); // Note: Index 1 for the first exam, in a real-world scenario you'd manage indexes carefully

        Assert.assertEquals(expectedScore, student.getExamScores().get(0), 0.001);
    }

    @Test
    public void testGetAverageExamScore() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{100.0, 200.0});

        // Then
        Assert.assertEquals(150.0, student.getAverageExamScore(), 0.01); // Allow a small margin of error
    }

    @Test
    public void testToString() {
        // Given
        Student student = new Student("Leon", "Hunter", new Double[]{100.0, 95.0});

        // Print actual toString() result
        System.out.println(student.toString());  // Add this line

        // Then
        String expected = "Student Name: Leon Hunter\n> Average Score: 98\n> Exam Scores:\n    Exam 1 -> 100.0\n    Exam 2 -> 95.0\n";
        Assert.assertEquals(expected, student.toString()); // Check for equality
    }
}
