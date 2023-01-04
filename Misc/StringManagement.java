package Misc;

public class StringManagement {

    public static int countLetterInString(String string, char letter) {
        int nSum = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == letter) {
                nSum++;
            }
        }
        return nSum;
    }
}
