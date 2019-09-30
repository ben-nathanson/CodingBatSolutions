# we need to pick a random number
import random
# we need to keep track of The possible moves
moves = ["rock", "paper", "scissors"]

# Player 1 plays here:
player_1_choice = input("Rock, paper, scissors?\n").lower()
while player_1_choice not in moves:
    player_1_choice = input("Rock, paper, scissors?\n").lower()

# The computer plays here:
computer_choice = moves[random.randint(0,2)]
print("The computer chose " + computer_choice)
result = None

# We decided who has won here:
if computer_choice == player_1_choice:
    print("It's a draw!")
    exit()
if computer_choice == "rock":
    if player_1_choice == "paper":
        print("The player wins!")
        exit()
    else:# player chose scissors
        print( "The player loses!")
        exit()
if computer_choice == "paper":
    if player_1_choice == "scissors":
        print("The player wins!")
        exit()
    else:# player chose rock
        print("The player loses!")
        exit()
if computer_choice == "scissors":
    if player_1_choice == "rock":
        print("The player wins!")
        exit()
    else:
        print("The player loses!")
        exit()
