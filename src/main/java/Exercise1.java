import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise1 {


    public static void validator(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            char[] buf = new char[256];
            int c;
            while ((c = fileReader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                builder.append(buf);

            }
            String lines = builder.toString();
            Pattern type1 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
            Pattern type2 = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}-\\d{4}");
            Matcher matcher = type1.matcher(lines);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
            matcher = type2.matcher(lines);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}