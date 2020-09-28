# This solves the "Diagonal Traversal" problem where we want to traverse
# an N X M array in diagonal order

from typing import List

class Direction:
    UP:str = "UP"
    DOWN: str = "DOWN"

class Solution:
    element_limit = 10000
    def findDiagonalOrder(self, matrix: List[List[int]]) -> List[int]:
        if matrix is None:
            return []

        number_of_rows = len(matrix)
        if number_of_rows == 0:
            return []

        number_of_columns = len(matrix[0])
        
        if number_of_columns == 0:
            return []

        assert (number_of_columns * number_of_rows) <= self.element_limit

        row_index: int = 0
        column_index: int = 0
        direction = Direction.UP
        
        last_element_row: int = number_of_rows - 1
        last_element_column: int = number_of_columns - 1

        result: List[int] = []
        while True:
            element: int = matrix[row_index][column_index]
            result.append(element)
            # indicates that this is the very last element in the traversal:
            if row_index == last_element_row and column_index == last_element_column:
                break

            if direction == Direction.UP:
                row_index -= 1
                column_index += 1
                # indicates the end of a row:
                if column_index >= number_of_columns:
                    direction = Direction.DOWN
                    row_index += 2
                    column_index -= 1
                # indicates that we just passed the 0th row
                elif row_index < 0:
                    direction = Direction.DOWN
                    row_index += 1
            elif direction == Direction.DOWN:
                row_index += 1
                column_index -= 1
                # indicates the beginning of a row:
                if column_index < 0:
                    direction = Direction.UP
                    if row_index >= number_of_rows:
                        row_index -= 1
                        column_index += 2
                    else:
                        column_index += 1
                elif row_index >= number_of_rows:
                    direction = Direction.UP
                    row_index -= 1
                    column_index += 2
            else:
                assert False, "This should not be reachable."
        return result

input = [
 [ 1, 2, 3, 4],
 [ 5, 6, 7, 8 ],
 [ 9, 10, 11, 12],
 [ 13, 14, 15, 16],
]
expected = [1 , 2, 5, 9, 6, 3, 4, 7, 10, 13, 14, 11, 8, 12 , 15, 16]
s=Solution()
result = s.findDiagonalOrder(input)
print(f"found:    {result}")
print(f"expected: {expected}")
assert expected == result
