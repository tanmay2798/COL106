//package gupta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decompress {
    public static List<String> decompress(List<Integer> compressed) {
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, "" + (char)i);
        }

        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer uncompressed = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k)) {
                entry = dictionary.get(k);
            } else if (k == dictSize) {
                entry = w + w.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed k: " + k);
            }

            dictionary.put(dictSize++, w + entry.charAt(0));
            uncompressed.append(entry);
            w = entry;
        }

        return Arrays.asList(uncompressed.toString().split("\n"));
    }

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            return;
        }

        List<Integer> compressed = new ArrayList<Integer>();
        for (String l : lines) {
            compressed.add(Integer.valueOf(l));
        }

        try {
            Files.write(Paths.get(args[1]), decompress(compressed));
        } catch (IOException e) {
            return;
        }
    }
}