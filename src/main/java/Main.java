import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH1 = "src\\main\\resources\\file.txt";
        final String FILE_PATH2 = "src\\main\\resources\\file2.txt";
        final String PATH_TO_JSON = "src\\main\\resources\\user.json";
        final String FILE_PATH3 = "src\\main\\resources\\words.txt";
        {
            System.out.println("***************\ntesting validator" +
                    "& scanner):");

            Exercise1.validator(FILE_PATH1);
            System.out.println("finishing testing validator\n***************");
        }
        {
            System.out.println("testing exercise 2");
            System.out.println("reading users from file...");
            List<User> users = Exercise2.getUsersFromFile(FILE_PATH2);
            System.out.println("reading ok");
            System.out.println("writing to JSON file");
            Exercise2.writeToJSON(PATH_TO_JSON, users);
            System.out.println("writing is OK");
            System.out.println("trying deserialised person from JSON");
            users = Exercise2.getUsersFromJSONFile(PATH_TO_JSON);
            for (User us : users) {
                System.out.println(us);
            }
            System.out.println("finishing testing exercise 2\n***************");
        }
        {
            System.out.println("testing exercise 3");
            Exercise3.wordCounter(FILE_PATH3);
            System.out.println("testing complete!\n***************");
        }
    }
}