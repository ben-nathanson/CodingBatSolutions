# Based on problems from CMU's 15-295 Competitive Programming Course
# https://contest.cs.cmu.edu/295/f15/contest1.pdf

from typing import List


class Pair:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Solution:
    def solve(self, nums: List[Pair]) -> int:
        assert nums is not None
        assert len(nums) >= 2 and len(nums) <= 20
        x_list: list = []
        y_list: list = []
        for pair in nums:
            assert pair.x >= 1 and pair.y >= 1
            x_list.append(pair.x)
            y_list.append(pair.y)
        width: int = max(x_list) - min(x_list)
        height: int = max(y_list) - min(y_list)
        return pow(max(width, height), 2)


assert Solution().solve([Pair(3, 4), Pair(5, 7), Pair(4, 3)]) == 16
assert Solution().solve([Pair(1, 5), Pair(5, 1), Pair(10, 5), Pair(5, 10)]) == 81
