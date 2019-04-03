public class stringReplaceTest {
    public static String stringReplace(String base, String remove, String replace) {
        int prev = 0; // defines index of the end of the previous remove string
        int next = base.indexOf(remove);
        // handle the special case where no remove string appears:
        if (next == -1) return base;
        // at this point we know we have 1 or more removes to perform
        // handle the normal case:
        int removeLength = remove.length();
        int baseLength = base.length();
        String nonRemoves = "";
        do {
            nonRemoves += base.substring(prev, next) + replace;
            prev = next + removeLength;
            // check if the most recent remove was at the end of the base string
            if (prev >= base.length()) break;
            next += base.substring(next + removeLength).indexOf(remove) + removeLength;
            // check if there are no more removes
            if (next < prev) {
                nonRemoves += base.substring(prev);
                break;
            }
        } while (true);
        // check that no remove strings were created in the process of appending
        // substring to the nonRemoves string
        if (nonRemoves.indexOf(remove) == -1) {
            return nonRemoves;
        } else {
            // recurse if necessary
            return stringReplace(nonRemoves, remove, replace);
        }
    }

    public static void print(Object input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        print(stringReplace("the red quick fox jumped over the lazy dogs", "the ", ""));
        print(stringReplace(stringReplace("<p>Some Text</p>", "<p>", "<h1>"), "</p>", "</h1>"));
        print(stringReplace("and one and two and three and four and", "and", ""));
    }
}
