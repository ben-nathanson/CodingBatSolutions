/*
* We'll say that a "triple" in a string is a char appearing three times in a row. 
* Return the number of triples in the given string. The triples may overlap.
* https://codingbat.com/prob/p195714
*/

public int countTriple(String str) {
  int stringLength = str.length(); // save string length
  if (stringLength < 2) return 0; // special case: a string shorter than three characters
  int totalCounter = 0; // counts the total number of triples
  for(int pC = 2; (pC + 1 <= stringLength); pC++) {
    // check if the character to the left matches
    if (str.charAt(pC) == str.charAt(pC-1) && 
    // check if the character two characters to the left matches
    (str.charAt(pC) == str.charAt(pC-2))) {
    // if both conditions are true, we have a triplet
      totalCounter++; //  increment the counter
    }
  }
  return totalCounter;
}
