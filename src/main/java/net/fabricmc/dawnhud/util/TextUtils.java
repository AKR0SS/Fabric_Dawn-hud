package net.fabricmc.dawnhud.util;

public class TextUtils {
    String _string = "";

    public String toCapital(String nString) {
        String wordArray[] = nString.split("\\s");

        for(String w:wordArray){
            String first = w.substring(0,1);
            String afterfirst = w.substring(1);
            _string += first.toUpperCase() + afterfirst + " ";
        }
        return _string.trim();
    }
}
