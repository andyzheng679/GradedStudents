package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    //constructor
    public Student(String firstName, String lastName, Double[] testScores){
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>(Arrays.asList(testScores));
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Double> getExamScores() {
        return new ArrayList<>(examScores);
    }

    public int getNumberOfExamsTaken(){
        return examScores.size();
    }

    public String getExamScoresString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < examScores.size(); i++){
            stringBuilder.append("Exam ").append(i + 1).append(":").append(examScores.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
    //stringbuilder, for every score in examscore, append it to stringbuilder, for loop bc need i as a counter

    public void addExamScore(double examScore){
        examScores.add(examScore);
    }

    public void setExamScore(int examNumber, double newScore){
        examScores.set(examNumber - 1, newScore);   //-1 bc exam 1 is the 0th index
    }

    public double getAverageExamScore(){
        double sum = 0;
        //double avg = 0;
        for (int i = 0; i < examScores.size(); i++){
            sum += examScores.get(i);
        }
        return sum / examScores.size();
    }
    //sum var, for every exam score, add it to sum, return sum / size of list

    @Override   //need to override bc toString is in object calss
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student Name: ").append(firstName).append(" ").append(lastName).append("\n");

        double average = this.getAverageExamScore();
        stringBuilder.append("> Average Score: ").append(Math.round(average)).append("\n");

        stringBuilder.append("> Exam Scores:\n");
        for (int i = 0; i < examScores.size(); i++) {
            stringBuilder.append("    Exam ").append(i + 1).append(" -> ").append(examScores.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }


}
