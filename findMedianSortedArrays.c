/*Find the median of two sorted arrays. 
* https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/
* This method merges two sorted arrays, and then calculates the median value of 
* the combined array. 
*/
double findMedianSortedArrays(int * nums1, int nums1Size, int * nums2, int nums2Size) {
  int workingArray[nums1Size + nums2Size];
  int a = 0; // index for m
  int b = 0; // index for n
  int counter = 0; // counter
  int lim = nums1Size + nums2Size; // limit, equal to the size of the final concatenated array
  while(counter <= lim) { // merge and sort the two parent arrays
    // if we've added all the elements from nums1
    // add all the remaining elements from nums2
    if (a >= nums1Size) {
      while (b < nums2Size) {
        workingArray[counter++] = nums2[b++];
      }
    }
    // if we've added all the elements from nums2
    if (b >= nums2Size) {
      while (a < nums1Size) {
        workingArray[counter++] = nums1[a++];
      }
    }
    // add all the remaining elements from nums1
    // otherwise add selectively
    //
    if ((nums1[a] <= nums2[b]) && (a < nums1Size)) {
      workingArray[counter] = nums1[a++]; // add that number and increment the index
    }
    else {
      if (b < nums2Size) {
          workingArray[counter] = nums2[b++]; // add that number and increment the index
      }
    }
    counter++;
  }
  // now we should we have a concatenated, sorted array
  // find the median
  if (lim & 1) { // if the size of the working array is odd, we will get a remainder of 1
    return workingArray[(lim / 2)]; // return the middle number
  } else { // if the size of the working array is even, we return the average of the two middle numbers
    double leftMed = workingArray[(lim / 2) - 1];
    double rightMed = workingArray[lim / 2];
    return (leftMed + rightMed) / 2;
  }
  return -1;
}
