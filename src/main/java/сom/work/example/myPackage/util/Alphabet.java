package сom.work.example.myPackage.util;

import java.util.LinkedList;
import java.util.List;

public class Alphabet {
    public static List<Character> alphabet = new LinkedList<>();

    static {
        for (int i = 97; i < 123; i++) {
            alphabet.add((char) i);
        }
    }
}
