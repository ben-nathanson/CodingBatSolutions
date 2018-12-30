/*
* Given a string, return the length of the largest "block" in the string. 
* A block is a run of adjacent chars that are the same.
* https://codingbat.com/prob/p179479
*/
public int maxBlock(String str) {
  int max = 0;// the shortest possible max block is 0, or an empty string
  int temp;   // not initialized intentionally
  int end = str.length()-1;
  for (int i = 0; i < end;) {// iterate through the string str
    temp = 1; // 1 because every character is block of size 1 with itself
    // check if we have a multi-character block
    if (str.charAt(i)==str.charAt(i+1)) {
        temp++; // count the first match towards temp
        i++;    // increment the index
         // check if there is another character to the right,
         //  and check if that character matches
         while(i < end && str.charAt(i) == str.charAt(i+1)) {
           temp++; // count the matching character
           i++;    // increment the index
         }
    } else i++; // move on to the next character and see if that starts a match
    if (temp > max) max = temp; // if temp is greater than the current maximum
  }
return max;
}
/*
* Reflections:
* Lesson learned: every character matches with itself and we can't get
* around this by setting max to 1 because of the special case in which
* we have the empty string, where max would equal 0. 
*   
* Lesson learned: we can optimize by incrementing i and temp before the while, 
* loop, saving us from checking the same condition more than once
* 
* Lesson learned: check if the index i is out of bounds before using an 
* array access in a condition! 
*/
