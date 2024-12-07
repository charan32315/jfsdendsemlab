package com.klu.opproject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert records
        Transaction transaction = session.beginTransaction();
        Project project1 = new Project();
        project1.setProjectName("AI Development");
        project1.setDuration(12);
        project1.setBudget(50000);
        project1.setTeamLead("Alice");

        Project project2 = new Project();
        project2.setProjectName("Web App");
        project2.setDuration(8);
        project2.setBudget(30000);
        project2.setTeamLead("Bob");

        session.save(project1);
        session.save(project2);
        transaction.commit();

        // Perform aggregate functions on Budget
        Criteria criteria = session.createCriteria(Project.class);

        // Count
        criteria.setProjection(Projections.rowCount());
        System.out.println("Total Projects: " + criteria.uniqueResult());

        // Max
        criteria.setProjection(Projections.max("budget"));
        System.out.println("Max Budget: " + criteria.uniqueResult());

        // Min
        criteria.setProjection(Projections.min("budget"));
        System.out.println("Min Budget: " + criteria.uniqueResult());

        // Sum
        criteria.setProjection(Projections.sum("budget"));
        System.out.println("Sum of Budgets: " + criteria.uniqueResult());

        // Average
        criteria.setProjection(Projections.avg("budget"));
        System.out.println("Average Budget: " + criteria.uniqueResult());

        session.close();
        sessionFactory.close();
    }
}

