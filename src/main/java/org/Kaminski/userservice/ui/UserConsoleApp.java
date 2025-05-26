package org.Kaminski.userservice.ui;

import org.Kaminski.userservice.dao.*;
import org.Kaminski.userservice.entity.User;

import java.util.List;
import java.util.Scanner;

public class UserConsoleApp {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("\nМЕНЮ ПОЛЬЗОВАТЕЛЯ");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Получить пользователя по ID");
            System.out.println("3. Список пользователей");
            System.out.println("4. Обновить пользователя");
            System.out.println("5. Удалить пользователя");
            System.out.println("0. Выйти");
            System.out.print("Ваш выбор ?: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> createUser();
                case 2 -> getUser();
                case 3 -> listUsers();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 0 -> System.exit(0);
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void createUser() {
        System.out.print("Имя: ");
        String name = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Возраст: ");
        int age = scanner.nextInt();

        userDAO.createUser(new User(name, email, age));
        System.out.println("Пользователь создан.");
    }

    private static void getUser() {
        System.out.print("ID пользователя: ");
        Long id = scanner.nextLong();
        User user = userDAO.getUser(id);
        System.out.println(user != null ? user : "Пользователь не найден");
    }

    private static void listUsers() {
        List<User> users = userDAO.getAllUsers();
        users.forEach(System.out::println);
    }

    private static void updateUser() {
        System.out.print("ID пользователя: ");
        Long id = scanner.nextLong();
        User user = userDAO.getUser(id);
        if (user == null) {
            System.out.println("Пользователь не найден");
            return;
        }

        System.out.print("Новое имя: ");
        user.setName(scanner.next());
        System.out.print("Новый email: ");
        user.setEmail(scanner.next());
        System.out.print("Новый Возраст: ");
        user.setAge(scanner.nextInt());

        userDAO.updateUser(user);
        System.out.println("Пользователь обновлён.");
    }

    private static void deleteUser() {
        System.out.print("ID пользователя: ");
        Long id = scanner.nextLong();
        userDAO.deleteUser(id);
        System.out.println("Пользователь удалён.");
    }
}

