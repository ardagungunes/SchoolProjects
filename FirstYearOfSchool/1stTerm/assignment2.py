mapp = input("Please enter feeding map as a list:\n")
directions = input("Please enter direction of movements as a list:\n")
map_list = []
directions_list = list()
column = mapp.count("]") - 1  # How many column in the map


def char_control(ch):
    return (ch == "A" or ch == "X" or ch == "W" or ch == "M" or ch == "P" or ch == "*" or ch == "C" or ch == "U"
            or ch == "D" or ch == "L" or ch == "R")


empty_list = [ch for ch in mapp if char_control(ch)]


def write_row(lst):
    for x in lst:
        print(*x)


column_count = len(empty_list) / column
# This for loop creates nested lists
for j in range(1):
    row = []
    for ch in mapp:
        if char_control(ch):
            row.append(ch)
            column_count -= 1
            if column_count == 0:
                map_list.append(row)
                row = []
                column_count = len(empty_list) / column

print("Your board is:")
write_row(map_list)  # In order to avoid semantic errors of  list copying

nested_list = map_list.copy()  # For further.


directions_list = [ch for ch in directions if char_control(ch)]


def rabbit_position():
    indexx = 0
    count = 0
    for lst in map_list:
        if indexx < len(map_list[0]):
            for i in lst:
                if i == "*":
                    return [count, indexx]

                else:
                    indexx += 1
                    if indexx == len(map_list[0]):
                        indexx = 0

            count += 1
            if count == len(map_list):
                count = 0


a, b = rabbit_position()
point = 0
for x in directions_list:

    if x == "U":
        if nested_list[a-1][b] != "W" and a != 0:

            nested_list[a][b] = "X"

            if nested_list[a-1][b] == "P":
                nested_list[a - 1][b] = "*"
                break
            elif nested_list[a-1][b] == "C":
                point += 10

            elif nested_list[a-1][b] == "A":
                point += 5

            elif nested_list[a-1][b] == "M":
                point -= 5

            nested_list[a - 1][b] = "*"
            a -= 1

    elif x == "D":
        if nested_list[a+1][b] != "W" and a != len(nested_list) - 1:

            nested_list[a][b] = "X"

            if nested_list[a+1][b] == "P":
                nested_list[a + 1][b] = "*"
                break
            elif nested_list[a+1][b] == "C":
                point += 10

            elif nested_list[a+1][b] == "A":
                point += 5

            elif nested_list[a+1][b] == "M":
                point -= 5

            nested_list[a + 1][b] = "*"
            a += 1

    elif x == "R":

        if nested_list[a][b+1] != "W" and b != len(nested_list[0]) - 1:

            nested_list[a][b] = "X"

            if nested_list[a][b+1] == "P":
                nested_list[a][b + 1] = "*"
                break

            elif nested_list[a][b+1] == "C":
                point += 10

            elif nested_list[a][b+1] == "A":
                point += 5

            elif nested_list[a][b+1] == "M":
                point -= 5

            nested_list[a][b + 1] = "*"
            b += 1

    elif x == "L":
        if nested_list[a][b-1] != "W" and b != 0:

            nested_list[a][b] = "X"

            if nested_list[a][b-1] == "P":
                nested_list[a][b-1] = "*"
                break
            elif nested_list[a][b-1] == "C":
                point += 10

            elif nested_list[a][b-1] == "A":
                point += 5

            elif nested_list[a][b-1] == "M":
                point -= 5

            nested_list[a][b - 1] = "*"
            b -= 1

print("Your output should be like this:")
write_row(nested_list)
print("Your score is:", point)




