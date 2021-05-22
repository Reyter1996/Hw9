import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Exercise2 {


    public static List<User> getUsersFromFile(String filePath) {
        List<User> list = new Stack<>();
        File file = new File(filePath);
        try (FileInputStream fileIO = new FileInputStream(file)) {
            Scanner scanner = new Scanner(fileIO);
            while (scanner.hasNextLine()) {
                User user = getUserFromLine(scanner.nextLine());
                if (user != null) {
                    list.add(user);
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void writeToJSON(String path, List<User> userList) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("file not found!");
            System.out.println("creating...");
            try {
                file.createNewFile();
                System.out.println("file is created");
            } catch (IOException e) {
                System.out.println("file can`t be created");
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("file is OK");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static User getUserFromLine(String line) {
        String[] fields = line.split(" ");
        if (fields[1].equalsIgnoreCase("age")) {
            return null;
        }
        User user = new User(fields[0], Integer.valueOf(fields[1]));
        return user;
    }

    public static List<User> getUsersFromJSONFile(String path) {
        List<User> users = new Stack<>();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("file not found!");
            return null;
        }
        try (FileReader reader = new FileReader(file)) {
            StringBuilder builder = new StringBuilder("");
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                builder.append(buf);
            }
            Type collectionType = new TypeToken<List<User>>() {
            }.getType();
            users = new Gson().fromJson(builder.toString(), collectionType);

            return users;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}