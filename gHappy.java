/*
* We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' 
* immediately to its left or right. Return true if all the g's in the given 
* string are happy.
* https://codingbat.com/prob/p198664
*/
public boolean gHappy(String str) {
  if(str.length() == 1) return false; // special case: cannot have more than 1 'g'
  for(int i = 0; i < str.length(); i++) {
    if((i > 0) && (i < str.length() - 1)) { // common case: a middle letter
      if(str.charAt(i) == 'g') { // do we have a g?
        if(str.charAt(i+1) == 'g') {  // is the right neighbor a g?
            i+=2;
        } else return false; // if the previous 2 conditions were not satisfied return false
      }
    } else if(i == 0) {// special case: the first letter
      if(str.charAt(0) == 'g' && str.charAt(1) != 'g') return false;
    } else {           // special case: the last letter
      if(str.charAt(i) == 'g' && str.charAt(i-1) != 'g') return false;
    }
  }
  return true; // if all the conditions have passed in every iteration, return true
}
