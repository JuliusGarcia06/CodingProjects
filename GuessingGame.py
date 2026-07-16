import random

random_num = random.randint(1,100)
userGuess = 0

print("I'm thinking of a number between 1 and 100")

while userGuess != random_num:
    userGuess = int(input("Enter a number between 1 and 100 to win: "))
    if(userGuess < random_num):
        print(f"{userGuess} is too low!")
    elif(userGuess > random_num):
        print(f"{userGuess} is too high!")

print(f"{userGuess} is correct!!! YOU WIN!!!")