package com.viettay.cruddemo;

import com.viettay.cruddemo.dao.AppDAO;
import com.viettay.cruddemo.entity.*;
import org.hibernate.tool.schema.SourceType;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
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

	// Executed after the Spring Beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

			// createCourseAndStudents(appDAO);

			// findCourseAndStudents(appDAO);

			// findStudentAndCourses(appDAO);

			// addMoreCoursesForStudent(appDAO);

			// deleteCourse(appDAO);

			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting student id: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more course
		Course tempCourse1 = new Course("Music");
		Course tempCourse2 = new Course("English");

		// add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Saving student: " + tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;

		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("Math");

		// create the students
		Student tempStudent1 = new Student("tay", "viet", "tayviet@gmail.com");
		Student tempStudent2 = new Student("thanh", "tan", "thanhtan@gmail.com");
		// add students to the course

		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 11;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 11;
		Course temCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(temCourse);

		// print the reviews
		System.out.println(temCourse.getReviews());

		System.out.println("Done!");

	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("Math");

		// add some review
		tempCourse.addReview(new Review("Great course..."));
		tempCourse.addReview(new Review("Cool course..."));
		tempCourse.addReview(new Review("Bad course..."));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Update course id: " + theId);
		tempCourse.setTitle("Music");

		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("Vuong");

		appDAO.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorrWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 1;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated course: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Tay", "Viet", "tayviet@gmail.com");

		// create the instructor_detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Music");

		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some sources
		Course tempCourse1 = new Course("English");
		Course tempCourse2 = new Course("Math");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//
		// NOTE: this will ALSO save the courses
		// because of CascadedType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail =appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");


	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Tay", "Viet", "tayviet@gmail.com");

		// create the instructor_detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.viettay.com/youtube", "Viet Tay");

		// associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

}
