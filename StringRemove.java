public class StringRemove {
  // returns a string with all instances of remove absent from input
  public static String stringRemove(String input, String remove) {
    String outcome = "";
    if (input == null || remove == null) return outcome;
    if (input.length() == 0 || input.length() < remove.length() || remove.length() == 0) return outcome;
    if (input.indexOf(remove) == -1) return input;
    // two strings, of some finite length greater than 0, with remove <= input string in size
    int left = 0;
    int right = input.indexOf(remove);
    do {
      outcome += input.substring(left, right);
      left = right + remove.length();
      right = input.substring(left).indexOf(remove) + left;
      if (input.substring(left).indexOf(remove) == -1) {
        outcome += input.substring(left);
        break;
      }
    } while (left < input.length());
    while(outcome.contains(remove)) outcome = stringRemove(outcome, remove);
    return outcome;
  }

  // tests our stringRemove method
  public static void test(String input, String remove) {
    System.out.println("Remove \"" + remove + "\" from \"" + input + "\" ---> \"" + stringRemove(input,remove) + "\"");
  }

  public static void main(String[] args) {
    // tests
    System.out.println("Tests:");
    test("catdogcat", "cat"); // dog
    test("abc", "bc");
    test("a:bc", "bc");
    test("ab:c", "bc");
    test("tomato soup", "potato soup");
    test("cacatt", "cat"); // ""
    test("cat", "cat"); // ""
    test(null, ""); // ""
    test(null, null); // ""
  }

}
