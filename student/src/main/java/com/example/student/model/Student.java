package com.example.student.model;
import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name="students")
public class Student {
 @Id
 private int studentId;
 private String studentName;
 private String email;
 private String courseName;
 private double programmingScore;
 private double mathematicsScore;
 @ElementCollection(fetch=FetchType.EAGER)
 @CollectionTable(name="student_skills",joinColumns=@JoinColumn(name="student_id"))
 @Column(name="skill")
 private List<String> technicalSkills = new ArrayList<>();
 public Student() {}
 public Student(int studentId, String studentName, String email, String courseName, double programmingScore, double mathematicsScore, List<String> technicalSkills) {
  this.studentId=studentId;
  this.studentName=studentName;
  this.email=email;
  this.courseName=courseName;
  this.programmingScore=programmingScore;
  this.mathematicsScore=mathematicsScore;
  this.technicalSkills=technicalSkills;
 }
 public int getStudentId() { return studentId; }
 public void setStudentId(int value) { this.studentId=value; }
 public String getStudentName() { return studentName; }
 public void setStudentName(String value) { this.studentName=value; }
 public String getEmail() { return email; }
 public void setEmail(String value) { this.email=value; }
 public String getCourseName() { return courseName; }
 public void setCourseName(String value) { this.courseName=value; }
 public double getProgrammingScore() { return programmingScore; }
 public void setProgrammingScore(double value) { this.programmingScore=value; }
 public double getMathematicsScore() { return mathematicsScore; }
 public void setMathematicsScore(double value) { this.mathematicsScore=value; }
 public List<String> getTechnicalSkills() { return technicalSkills; }
 public void setTechnicalSkills(List<String> value) { this.technicalSkills=value; }
 @JsonProperty(access=JsonProperty.Access.READ_ONLY)
 public double getAverageScore(){return (programmingScore+mathematicsScore)/2;}
 @JsonProperty(access=JsonProperty.Access.READ_ONLY)
 public String getPerformanceCategory(){double a=getAverageScore();return a>=85?"Excellent":a>=70?"Good":a>=50?"Satisfactory":"Needs Improvement";}
 @JsonProperty(access=JsonProperty.Access.READ_ONLY)
 public String getStrongerSubject(){return programmingScore>mathematicsScore?"Programming":mathematicsScore>programmingScore?"Mathematics":"Both Equal";}
 @Override public String toString(){return studentId+": "+studentName;}
}
