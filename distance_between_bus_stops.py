from math import pow
from typing import List

class Solution:
    def start_to_end_traversal(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        n: int = len(distance)
        total: int = 0
        index: int = start
        end: int = int((destination - 1 + n) % n)
        while True:
            total += distance[index]
            if index == end:
                break
            else:
                index += 1
                if index == n:
                    index = 0
        return total

    def end_to_start_traverasal(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        return self.start_to_end_traversal(distance, destination, start)

    def distance_between_bus_stops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        n: int = len(distance)
        assert 1 <= n and n <= pow(10, 4)
        assert destination < n
        assert 0 <= start

        if start == destination:
            return 0

        clockwise_total: int = self.start_to_end_traversal(
            distance, start, destination
        )
        counterclockwise_total: int = self.end_to_start_traverasal(
            distance, start, destination
        )

        return min(clockwise_total, counterclockwise_total)


s = Solution()

assert s.distance_between_bus_stops([1, 2, 3, 4], 0, 3) == 4
assert s.distance_between_bus_stops([1, 2, 3, 4], 0, 3) == s.distance_between_bus_stops(
    [1, 2, 3, 4], 3, 0
)
assert s.distance_between_bus_stops([1, 2, 3, 4], 0, 2) == 3

assert s.distance_between_bus_stops(
    [7, 10, 1, 12, 11, 14, 5, 0], 7, 2
) == s.distance_between_bus_stops([7, 10, 1, 12, 11, 14, 5, 0], 2, 7)
assert s.distance_between_bus_stops([7, 10, 1, 12, 11, 14, 5, 0], 7, 2) == 17
