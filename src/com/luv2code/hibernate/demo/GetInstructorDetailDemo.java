package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
											
		//create session
		Session session = factory.getCurrentSession();
		
		try {

			//Start a transaction
			session.beginTransaction();

			//get the instructor detail object
			int instructorId=3;
			InstructorDetail tempInstructorDetail =
					session.get(InstructorDetail.class, instructorId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
