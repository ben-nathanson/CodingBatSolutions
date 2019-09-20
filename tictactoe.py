import random
import time
#tic tac toe
# note: board size must always be an odd number
board_size = 3
empty = ' '
board = [[empty for i in range(board_size)] for j in range(board_size)]
player_1_symbol = 'X'
player_2_symbol = 'O'
art = [" __ _   ___  _   ___  _ "," /// `  //_// `  // //_`","///_,  // //_,  //_//_, "]

def print_board():
  for i in range(board_size):
    for j in range(board_size):
      print(board[i][j], end = '')
    print()

# check if there are x's and o's on the entire board
def board_is_full():
    for i in range(board_size):
      for j in range(board_size):
          if board[i][j] == empty:
              return False
    return True

def computer_play():

    # take any cell where the computer could immediately
    # use that cell to win the game
    for r in range(board_size):
        count = 0
        for c in range(board_size):
            if board[r][c] == player_2_symbol:
                count += 1
                if count == board_size - 1:
                    for d in range(board_size):
                        if board[r][d] == empty:
                            board[r][d] = player_2_symbol
                            return
    # column-wise
    for i in range(board_size):
        count = 0
        for j in range(board_size):
            if board[j][i] == player_2_symbol:
                count += 1
            if count == board_size - 1:
                for p in range(board_size):
                    if board[p][i] == empty:
                        board[p][i] = player_2_symbol
                        return
    # diagonal down
    for i in range(board_size):
        count = 0
        if board[i][i] == player_2_symbol:
            count += 1
        if count == board_size - 1:
            for p in range(board_size):
                if board[p][p] == empty:
                    board[p][p] = player_2_symbol
                    return

    # diagonal up
    diagonal_ctr = 0
    for i in range(board_size):
        if board[board_size - i - 1][i] == player_2_symbol:
            diagonal_ctr += 1
        if diagonal_ctr == board_size - 1:
            for p in range(board_size):
                if board[board_size - p - 1][p] == empty:
                    board[board_size - p - 1][p] = player_2_symbol
                    return

    # take out any cell where the enemy could immediately
    # use that cell to win the game
    # row-wise
    for r in range(board_size):
        count = 0
        for c in range(board_size):
            if board[r][c] == player_1_symbol:
                count += 1
                if count == board_size - 1:
                    for d in range(board_size):
                        if board[r][d] == empty:
                            board[r][d] = player_2_symbol
                            return

    # column-wise
    for i in range(board_size):
        count = 0
        for j in range(board_size):
            if board[j][i] == player_1_symbol:
                count += 1
            if count == board_size - 1:
                for p in range(board_size):
                    if board[p][i] == empty:
                        board[p][i] = player_2_symbol
                        return
    # diagonal down
    for i in range(board_size):
        count = 0
        if board[i][i] == player_1_symbol:
            count += 1
        if count == board_size - 1:
            for p in range(board_size):
                if board[p][p] == empty:
                    board[p][p] = player_2_symbol
                    return

    # diagonal up
    diagonal_ctr = 0
    for i in range(board_size):
        if board[board_size - i - 1][i] == player_1_symbol:
            diagonal_ctr += 1
        if diagonal_ctr == board_size - 1:
            for p in range(board_size):
                if board[board_size - p - 1][p] == empty:
                    board[board_size - p - 1][p] = player_2_symbol
                    return
    # take the middle spot if available
    middle = int(board_size / 2)
    if board[middle][middle] == empty:
        board[middle][middle] = player_2_symbol
        return

    # take a diagonal if available:
    up_viable = check_up_diagonal_viable(player_2_symbol)
    down_viable = check_down_diagonal_viable(player_2_symbol)

    if up_viable and down_viable:
        # if both options exist, flip a coin
        if random.randint(0,1) == 0:
            move_diagonal_down(player_2_symbol)
            return
        else:
            move_diagonal_up(player_2_symbol)
            return

    if up_viable:
        move_diagonal_up(player_2_symbol)
        return

    if down_viable:
        move_diagonal_down(player_2_symbol)
        return

    row_or_column_first = random.randint(0,1)
    if row_or_column_first == 0:
        #go through each row:
        # prioritize the first and last cells of a row
        for i in range(board_size):
            if check_row_viable(i, player_2_symbol):
                flip = random.randint(0,1)
                column_index = 0
                if flip == 1:
                    column_index = 0
                    if board[i][column_index] != empty:
                        column_index == board_size -1
                elif flip == 0:
                    column_index = board_size -1
                    if board[i][column_index] != empty:
                        column_index == 0
                while board[i][column_index] != empty:
                    column_index = random.randint(0, board_size - 1)
                board[i][column_index] = player_2_symbol
                return
    else:
        #go through each column:
        for i in range(board_size):
            if check_column_viable(i, player_2_symbol):
                flip = random.randint(0,1)
                row_index = 0
                if flip == 1:
                    row_index = 0
                    if board[i][row_index] != empty:
                        row_index == board_size -1
                elif flip == 0:
                    row_index = board_size -1
                    if board[i][row_index] != empty:
                        row_index == 0
                while board[row_index][i] != empty:
                    row_index = random.randint(0, board_size - 1)
                board[row_index][i] = player_2_symbol
                return

def move_diagonal_up(mark):
    i = random.randint(0, board_size - 1)
    while board[i][board_size - i -1] != empty:
        i = random.randint(0, board_size - 1)
    board[i][board_size - i -1] = mark
    return

def move_diagonal_down(mark):
    i = random.randint(0, board_size - 1)
    while board[i][i] != empty:
        print(board[i][i])
        print(empty)
        i = random.randint(0, board_size - 1)
    board[i][i] = mark
    return

def player_play():
    print("Type q to end the game at any time.")
    row_requested = input("Row: ")
    if row_requested.lower() == 'q':
        quit()
    column_requested = input("Column: ")
    if column_requested.lower() == 'q':
        quit()
    # the minus one is to account for the fact that users are
    # accustomed to things starting at 1 instead of 0
    try:
        row_index = int(row_requested) - 1
        column_index = int(column_requested) - 1
    except:
        print("Please enter valid coordinates.")
        player_play()
        return
    if row_index < 0 or column_index < 0:
        print("Please enter valid coordinates (greater than 0).")
        player_play()
        return
    if row_index >= board_size or column_index >= board_size:
        print("That spot is out of bounds.")
        player_play()
    elif board[row_index][column_index] != empty:
        print("That spot is already taken.")
        player_play()
    else:
         board[row_index][column_index] = player_1_symbol

def check_down_diagonal_viable(mark):
    for l in range(board_size):
        if board[l][l] != mark and board[l][l] != empty:
            break
        elif l == board_size - 1:
            return True
    return False

def check_row_viable(row, mark):
    for cell in board[row]:
        if cell != mark and cell != empty:
            return False
    return True

def check_column_viable(column_index, mark):
    for i in range(board_size):
        cell = board[i][column_index]
        if cell != mark and cell != empty:
            return False
    return True

def check_up_diagonal_viable(mark):
    m = board_size - 1
    for p in range(board_size):
        if (board[p][m] != mark) and (board[p][m] != empty):
            return False
        elif m == 0:
            return True
        m = m - 1

def check_win(mark):
    # horizontal
    for row in board:
        for i in range(board_size):
            if row[i] != mark:
                break
            elif i == board_size - 1:
                return True
    # vertical
    for j in range(board_size):
        for k in range(board_size):
            if board[k][j] != mark:
                break
            elif k == board_size - 1:
                return True
    # down-and-right diagonal
    for l in range(board_size):
        if board[l][l] != mark:
            break
        elif l == board_size - 1:
            return True
    m = board_size - 1
    # down-and-left diagonal
    for p in range(board_size):
        if board[p][m] != mark:
            break
        elif m == 0:
            return True
        m = m - 1
    # if no one has won, check that the board is full
    # if so, it's a draw
    if board_is_full():
        print("The board is full. It's a draw!")
        exit(0)
        return
    return False

def run_game():
    print("Flipping a coin...")
    for i in art:
        print(i)
        time.sleep(1)
    if random.randint(0,1) == 0:
        print("Player 1 plays first!")
        player_play()
        while(1):
            computer_play()
            print_board()
            if check_win(player_2_symbol):
                print_board()
                print("Computer wins!")
                exit(0)
            player_play()
            if check_win(player_1_symbol):
                print_board()
                print("Player wins!")
                exit(0)
    else:
        print("Computer plays first!")
        computer_play()
        while(1):
            print_board()
            player_play()
            if check_win(player_1_symbol):
                print_board()
                print("Player wins!")
                exit(0)
            computer_play()
            if check_win(player_2_symbol):
                print_board()
                print("Computer wins!")
                exit(0)

run_game()
