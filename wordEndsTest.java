// Ben Nathanson
// based on on a problem by Nick Parlante
// Codingbat tests answers with a two sets of inputs and outputs, the first set
// being visible to the user, and the second set being visible only to the
// server. As it happens my method passed all of the test in the first set, but
// failed somewhere in the second set! So, I decided to write my own test
// methods and find the bug. The idea in this challenge was that you have a
// needle string, and a haystack string, and you want to return all of the
// letters before and after each occurrence of the needle in the haystack.

public class wordEndsTest {
  static boolean TEST_FLAG = true;
  private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz"
                                      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  // return count
  public static String getRandomLetters(int n) {
    StringBuilder output = new StringBuilder();
    int lettersLength = LETTERS.length();
    for(int i = 0; i < n; i++) {
      output.append(LETTERS.charAt((int)(Math.random() * lettersLength)));
    }
    return output.toString();
  }

  // Pass random inputs to the wordEnds method to recreate the failure
  public static void fuzzWordEnds(int numberOfTests, int maxStringLength,
                                  boolean verbose) {
    int errorCount = 0;
    for (int i = 0; i < numberOfTests; i++) {
      int textLength = (int)(Math.random() * maxStringLength);
      String text = getRandomLetters(textLength);
      boolean coinFlip = (Math.random() * 2) > 1;
      String needle = "";
      int needleLength = needle.length();
      if (coinFlip) {
        // generate a random string of letters
        needleLength = (int)(Math.random() * 3) + 1;
        needle = getRandomLetters(needleLength);
      } else {
        // pluck a random substring from the haystack to form the needle
        int start = (int)(Math.random() * text.length());
        int end = start + (int)(Math.random() * 5);
        if (end > text.length()) {
          end = (int)(Math.random() * (text.length() - start)) + start;
        }
        needle = text.substring(start, end);
        needleLength = needle.length();
      }
      try {
        String result = wordEnds(text, needle);
        if (verbose) System.out.println("Text:   " + text +
                                        "\nNeedle: " + needle +
                                        "\nResult: " + result);
      } catch (StringIndexOutOfBoundsException e) {
        errorCount++;
        System.out.println(e);
        e.printStackTrace(System.out);
        System.out.println("Text:   " + text);
        System.out.println("Needle: " + needle);
        System.out.println("Error count: " + String.valueOf(errorCount));
      }
    }
  }

  public static String wordEnds(String text, String needle) {
    int textLength = text.length();
    int needleLength = needle.length();
    int index = text.indexOf(needle);
    // Special case 1:
    // If the needle does not occur in the text:
    if (index < 0) return "";
    // Special case 2:
    // The text is the needle. Thus there cannot be neighboring letters
    if (text.equals(needle)) return "";
    // Common case: The needle is somewhere in text with neigboring letters:
    String output = "";
    // get the first left neighbor of the first instance of the needle
    // there is no neighbor at the index -1 so we skip if index <= 0
    if (index > 0) output += text.charAt(index - 1);
    // get the first right neighbor of the first instance of the needle
    int rightNeighbor = index + needleLength;
    if ((rightNeighbor < textLength) && (rightNeighbor > -1)) {
      output += text.charAt(index + needleLength);
    } else return output;
    index = text.indexOf(needle, index + 1);
    if (index < 0) return output;
    do {
      output += text.charAt(index - 1); // left neighbor
      rightNeighbor = index + needleLength;
      if ((rightNeighbor < textLength)  && (rightNeighbor > -1)) output += text.charAt(rightNeighbor);
      index = text.indexOf(needle, index + 1);
    } while(index < textLength && index > - 1);
    return output;
  }

  // Tests that given text and a needle, wordEnds returns the expectedResult
  // I originally used assert, but wanted more detail.
  public static void testWordEnds(String text, String needle,
                                  String expectedResult, boolean verbose) {
    String actualResult = wordEnds(text, needle);
    if (actualResult.equals(expectedResult)) {
      if (verbose) {
        System.out.println("Expect: " + "\"" + expectedResult + "\"\n" +
                           "Result: \"" + actualResult + "\"\n");
      }
    } else {
      System.out.println(expectedResult + "!=" + actualResult);
      TEST_FLAG = false;
    }
  }

  public static void main(String[] args) {
    // test whether the method returns the correct outputs:
    boolean moreInformation = true;
    testWordEnds("abcXY123XYijk", "XY", "c13i", moreInformation);
    testWordEnds("XY123XY", "XY", "13", moreInformation);;
    testWordEnds("XY1XY", "XY", "11", moreInformation);
    testWordEnds("XYXY", "XY", "XY", moreInformation);
    testWordEnds("XY", "XY", "", moreInformation);
    testWordEnds("Hi", "XY", "", moreInformation);
    testWordEnds("", "XY", "", moreInformation);
    testWordEnds("abc1xyz1i1j", "1", "cxziij", moreInformation);
    testWordEnds("abc1xyz1", "1", "cxz", moreInformation);
    testWordEnds("abc1xyz11", "1", "cxz11", moreInformation);
    testWordEnds("abc1xyz1abc", "abc", "11", moreInformation);
    testWordEnds("abc1xyz1abc", "b", "acac", moreInformation);
    testWordEnds("abc1abc1abc", "abc", "1111", moreInformation);
    testWordEnds("abc1abc1abc1234234234234234", "abc", "11111", moreInformation);
    if(TEST_FLAG) System.out.println("Test 1 successful.");
    if(!TEST_FLAG) System.out.println("Test 1 failed.");

    // test whether the method crashes on junk inputs:
    int numberOfTests = 5;
    int lengthOfHaystack = 100;
    boolean verbose = true;
    fuzzWordEnds(numberOfTests, lengthOfHaystack, verbose);
    System.out.println("Test 2 successful.");
  }
}
