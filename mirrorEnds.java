/* 
* Given a string, look for a mirror image (backwards) string at both the 
* beginning and end of the given string. In other words, zero or more characters 
* at the very begining of the given string, and at the very end of the string in
* reverse order (possibly overlapping). For example, the string "abXYZba" has 
* the mirror end "ab".
* https://codingbat.com/prob/p139411
*/
public String mirrorEnds(String string) {
  String mirror = "";                                // mirror = the mirrored characters
  int right = string.length()-1;                     // right = end character index
  int left;                                          // left = beginning character index
  for(left = 0; left < string.length(); left++) {    // loop a max of n times where n = length of string
    if(string.charAt(left) == string.charAt(right)) {// compare left and right
      mirror += string.charAt(left);                 // if the same, append char to mirror
      right--;                                       // move right "left" by decrementing it
    } else break;                                    // if different, break the loop
  }
  return mirror;
}
