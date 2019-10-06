/*
Given a string and an integer k, you need to reverse the first k characters
for every 2k characters counting from the start of the string. 
If there are less than k characters left, reverse all of them. 
If there are less than 2k but greater than or equal to k characters, 
then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
*/
class ReverseK {
    public void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public String reverseStr(String s, int k) {
        int strLength = s.length();
        if (strLength == 1) return s;
        char[] strArray = s.toCharArray();
        // iterate through the string
        int i;
        for (i = 0; i < strLength; i += (2*k)) {
            // reverse the subsections at every 2k characters
            int left = i;
            int right = left + k - 1;
            if (right >= strLength) break;
            while(left < right) {
                swap(strArray, left++, right--);
            }

        }
        int charactersLeft = strLength - i;
        if (charactersLeft < k) {
            //reverse all of the characters left at the end of the string
            // from s[i] to s[s.length()-1]
            int left = i;
            int right = strLength - 1;
            while(left < right) {
                swap(strArray, left++, right--);
            }
        }
        return String.valueOf(strArray);
    }

    public static void main(String args[]) {
      ReverseK r = new ReverseK();
      System.out.println("Original: abcdefg\nResult:" + r.reverseStr("abcdefg",2));
    }
}
