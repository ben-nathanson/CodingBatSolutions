# make change
quarter = 25
dime = 10
nickel = 5
penny = 1

def test():
    makechange(2) # bad entry
    makechange(-.33) # bad entry
    makechange(0) # should be 0 of everything
    makechange(0.99) # 3 quarters, 2 dimes, 4 pennies
    makechange(.33) # 1 quarter, 1 nickel, 3 pennies

def makechange(amount):
    if amount < 0:
        print("Invalid amount: less than zero.")
        return
    if amount >= 1:
        print("Please use dollar bills for amounts greater than $1.00")
        return
    change_left = int(amount * 100)
    num_quarters = int((change_left / quarter) - ((change_left / quarter) % 1))
    if num_quarters != 0:
        change_left -= num_quarters * quarter
    num_dimes = int((change_left / dime) - ((change_left / dime) % 1))
    if num_dimes != 0:
        change_left -= num_dimes * dime
    num_nickels = int((change_left / nickel) - ((change_left / nickel) % 1))
    if num_nickels != 0:
        change_left -= num_nickels * nickel
    num_pennies = change_left
    print("For " + str(amount) + ":")
    print(str(num_quarters) + " quarters")
    print(str(num_dimes) + " dimes")
    print(str(num_nickels) + " nickels")
    print(str(num_pennies) + " pennies")
    print("-----------")


test()
