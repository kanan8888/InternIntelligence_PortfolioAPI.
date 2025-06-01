package az.portfolioapi.util;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL = UPPER + LOWER + DIGITS;

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4");
        }

        StringBuilder password = new StringBuilder(length);

        // Hər tipdən ən azı 1 simvol əlavə edirik
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        // Qalan simvolları qarışıq olaraq əlavə edirik
        for (int i = 3; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        // Simvolların yerini qarışdırmaq üçün array-a çeviririk
        char[] pwdArray = password.toString().toCharArray();

        for (int i = pwdArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // swap
            char temp = pwdArray[i];
            pwdArray[i] = pwdArray[j];
            pwdArray[j] = temp;
        }

        return new String(pwdArray);
    }
}
