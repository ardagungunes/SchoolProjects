import sys
import copy  # While I was creating inverse matrix I didn't want to change older key matrix

operation_type = sys.argv[1]
key_file_path = sys.argv[2]
input_file_path = sys.argv[3]
output_file_name = sys.argv[4]


legal_chars = {"A": 1, "a": 1, "B": 2, "b": 2, "C": 3, "c": 3, "D": 4, "d": 4, "E": 5, "e": 5,
               "F": 6, "f": 6, "G": 7, "g": 7, "H": 8, "h": 8, "I": 9, "i": 9, "J": 10, "j": 10,
               "K": 11, "k": 11, "L": 12, "l": 12, "M": 13, "m": 13, "N": 14, "n": 14, "O": 15, "o": 15,
               "P": 16, "p": 16, "Q": 17, "q": 17, "R": 18, "r": 18, "S": 19, "s": 19, "T": 20, "t": 20,
               "U": 21, "u": 21, "V": 22, "v": 22, "W": 23, "w": 23, "X": 24, "x": 24, "Y": 25, "y": 25,
               "Z": 26, "z": 26, " ": 27}

decoding = {1: "A", 2: "B", 3: "C", 4: "D", 5: "E", 6: "F", 7: "G", 8: "H", 9: "I", 10: "J", 11: "K",
            12: "L", 13: "M", 14: "N", 15: "O", 16: "P", 17: "Q", 18: "R", 19: "S", 20: "T", 21: "U",
            22: "V", 23: "W", 24: "X", 25: "Y", 26: "Z", 27: " "}

legal_key = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ","]

# Error checking line 25-75
try:
    key_file = open(key_file_path, "r")
except FileNotFoundError:
    print("Key file not found error")
    exit()
try:
    f = open(input_file_path, "r")
except FileNotFoundError:
    print("Input file not found error")
    exit()

if len(sys.argv[1:]) != 4:
    print("Parameter number error")
    exit()

elif operation_type != "dec" and operation_type != "enc":
    print("Undefined parameter error")
    exit()

elif not input_file_path.endswith(".txt"):
    print("The input file could not be read error")
    exit()

elif f.readline() == "":
    print("Input file is empty error")
    exit()

elif not key_file_path.endswith(".txt"):
    print("Key file could not be read error")
    exit()

elif key_file.read() == "":
    print("Key file is empty error")
    exit()

f.seek(0)  # To read again
key_file.seek(0)

for i in f.read():
    if operation_type == "enc":
        if i not in legal_chars.keys():
            print("Invalid character in input file error")
            exit()


for i in key_file.readlines():
    i = i.rstrip("\n")
    for x in i:
        if x not in legal_key:
            print("Invalid character in key file error")
            exit()

f.close()
key_file.close()

key_file = open(key_file_path, "r")


def get_key_matrix(key):
    lst = []
    key_matrix = []
    for i in key.readlines():
        i = i.strip("\n")

        lst.append(i.split(","))

    for x in lst:
        row = []
        for j in x:
            j = int(j)
            row.append(j)

        key_matrix.append(row)

    key.seek(0)  # To read or make operations again

    return key_matrix


key_lst = get_key_matrix(key_file)


def get_list_of_input(input_file_path):
    """This function takes an input file and appends its elements to a list"""

    if operation_type == "enc":

        input_file = open(input_file_path, "r")

        chars = input_file.read()

        uncoded_numbers = [[legal_chars[char]] for char in chars]

        #  To find out the space count we need if there is
        needed_space = len(uncoded_numbers) % len(key_lst[0])

        if needed_space != 0:
            while len(chars) % len(key_lst[0]) != 0:
                chars += " "



        uncoded_numbers = [[legal_chars[char]] for char in chars]

        return uncoded_numbers

    elif operation_type == "dec":
        input_file = open(input_file_path, "r")
        numbers = input_file.read().split(",")

        numbers = [[int(i)] for i in numbers]

        return numbers


def matrix_of_uncoded(lst):
    """This function returns matrix of the message"""
    counter = len(key_lst)
    matrices_of_letters = []
    row = []
    for i in lst:
        row.append(i)
        counter -= 1
        if counter == 0:
            matrices_of_letters.append(row)
            row = []
            counter = len(get_key_matrix(key_file))

    return matrices_of_letters


def multiply_matrices(matrix1, matrices):
    encoded_msg = []
    for matrix2 in matrices:
        result = [[0] for i in range(len(matrix1))]
        for x in range(len(matrix1)):
            for j in range(len(matrix2[0])):
                for k in range(len(matrix2)):
                    result[x][j] += matrix1[x][k] * matrix2[k][j]

        encoded_msg.append(result)

    return encoded_msg


# I used Gauss-Jordan Method for taking the inverse matrix
def get_inverse_matrix(matrix):

    # Firstly I created an identity matrix according to key matrix size
    inverse_matrix = copy.deepcopy(matrix)
    identity_matrix = [[0]*len(matrix)for i in range(len(matrix))]
    counter = 0
    for i in identity_matrix:
        i[counter] = 1
        counter += 1

    clone_identity = copy.deepcopy(identity_matrix)

    a = 0
    while a < len(matrix):
        lst = []  # this list for storing the default row of key matrix
        lst_identity = []  # for storing the default row of identity matrix

        # In this if statement I wanted to check whether default diagonal consist of zero or not
        if inverse_matrix[a][a] == 0:
            # if there is a zero I make row operations again
            for i, j in zip(inverse_matrix, clone_identity):
                if i[a] != 0:
                    for x in range(len(matrix)):
                        inverse_matrix[a][x] += i[x]
                        clone_identity[a][x] += j[x]

            if a == 0:
                pass
            else:
                a -= 1

        divider = 1/(inverse_matrix[a][a])  # to get inverse I have to obtain 1 for every diagonal

        # I used zip because if I change something in original matrix I have to change in identity too.
        for i,j in zip(inverse_matrix[a], clone_identity[a]):

            i *= divider
            lst.append(i)

            j *= divider
            lst_identity.append(j)

        inverse_matrix[a] = lst
        clone_identity[a] = lst_identity

        addition_key = copy.deepcopy(lst)
        addition_identity = copy.deepcopy(lst_identity)

        for x, y in zip(inverse_matrix, clone_identity):

            if x == inverse_matrix[a]:
                pass

            else:
                multiplier = -(x[a])  # To get zero for every non-diagonal part

                for k in range(len(matrix)):
                    addition_key = copy.deepcopy(lst)
                    addition_key[k] *= multiplier
                    x[k] += addition_key[k]

                for l in range(len(matrix)):
                    addition_identity = copy.deepcopy(lst_identity)

                    if y == addition_identity:
                        pass
                    else:
                        addition_identity[l] *= multiplier
                        y[l] += addition_identity[l]

        a += 1

    return clone_identity


if operation_type == "enc":
    strr = ""
    output_file = open(output_file_name, "w")
    a = get_list_of_input(input_file_path)
    b = matrix_of_uncoded(a)
    encoded_message = multiply_matrices(key_lst, b)

    for i in encoded_message:
        for x in i:
            strr += (str(x[0]))
            strr += ","

    strr = strr.rstrip(",")

    output_file.write(strr)
    output_file.close()


elif operation_type == "dec":
    strr = ""
    output_file = open(output_file_name, "w")
    inverse_matrix = get_inverse_matrix(key_lst)
    a = get_list_of_input(input_file_path)
    b = matrix_of_uncoded(a)
    encoded_message = multiply_matrices(inverse_matrix, b)

    for i in encoded_message:
        for x in i:
            strr += decoding[round(float(x[0]))]

    output_file.write(strr)
    output_file.close()