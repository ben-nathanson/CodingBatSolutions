// CodingBat problem: https://codingbat.com/prob/p154137

// Given a string, return a string where every appearance of the lowercase word
// "is" has been replaced with "is not". The word "is" should not be
// immediately preceeded or followed by a letter -- so for example the "is" in
// "this" does not count. (Note: Character.isLetter(char) tests if a char is a
// letter.) notReplace("is test") → "is not test"
// notReplace("is-is") → "is not-is not"
// notReplace("This is right") → "This is not right"

// Test this from the command line with 'javac notReplace.java; java -ea notReplace;'

class notReplace {
    public static String solve(String base) {
        StringBuilder result = new StringBuilder("");
        int index = 0;
        int lastIndex = base.length() - 1;
        while (index <= lastIndex) {
            char token = base.charAt(index);
            if (token == 'i') {
                // Check right index boundary and whether next character is 's'
                if (lastIndex - index >= 1 && (base.charAt(index + 1) == 's')) {
                    // At this point we know that the pattern 'is' has appeared
                    // Check the character before 'is':
                    if (index > 0 && Character.isLetter(base.charAt(index - 1))) {
                        // there was a character before 'i':
                        result.append(token);
                    } else if (lastIndex - index >= 2 && (Character.isLetter(base.charAt(index + 2)))) {
                        // there was a character after 's':
                        result.append(token);
                    } else {
                        result.append("is not");
                        index++; // Increment an extra time to ensure we skip 'i' and 's'
                    }
                } else {
                    // The 'i' was at the end of str or the next character was not 's'
                    result.append(token);
                }
            } else {
                // The character at index was not 'i'
                result.append(token);
            }
            index++;
        }
        return result.toString();
    }

    public static void test(String challenge, String solution) {
        System.out.println("Challenge: \t" + challenge);
        String result = notReplace.solve(challenge);
        System.out.println("Result: \t" + result);
        System.out.println("Solution: \t" + solution + "\n");
        assert(solution.equals(result));
    }

    public static void main(String[] args) {
        test("is test", "is not test");
        test("is-is", "is not-is not");
        test("This is right", "This is not right");
        test("This is isabell", "This is not isabell");
        test("","");
        test("is", "is not");
        test("isis", "isis");
        test("Dis is bliss is", "Dis is not bliss is not");
        test("is his", "is not his");
        test("xis yis", "xis yis");
        test("AAAis is", "AAAis is not");

    }
}
