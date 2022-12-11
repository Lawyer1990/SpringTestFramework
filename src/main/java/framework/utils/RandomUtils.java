package framework.utils;

import framework.enums.CharacterSet;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

import static framework.enums.CharacterSet.ENGLISH_ALPHABET;
import static framework.enums.CharacterSet.NUMERIC;


public final class RandomUtils {

    private static final Random random = new Random();

    private static final String AUTOTESTS_SMALL_PREFIX = "t";
    public static final String AUTOTESTS_PREFIX = "adt";

    private static final int DEFAULT_MIN_DATE_OFFSET = 0;

    private static final int DEFAULT_MAX_DATE_OFFSET = 100;

    private RandomUtils() {
        //RandomUtils class should not be instantiated
    }

    /**
     * Generate random string string.
     *
     * @param charactersCount the characters count
     * @param charactersSet   the characters set
     * @return the string
     */
    private static String generateRandomString(long charactersCount, CharacterSet charactersSet) {
        if (charactersCount < 0) {
            throw new IllegalArgumentException("Characters count must be greater then 0");
        }
        if (StringUtils.isEmpty(charactersSet.getCharacters())) {
            throw new IllegalArgumentException("Characters set must be defined and contain at least one character");
        }
        List chars = Arrays.asList(charactersSet.getCharacters().split(""));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charactersCount; i++) {
            stringBuilder.append(getRandomListElement(chars));
        }
        return stringBuilder.toString();
    }

    /**
     * @param prefix          - prefix for string
     * @param charactersCount the characters count
     * @param charactersSet   characters set
     * @return random string with prefix
     */
    public static String generateRandomStringWithPrefix(String prefix, long charactersCount, CharacterSet charactersSet) {
        return new StringBuilder()
                .append(prefix)
                .append(generateRandomString(Long.sum(-prefix.length(), charactersCount), charactersSet))
                .toString();
    }

    /**
     * Gets random string.
     *
     * @param charCount the characters count
     * @return the random string
     */
    public static String getRandomString(long charCount, CharacterSet charactersSet, boolean usePrefix) {

        if ((charCount > AUTOTESTS_PREFIX.length()) && usePrefix) {
            return generateRandomStringWithPrefix(AUTOTESTS_PREFIX, charCount, charactersSet);
        } else if ((charCount > AUTOTESTS_SMALL_PREFIX.length()) && usePrefix) {
            return generateRandomStringWithPrefix(AUTOTESTS_SMALL_PREFIX, charCount, charactersSet);
        } else {
            return generateRandomString(charCount, charactersSet);
        }
    }

    /**
     * Gets random list element.
     *
     * @param <T>            the type parameter
     * @param listCollection the list collection
     * @return the random list element
     */
    public static <T> T getRandomListElement(List<T> listCollection) {
        if (listCollection == null || listCollection.isEmpty()) {
            throw new IllegalArgumentException("Source collection must be defined and contain at least one element");
        }
        int randomIndex = random.nextInt(listCollection.size());
        return listCollection.get(randomIndex);
    }

    /**
     * Gets random int value in range (with min and max inclusion).
     *
     * @param min the min
     * @param max the max
     * @return random int between
     */
    public static int getRandomIntBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(String.format(
                    "Min value [%d] value cannot be greater than Max value [%d]", min, max));
        }
        return random.nextInt(max + 1 - min) + min;
    }

    public static LocalDate getRandomDateLaterThan(LocalDate date) {
        return date.plusDays(getRandomIntBetween(DEFAULT_MIN_DATE_OFFSET + 1, DEFAULT_MAX_DATE_OFFSET));
    }

    public static LocalDate getRandomDateLaterThan(String date) {
        return LocalDate.parse(date).plusDays(getRandomIntBetween(DEFAULT_MIN_DATE_OFFSET + 1, DEFAULT_MAX_DATE_OFFSET));
    }

    /**
     * Gets random date in the past.
     *
     * @return the random date in the past
     */
    public static LocalDate getRandomDateInPast() {
        return LocalDate.now().minusDays(getRandomIntBetween(DEFAULT_MIN_DATE_OFFSET, DEFAULT_MAX_DATE_OFFSET));
    }

    public static LocalDate getRandomDateInPastFromCurrentDay(String currentDay) {
        int count = LocalDate.parse(currentDay).compareTo(LocalDate.now());
        return LocalDate.parse(currentDay).minusDays(getRandomIntBetween(DEFAULT_MIN_DATE_OFFSET, count));
    }

    public static LocalDate getRandomDateInPast(int yearsAgoFrom, int yearsAgoUntil) {
        return LocalDate.now().minusDays(getRandomIntBetween(yearsAgoFrom * 365, yearsAgoUntil * 365));
    }

    public static int[] getRandomIntArray(int min, int max, int amount) {
        if (min > max || (amount > (max - min))) {
            throw new IllegalArgumentException(String.format(
                    "Min value [%d] value cannot be greater than Max value [%d]", min, max));
        }
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] array = new int[amount];
        for (int i = 0; i < amount; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String getRandomHex() {
        return Integer.toHexString(getRandomIntBetween(0, 65535));
    }

    public static String getRandomColor() {
        String hex = getRandomHex();
        for (int i = hex.length(); i < 6; i++) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String createText(int wordsNumber) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < wordsNumber; i++) {
            text.append(createName()).append(StringUtils.SPACE);
        }
        return text.toString();
    }

    public static String createName() {
        return RandomUtils.getRandomString(8, ENGLISH_ALPHABET, true);
    }

    public static String createUserName() {
        return RandomUtils.getRandomString(8, ENGLISH_ALPHABET, true).toLowerCase();
    }


    public static String createName(String prefix) {
        return prefix + RandomUtils.getRandomString(7, ENGLISH_ALPHABET, false);
    }

    public static String createId() {
        return RandomUtils.getRandomString(6, NUMERIC, false);
    }

    public static boolean getRandomBoolean() {
        return random.nextInt(2) == 0;
    }

    public static String createEmail() {
        return createName() + "@" + createName() + ".com";
    }

    public static String createPhoneNumber() {
        return "+" + RandomUtils.getRandomString(15, NUMERIC, false);
    }

    public static String getUserName() {
        return createName().toLowerCase();
    }

    public static String createName(int n) {
        return RandomUtils.getRandomString(n, ENGLISH_ALPHABET, false);
    }

    public static String createLongName() {
        return RandomUtils.getRandomString(12, ENGLISH_ALPHABET, true);
    }

    public static String createLanguage() {
        return RandomUtils.getRandomString(2, ENGLISH_ALPHABET, false).toLowerCase(Locale.ROOT) + "-" + RandomUtils.getRandomString(2, ENGLISH_ALPHABET, false).toUpperCase(Locale.ROOT);
    }

    public static List<String> getRandomListOfString(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(createName());
        }
        return list;
    }
}
