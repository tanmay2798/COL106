package gupta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compress {
    public static List<Integer> compress(List<String> uncompressed) {
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put("" + (char)i, i);
        }

        String w = "";
        List<Integer> compressed = new ArrayList<Integer>();
        for (String l : uncompressed) {
            l = l + System.lineSeparator();
            for (char c : l.toCharArray()) {
                String wc = w + c;
                if (dictionary.containsKey(wc)) {
                    w = wc;
                } else {
                    compressed.add(dictionary.get(w));
                    dictionary.put(wc, dictSize++);
                    w = "" + c;
                }
            }
        }
        if (!w.equals("")) {
            compressed.add(dictionary.get(w));
        }

        return compressed;
    }

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/ass4/new_medium.txt"));
        } catch (IOException e) {
            return;
        }

        List<String> compressed = new ArrayList<String>();
        for (int k : compress(lines)) {
            compressed.add(String.valueOf(k));
        }

        try {
            Files.write(Paths.get("/Users/tanmaygoyal/eclipse-workspace/tanmay/src/ass4/gupta.txt"), compressed);
        } catch (IOException e) {
            return;
        }
    }
}