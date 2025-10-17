public class TempClass { public static void main(String[] args) { import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

class UniversalWindows extends JFrame {

    private JTextArea codeArea;

    public UniversalWindows() {
        setTitle("Universal Java Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        codeArea = new JTextArea();
        add(new JScrollPane(codeArea), BorderLayout.CENTER);

        JPanel taskbar = new JPanel();
        taskbar.setBackground(Color.LIGHT_GRAY);
        add(taskbar, BorderLayout.SOUTH);

        JButton startButton = new JButton("Пуск");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Сохранен код во временный файл
                    String className = "TempClass"; // Имя класса
                    String code = "public class " + className + " { public static void main(String[] args) { " + codeArea.getText() + " } }"; // Обворачиваем кодом класса и методом main
                    File tempFile = new File(className + ".java");
                    FileWriter writer = new FileWriter(tempFile);
                    writer.write(code);
                    writer.close();

                    // 2. Компиляция java-файла
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    compiler.run(null, null, null, tempFile.getPath());

                    // 3. Загрузка класса и запуск метода main (использование Reflection)
                    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File(".").toURI().toURL() }); // текущая директория
                    Class<?> clazz = Class.forName(className, true, classLoader);
                    Method mainMethod = clazz.getMethod("main", String[].class); // Метод main
                    mainMethod.invoke(null, new Object[] { null }); // Вызов метода main, аргументы передаются как null.

                    // 4. Удаление временного файла
                    tempFile.delete();
                    new File(className + ".class").delete();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        taskbar.add(startButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UniversalWindows();
        });
    }
}
 } }