import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Exercise3 {
    public static void wordCounter(String filePath) {
        int BUFFER_SIZE = 1024;
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[BUFFER_SIZE];
            int c;
            while ((c = reader.read(buffer)) > 0) {
                if (c < BUFFER_SIZE) {
                    buffer = Arrays.copyOf(buffer, c);
                }
                builder.append(buffer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        String[] words = builder.toString().split("\\s+");
        int count1 = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null) {
                int count2 = 1;
                for (int j = i + 1; j < words.length; j++) {
                    if (words[i].equals(words[j])) {
                        count2++;
                        words[j] = null;
                    }
                }
                words[i] = words[i].concat(" " + count2);
                count1++;
            }
        }
        String[] newWords = new String[count1];
        count1 = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null) {
                newWords[count1] = words[i];
                count1++;
            }
        }
        toSort(newWords);
        for (String word : newWords) {
            System.out.println(word);
        }
    }

    private static void toSort(String[] words) {
        String buffer;
        String max = "";
        int maxNum = 0;
        for (int i = 0; i < words.length - 1; i++) {
            max = words[i];
            for (int j = i + 1; j < words.length; j++) {
                if (max.charAt(max.length() - 1) < words[j].charAt(words[j].length() - 1)) {
                    max = words[j];
                    maxNum = j;
                }
            }
            if(max.charAt(max.length()-1)>words[i].charAt(words[i].length()-1)) {
                buffer = words[i];
                words[i] = max;
                words[maxNum] = buffer;
            }
        }
    }
}
