package com.omni.mongotest.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * @author Mr.Pei
 * @date 2023/11/16 14:44
 **/
@UtilityClass
public class CodeUtils {

    private static final String BASE_36_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String convertToBase36(long number) {
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            int remainder = (int) (number % 36);
            sb.insert(0, BASE_36_CHARS.charAt(remainder));
            number /= 36;
        }

        // Pad with zeros to make it 5 characters long
        while (sb.length() < 5) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }


    public long convertToBase10(String base36String) {
        long result = 0;
        long base = 1;
        for (int i = base36String.length() - 1; i >= 0; i--) {
            char c = base36String.charAt(i);
            int digit = BASE_36_CHARS.indexOf(c);
            result += digit * base;
            base *= 36;
        }
        return result;
    }

    public String generateRandomChinese(int count) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // Randomly select a Unicode code point for Chinese characters
            int codePoint = '\u4E00' + random.nextInt('\u9FFF' - '\u4E00' + 1);
            sb.appendCodePoint(codePoint);
        }

        return sb.toString();
    }

}
