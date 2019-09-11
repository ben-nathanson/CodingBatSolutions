/*  Ben Nathanson
    9.11.2019
    Implementation of a bracket balance checker using a stack data structure.
    Improvements that I could add here:
    - Use a primitive char instead of a String object. Tradeoff being that the stock
      util.Stack would need to be replaced, while on the other hand, characters use
      less memory and are likely going to be faster to operate on, i.e. comparing two
      characters.
    - Clearer naming of variables e.g. "String character" could be confusing for others
*/

import java.util.Stack;

public class balancer {

  public static boolean isBalanced(String input) {
    Stack<String> stack = new Stack<>();

    // address special cases
    if (input == null) return false;
    if (input.length() == 0) return true;

    // iterate through our input string
    for (int i = 0; i < input.length(); i++) {
      String b = String.valueOf(input.charAt(i));
      // address three possibilities we care about: left bracket, right bracket, or neither
      if (isLeftBracket(b)) {
        stack.push(b);
      } else if (isRightBracket(b)) {
        if (stack.isEmpty()) return false; // nothing is available to match with
        String character = stack.pop();
        if (!isMatch(character, b)) return false; // we have an invalid combination like (}
      } else {
        continue;// ignore anything that's not parentheses
      }
    }
    // finally check that there is nothing remaining in the stack without a balancer
    while(!stack.isEmpty()) {
      String character = stack.pop();
      if (isBracket(character)) return false;
    }
    return true; // all checks have passed
  }

  public static boolean isBracket(String c) {
    return isLeftBracket(c) || isRightBracket(c);
  }

  public static boolean isLeftBracket(String c) {
    return c.equals("(") || c.equals("{") || c.equals("[");
  }

  public static boolean isRightBracket(String c) {
    return c.equals(")") || c.equals("}") || c.equals("]");
  }

  public static boolean isMatch(String a, String b) {
    if (a.equals("(")) return b.equals(")");
    if (a.equals("{")) return b.equals("}");
    if (a.equals("[")) return b.equals("]");
    return false;
  }

  public static void test(String s) {
    System.out.println(s + " --> " + isBalanced(s));
  }

  public static void main(String[] args) {
    test("()");
    test("{([])}");
    test("())}");
    test("(()");
    test("[]{}()");
    test("(){}{[()()()()()()]()}");
    test("foo");
    test("123");
    test("[hello world](1+2)");
  }
}
