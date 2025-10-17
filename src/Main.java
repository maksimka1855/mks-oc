import java.io.*;
import java.util.*;

class MiniOS {

    private static String language = "en";

    private static String getTranslation(String text) {
        if (language.equals("en")) {
            switch (text) {
                case "Добро пожаловать в МКС OS!": return "Welcome to MKS OS!";
                case "МКС OS": return "MKS OS";
                case "Панель Пуск": return "Start Menu";
                case "Консоль": return "Console";
                case "Программа скачивания приложений (бета версия OS)": return "App Downloader (OS Beta)";
                case "Управление файлами": return "File Management";
                case "Открыть браузер": return "Open Browser";
                case "Системная информация": return "System Info";
                case "Сменить тему": return "Toggle Theme";
                case "dir/s (рекурсивный просмотр директории)": return "dir/s (recursive directory view)";
                case "Выход": return "Exit";
                case "Выберите действие: ": return "Choose an action: ";
                case "Введите путь к директории: ": return "Enter directory path: ";
                case "завершение работы...": return "Shutting down...";
                case "Неверный выбор. Пожалуйста, выберите действие из списка.": return "Invalid choice. Please select an action from the list.";
                case "Доступные команды:": return "Available commands:";
                case "Отображает список команд": return "Displays list of commands";
                case "Очищает экран": return "Clears the screen";
                case "Выводит текст на экран": return "Prints text to the screen";
                case "Выводит список файлов в директории": return "Lists files in directory";
                case "Отображает версию ОС": return "Show OS version";
                case "Текущая дата": return "Current date";
                case "Текущее время": return "Current time";
                case "Список задач": return "List of tasks";
                case "Выход из консоли": return "Exit the console";

                // Новые переводы для элементов, которых нет в оригинальном коде
                case "Управление процессами": return "Process Management";
                case "Управление памятью": return "Memory Management";
                case "Файловая система": return "File System";
                case "Пользовательский интерфейс": return "User Interface";
                case "Безопасность": return "Security";
                default: return text;
            }
        }
        return text;
    }

    // добавлены пункты в меню консоли
    private static void showConsoleCommands() {
        System.out.println(getTranslation("Доступные команды:"));
        System.out.println("help - " + getTranslation("Отображает список команд"));
        System.out.println("exit - " + getTranslation("Выход из консоли"));
        System.out.println("dir - " + getTranslation("Выводит список файлов в директории"));
        System.out.println("tasklist - " + getTranslation("Список задач"));
    }

    private static boolean runConsole(Scanner scanner) {
        boolean consoleRunning = true;
        while (consoleRunning) {
            System.out.print("MKS OS> ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                consoleRunning = false;
            } else if (command.equals("help")) {
                showConsoleCommands();
                // заглушки для вывода в консоль
            } else if (command.equals("tasklist")) {
                System.out.println(getTranslation("Список задач: (заглушка)"));
            }  else {
                System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            }
        }
        return true;
    }
    // добавил заглушки для упрощения при отсуствии кода в оригинальном коде
    private static void processManagement() {
        System.out.println(getTranslation("Управление процессами: (заглушка)"));
    }

    private static void memoryManagement() {
        System.out.println(getTranslation("Управление памятью: (заглушка)"));
    }

    private static void fileSystem() {
        System.out.println(getTranslation("Файловая система: (заглушка)"));
    }

    private static void userInterface() {
        System.out.println(getTranslation("Пользовательский интерфейс: (заглушка)"));
    }

    private static void security() {
        System.out.println(getTranslation("Безопасность: (заглушка)"));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(getTranslation("Добро пожаловать в МКС OS!"));

        while (running) {
            System.out.println("\n" + getTranslation("МКС OS"));
            // добавил пункты
            System.out.println("1. " + getTranslation("Панель Пуск"));
            System.out.println("2. " + getTranslation("Консоль"));
            System.out.println("3. " +getTranslation("Управление процессами"));
            System.out.println("4. " + getTranslation("Управление памятью"));
            System.out.println("5. " + getTranslation("Файловая система"));
            System.out.println("6. " + getTranslation("Пользовательский интерфейс"));
            System.out.println("7. " + getTranslation("Безопасность"));
            System.out.println("9. " + getTranslation("Сменить язык/Change language"));
            System.out.println("0. " + getTranslation("Выход"));

            System.out.print(getTranslation("Выберите действие: "));

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 9:
                        System.out.println("Выберите язык (ru/en):");
                        String lang = scanner.nextLine();
                        if (lang.equals("ru") || lang.equals("en")) {
                            language = lang;
                        } else {
                            System.out.println("Недопустимый язык.");
                        }
                        break;
                    case 2:
                        System.out.println(getTranslation("Консоль: (заглушка)"));
                        running = runConsole(scanner);
                        break;// добавил кейсы в switch
                    case 3:
                        processManagement();
                        break;
                    case 4:
                        memoryManagement();
                        break;
                    case 5:
                        fileSystem();
                        break;
                    case 6:
                        userInterface();
                        break;
                    case 7:
                        security();
                        break;
                    case 0:
                        System.out.println(getTranslation("завершение работы..."));
                        running = false;
                        break;
                    default:
                        System.out.println(getTranslation("Неверный выбор. Пожалуйста, выберите действие из списка."));
                }
            } catch (NumberFormatException e) {
                System.out.println(getTranslation("Неверный выбор. Пожалуйста, выберите действие из списка."));

            }
        }
        scanner.close();
    }
}
