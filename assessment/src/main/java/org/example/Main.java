package org.example;

import org.example.entity.MenuItem;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== Restaurant Management System ====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addMenuItem();
                case 2 -> viewAllItems();
                case 3 -> updatePrice();
                case 4 -> deleteItem();
                case 5 -> {
                    HibernateUtil.getSessionFactory().close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addMenuItem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Available (true/false): ");
        boolean available = sc.nextBoolean();

        MenuItem item = new MenuItem(name, price, category, available);
        session.save(item);

        tx.commit();
        session.close();

        System.out.println("Menu Item Added Successfully!");
    }

    private static void viewAllItems() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<MenuItem> query = session.createQuery("FROM MenuItem", MenuItem.class);
        List<MenuItem> list = query.list();

        for (MenuItem item : list) {
            System.out.println(item);
        }

        session.close();
    }

    private static void updatePrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {
            item.setPrice(price);
            session.update(item);
            tx.commit();
            System.out.println("Price Updated!");
        } else {
            System.out.println("Item Not Found!");
        }

        session.close();
    }

    private static void deleteItem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {
            session.delete(item);
            tx.commit();
            System.out.println("Item Deleted!");
        } else {
            System.out.println("Item Not Found!");
        }

        session.close();
    }
}
