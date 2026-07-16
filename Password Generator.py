import random
import string

def generate_password(length=12):

    allowed_symbols = "!@#$*"
    
    characters = string.ascii_letters + string.digits + allowed_symbols

    password = ''.join(random.choice(characters) for i in range(length))
    return password

try:
    length = int(input("Enter password length: "))
    if length < 4:
        print("Length too short, setting to 4.")
        length = 4
    print("Generated Password: ", generate_password(length))
except ValueError:
    print("Please enter a valid number.")