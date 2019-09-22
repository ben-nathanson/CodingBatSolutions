// Ben Nathanson
// https://leetcode.com/submissions/detail/263178869/
// Self-critique for next time:
// We can use c - '0' instead of Ctoi(c) to get the int value of a char
// str.trim() removes leading whitespaces from a string
// Boundary values were a big pain point in this challenge.
// There are simpler ways of testing arithmetic overflow that I should
// get more familiar with.
// Despite these shortcomings, my solution passed 1079 test cases and
// was faster than most other Java submissions.
class myAtoi {
    // returns the int equivalent of the given character
    // returns -1 if an unrecognizable character is passed
    public static int Ctoi(char c) {
        if (c == '0') return 0;
        if (c == '1') return 1;
        if (c == '2') return 2;
        if (c == '3') return 3;
        if (c == '4') return 4;
        if (c == '5') return 5;
        if (c == '6') return 6;
        if (c == '7') return 7;
        if (c == '8') return 8;
        if (c == '9') return 9;
        return -1;
    }
    // returns the int interpretation of the given string as
    // specified by the leetcode "myAtoi" problem
    public static int myAtoi(String str) {
        // Case 0: str is empty.
        if (str.length() == 0) return 0;
        // Case 1: str is of length 1.
        if(str.length() == 1 && Character.isDigit(str.charAt(0))) return Ctoi(str.charAt(0));
        int i = 0;
        // This while loop skips over leading whitespace characters.
        while(i < str.length() && str.charAt(i) == ' ') i++;
        // Case 2: str was all whitespace characters
        if (i == str.length()) return 0;
        char first = str.charAt(i);
        // Case 3: str has a letter before any digits
        if(Character.isLetter(first)) return 0;
        // Case 4: str has a character that is not a + or -
        if(!Character.isDigit(first) && (first != '+') && (first != '-')) return 0;
        // set the negative flag and advance past a + or - if needed
        boolean negative = false;
        if (str.charAt(i) == '-') {
            negative = true;
            i++;
        } else if(str.charAt(i) == '+') {
            i++;
        }
        // this loop advances i past any leading 0's
        while ((i < str.length() && (Ctoi(str.charAt(i)) == 0))) i++;
        // Case 5: str ends in a series of 0's
        if(i >= str.length()) return 0;
        // Case 6: str includes a + or - and then a non-digit character
        if(!Character.isDigit(str.charAt(i))) return 0;
        int j = i;// j demarcates where the string of digits ends within str
        while(j < str.length() && Character.isDigit(str.charAt(j))) j++;
        int k = i;
        int m = j - k + 1;
        // Case 8: The string is too long an will cause an arithmetic overflow
        if (m == 11 && negative) {
          int[] max = {2,1,4,7,4,8,3,6,4,8};
          for (int n = 0; n < max.length; n++) {
            int found = Ctoi(str.charAt(i+n));
            int gate = max[n];
            if (found < gate) break;
            if (found > gate) return Integer.MIN_VALUE;
          }
        }
        if (m > 11 && negative) return Integer.MIN_VALUE;
        if (m > 11 && !negative) {
            // Case 8.2: The string is too long and will be positive
            return Integer.MAX_VALUE;
        }
        // Case 9: The string (after the + or -) is of length 1
        if (m == 1) {
            // Case 9.1: Return a negative number
            if (negative) return -1 * Ctoi(str.charAt(k+1));
            // Case 9.2: Return a positive number
            return Ctoi(str.charAt(k));
        }
        int result = 0;
        for (k = k; k < j; k++) {
            int multiplier = 1;
            for (int z = 0; z < m - 2; z++) multiplier *= 10;
            result += multiplier * Ctoi(str.charAt(k));
            m--;
        }
        if (negative) {
            if (result < 0) return Integer.MIN_VALUE;
            result *= -1;
            if (result > 0) return Integer.MIN_VALUE;
            return result;
        } else if (result < 0) {
            return Integer.MAX_VALUE;
        }
        return result;
    }
    public static void test(String input, String expected) {
      System.out.println("Input:    " + input);
      System.out.println("Output:   " + myAtoi(input));
      System.out.println("Expected: " + expected + "\n");
    }
    public static void main(String[] args) {
      test("-6147483648", "-2147483648");
      test("2147483648", "2147483647");
      test("214", "214");
      test("2", "2");
      test("", "0");
      test("2147483647", "2147483647");
      test("-2147483649", "-2147483648");
      test("-2147483650", "-2147483648");
      test("2147483646", "2147483646");
      test("-91283472332", "-2147483648");
      test("-2147483647", "-2147483647");
      test("    -10326662300y", "-2147483648");
    }
}
