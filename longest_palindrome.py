# From LeetCode

from typing import List


class Palindrome:
    def __init__(self, left_index: int, right_index: int):
        assert right_index >= left_index and right_index >= 0 and left_index >= 0
        self.right_index = right_index
        self.left_index = left_index

    def get_size(self) -> int:
        size: int = self.right_index - self.left_index + 1
        assert size > 0
        return size

    def get_substring(self, s: str) -> str:
        assert len(s) > self.right_index
        return s[self.left_index : self.right_index + 1]


class Solution:
    def prime(self, s: str):
        palindromes: List[Palindrome] = []
        i: int = 0
        length: int = len(s)
        while i < length - 1:
            # every character is a palindrome of size one
            palindromes.append(Palindrome(i, i))
            # add all palindromes of size two
            if s[i] == s[i + 1]:
                new_palindrome = Palindrome(i, i + 1)
                palindromes.append(new_palindrome)
            i += 1
        return palindromes

    def longestPalindrome(self, s: str) -> str:
        if s == None or len(s) == 0:
            return ""
        elif len(s) == 1:
            return s
        assert len(s) <= 1000
        old_palindromes: List[Palindrone] = self.prime(s)
        new_palindromes: List[Palindrome] = self.get_next_set_of_pallindromes(
            old_palindromes, s
        )
        while new_palindromes != []:
            old_palindromes = new_palindromes
            new_palindromes = self.get_next_set_of_pallindromes(old_palindromes, s)

        old_palindromes.sort(key=lambda x: x.get_size())

        solution: Palindrome = old_palindromes[-1]

        return solution.get_substring(s)

    def get_next_set_of_pallindromes(
        self, old_palindromes: List[Palindrome], s: str
    ) -> List[Palindrome]:
        length: int = len(s)
        new_palindromes: List[Palindrome] = []
        for p in old_palindromes:
            next_left_index: int = p.left_index - 1
            next_right_index: int = p.right_index + 1
            if next_left_index >= 0 and next_right_index < length:
                if s[next_left_index] == s[next_right_index]:
                    # we have a palindrome of size n + 1
                    new_palindromes.append(
                        Palindrome(next_left_index, next_right_index)
                    )
        return new_palindromes


assert Solution().longestPalindrome("") == ""
assert Solution().longestPalindrome("a") == "a"
assert Solution().longestPalindrome(None) == ""
assert Solution().longestPalindrome("abccba") == "abccba"
# Note "aba" would also be a valid solution in this case
assert Solution().longestPalindrome("abab") == "bab"
assert Solution().longestPalindrome("abcba") == "abcba"
assert Solution().longestPalindrome("cbbd") == "bb"
