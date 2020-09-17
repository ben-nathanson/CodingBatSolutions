# Based on problems from CMU's 15-295 Competitive Programming Course
# https://contest.cs.cmu.edu/295/f15/contest1.pdf

from typing import List


class Solution:
    def unsolve(self, nums: List[int]) -> List[int]:
        r: List[int] = []
        running_total: int = 0
        index: int = 0
        while index < len(nums):
            running_total += nums[index]
            r.append(running_total / (index + 1))
            index += 1
        return r

    def solve(self, nums: List[int]) -> List[int]:
        r: List[int] = []
        running_total: int = 0
        index: int = 0
        while index < len(nums):
            a = nums[index]
            b = a * (index + 1)
            c = b - running_total
            r.append(c)
            running_total += c
            index += 1
        return r


assert Solution().solve([1, 2, 2, 3, 4]) == [1, 3, 2, 6, 8]
assert Solution().solve([2]) == [2]
assert Solution().solve([3, 2, 3, 5]) == [3, 1, 5, 11]
