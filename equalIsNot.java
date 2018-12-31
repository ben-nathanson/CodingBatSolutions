/*
 * Given a string, return true if the number of appearances of "is" anywhere in the
 * string is equal to the number of appearances of "not" anywhere in the string 
 * (case sensitive).
 * https://codingbat.com/prob/p141736
 * 
 * A naive implementation of this algorithm simply compared a substring to a 
 * string, and then incremented the substring starting character by 1. A better
 * way is to "skip" over pointlessly double-checking characters by incrementing
 * that starting character by more than one at a time. That is what I did here. 
 * Another solution would be to start by checking the last letter first, and 
 * determining the skip from there, as with the Knuth-Morris-Pratt algorithm. 
 */
public boolean equalIsNot(String str) {
 int notCount = 0; // the number of appearances of "not" 
 int isCount = 0; // the number of appearances of "is"
 
 // Count the number of "is" appearances
 for (int i = 0; i < str.length() - 1;) {
  if (str.charAt(i) == 'i') {       // does the 'i' match?
    if (str.charAt(i + 1) == 's') { // does the 's' match?
      isCount++;                    // increment the count
      i +=2;                        // skip checking the 's'
    } else i += 2;                  
  } else i++;
 }
 
 // Count the number of "not" appearances
 for (int i = 0; i < str.length() - 2;) {
  if (str.charAt(i) == 'n') {
   if (str.charAt(i + 1) == 'o') {
    if (str.charAt(i + 2) == 't') {
     notCount++; // all three characters matched. we can increment the counter
     i += 3;
    } else i += 3; // "no" matched but "not" did not, so we skip two iterations
   } else i += 2; // 'n' matched but "no" did not, so we skip an iteration
  } else i++; // n did not match, increment i by one
 }
 
 return notCount == isCount; // return whether the two counts are equal
}
