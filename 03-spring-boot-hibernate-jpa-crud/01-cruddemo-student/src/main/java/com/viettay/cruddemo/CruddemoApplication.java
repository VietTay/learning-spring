package com.viettay.cruddemo;

import com.viettay.cruddemo.dao.StudenDAO;
import com.viettay.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudenDAO  studenDAO) {
        return runner -> {
            // createStudent(studenDAO);

            createMultipleStudents(studenDAO);

            // readStudent(studenDAO);

            // queryForStudents(studenDAO);
          
            // queryForStudentsByLastName(studenDAO);

            // updateStudent(studenDAO);

            // deleteStudent(studenDAO);

            // deleteAllStudents(studenDAO);

        };
    }

    private void deleteAllStudents(StudenDAO studenDAO) {

        System.out.println("Deleting all students");
        int rumRowsDeleted = studenDAO.deleteAll();
        System.out.println("Deleted row count: " + rumRowsDeleted);
    }

    private void deleteStudent(StudenDAO studenDAO) {
        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studenDAO.delete(studentId);
    }

    private void updateStudent(StudenDAO studenDAO) {

        // retrieve student based on the id: primary key
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studenDAO.findById(studentId);

        // change first name to "Vuong"
        System.out.println("Updating student ...");
        myStudent.setFirstName("Vuong");

        // update the student
        studenDAO.update(myStudent);

        // display the updated student
        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByLastName(StudenDAO studenDAO) {

        // get a list of students
        List<Student> theStudents = studenDAO.findByLastName("Thanh");

        // display list of students
        for (Student tempStudent: theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudenDAO studenDAO) {

        // get a list of students
        List<Student> theStudents = studenDAO.findAll();

        // display list of students
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudenDAO studenDAO) {

        // create  a student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Hai","Trieu", "haitrieu@gmail.com");

        // save  the student
        System.out.println("Saving the student ...");
        studenDAO.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generate id: " + theId);

        // retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studenDAO.findById(theId);

        // display student
        System.out.println("Found the student:  " + myStudent);
    }

    private void createMultipleStudents(StudenDAO studenDAO) {
        // create multiple students
        System.out.println("Creating 3 students object ...");
        Student tempStudent1 = new Student("Viet","Tay", "viettay@gmail.com");
        Student tempStudent2 = new Student("Tan","Thanh", "tanthanh@gmail.com");
        Student tempStudent3 = new Student("Tuan","Kiet", "tuankiet@gmail.com");

        // save the students objects
        System.out.println("Saving the students ...");
        studenDAO.save(tempStudent1);
        studenDAO.save(tempStudent2);
        studenDAO.save(tempStudent3);
    }

    private void createStudent(StudenDAO studenDAO) {

        // create the student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Viet","Tay", "viettay@gmail.com");

        // save the student object
        System.out.println("Saving the student ...");
        studenDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }


}
