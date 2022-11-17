package edu.uncc.inclass12;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grades")
public class Grade {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String course_grade;

    @ColumnInfo
    public String course_number;

    @ColumnInfo
    public String course_name;

    @ColumnInfo
    public String credit_hours;

    public Grade(long id, String course_grade, String course_number, String course_name, String credit_hours) {
        this.id = id;
        this.course_grade = course_grade;
        this.course_number = course_number;
        this.course_name = course_name;
        this.credit_hours = credit_hours;
    }

    public Grade( String course_grade, String course_number, String course_name, String credit_hours) {
        this.course_grade = course_grade;
        this.course_number = course_number;
        this.course_name = course_name;
        this.credit_hours = credit_hours;
    }

    public Grade() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse_grade() {
        return course_grade;
    }

    public void setCourse_grade(String course_grade) {
        this.course_grade = course_grade;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(String credit_hours) {
        this.credit_hours = credit_hours;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", course_grade='" + course_grade + '\'' +
                ", course_number='" + course_number + '\'' +
                ", course_name='" + course_name + '\'' +
                ", credit_hours='" + credit_hours + '\'' +
                '}';
    }
}
