package edu.umb.cs681.hw03;

public class Student implements Comparable<Student> {
    private String name;
    private String courseName;
    private int score;
    private int age;
    
    public Student(String name, int age, int score , String courseName) {
        this.name = name;
        this.score = score;
        this.age= age;
        this.courseName=courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
 
    public String getCourseName() {
        return this.courseName;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public String getName() {
        return this.name;
    }
    public void setAge(int age) {
        this.age = age;
    }
 
    public int getAge() {
        return this.age;
    }
 
    public void setScore(int score) {
        this.score = score;
    }
 
    public int getScore() {
        return this.score;
    }
 
    public String toString() {
        return this.name + ", Age: " + this.age + ", Score: " + this.score+ ", Course: " + this.courseName;
    }
 
    public int compareTo(Student another) {
        return another.getScore() - this.score;
    }
}