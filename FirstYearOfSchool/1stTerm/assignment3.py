import sys
f = open(sys.argv[1], "r")
commands = [line.split() for line in f.readlines()]
f.close()


board = {"a8":"R1","b8":"N1","c8":"B1","d8":"QU","e8":"KI","f8":"B2","g8":"N2","h8":"R2",
         "a7":"P1","b7":"P2","c7":"P3","d7":"P4","e7":"P5","f7":"P6","g7":"P7","h7":"P8",
         "a6": "  ","b6": "  ","c6": "  ","d6": "  ","e6": "  ","f6": "  ","g6": "  ","h6": "  ",
         "a5": "  ","b5": "  ","c5": "  ","d5": "  ","e5": "  ","f5": "  ","g5": "  ","h5": "  ",
         "a4": "  ","b4": "  ","c4": "  ","d4": "  ","e4": "  ","f4": "  ","g4": "  ","h4": "  ",
         "a3": "  ","b3": "  ","c3": "  ","d3": "  ","e3": "  ","f3": "  ","g3": "  ","h3": "  ",
         "a2":"p1","b2":"p2","c2":"p3","d2":"p4","e2":"p5","f2":"p6","g2":"p7","h2":"p8",
         "a1":"r1","b1":"n1","c1":"b1","d1":"qu","e1":"ki","f1":"b2","g1":"n2","h1":"r2"}

initial_board = board.copy()

rows = [1, 2, 3, 4, 5, 6, 7, 8]
columns = ["a", "b", "c", "d", "e", "f", "g", "h"]

def get_position(val,dct):
    for key,value in dct.items():
        if value == val:
            return key

def print_board(dct):
    count = 0
    for i in dct.values():
        print(i,end=" ")
        count+=1
        if count == 8:
            print()
            count = 0







def right_hor():
    pass



def dia(lst):
    pass

def is_legal(lst):
    old_position = get_position(lst[1], board)
    old_column, old_row = old_position[0], int(old_position[1])

    old_cl_index, old_row_index = columns.index(old_column), rows.index(old_row)

    new_position = lst[2]
    new_column, new_row = new_position[0], int(new_position[1])
    new_column_index, new_row_index = columns.index(new_column), rows.index(new_row)

    def up_vert():

        """
        It checks whether the piece can move upward

        """


        for i in range(new_row -1 ,old_row,-1):
            string = old_column+str(i)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].isupper():
            return True

        elif board[new_position].islower():
            return False

        return True

    def bupe_vert():
        for i in range(new_row -1 ,old_row,-1):
            string = old_column+str(i)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].islower():
            return True

        elif board[new_position].isupper():
            return False

        return True

    def down_vert():
        """
        It checks whether the piece can move downward

        """
        for i in range(new_row + 1,old_row):
            string = old_column+str(i)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].isupper():
            return True

        elif board[new_position].islower():
            return False

        return True

    def bdown_vertical():
        for i in range(new_row + 1,old_row):
            string = old_column+str(i)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].islower():
            return True

        elif board[new_position].isupper():
            return False

        return True


    def right_hor():
        for i in range(old_cl_index + 1,new_column_index):
            string = columns[i] + str(old_row)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].isupper():
            return True

        elif board[new_position].islower():
            return False

        return True

    def bright_hor():
        for i in range(old_cl_index + 1,new_column_index):
            string = columns[i] + str(old_row)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].islower():
            return True

        elif board[new_position].isupper():
            return False

        return True


    def left_hor():
        for i in range(new_column_index + 1,old_cl_index):
            string = columns[i] + str(old_row)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].isupper():
            return True

        elif board[new_position].islower():
            return False

        return True

    def bleft_hor():
        for i in range(new_column_index + 1,old_cl_index):
            string = columns[i] + str(old_row)

            if board[string].islower() or board[string].isupper():
                return False

        if board[new_position].islower():
            return True

        elif board[new_position].isupper():
            return False

        return True


    def white_dia():
        o_cl_ind = old_cl_index
        o_r_ind = old_row_index

        if new_column_index - old_cl_index < 0:

            for i in range(abs((new_row-old_row)-1)):
                o_cl_ind -= 1
                o_r_ind += 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper() :
                    return False

            if board[new_position].isupper() or board[new_position] == "  ":
                return True

            elif board[new_position].islower():
                return False

        elif new_column_index - old_cl_index > 0:
            for i in range(abs((new_row-old_row)-1)):
                o_cl_ind += 1
                o_r_ind += 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper() :
                    return False

            if board[new_position].isupper() or board[new_position] == "  ":
                return True

            elif board[new_position].islower():
                return False

        return False

    def bdia():
        o_cl_ind = old_cl_index
        o_r_ind = old_row_index

        if new_column_index - old_cl_index < 0:

            for i in range(abs((new_row - old_row) - 1)):
                o_cl_ind -= 1
                o_r_ind += 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].islower() or board[new_position] == "  ":
                return True



            elif board[new_position].isupper():
                return False



        elif new_column_index - old_cl_index > 0:
            for i in range(abs((new_row - old_row) - 1)):
                o_cl_ind += 1
                o_r_ind += 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].islower() or board[new_position] == "  ":
                return True

            elif board[new_position].isupper():
                return False

        return False


    def quenn_dia():
        o_cl_ind = old_cl_index
        o_r_ind = old_row_index

        if new_column_index - old_cl_index < 0:

            for i in range(abs(new_row - old_row) - 1):
                o_cl_ind -= 1
                o_r_ind -= 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].isupper() or board[new_position] == "  ":
                return True

            elif board[new_position].islower():
                return False

        elif new_column_index - old_cl_index > 0:
            for i in range(abs(new_row - old_row) - 1):
                o_cl_ind += 1
                o_r_ind -= 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].isupper() or board[new_position] == "  ":
                return True

            elif board[new_position].islower():
                return False

        return False



    def black_dia():
        o_cl_ind = old_cl_index
        o_r_ind = old_row_index

        if new_column_index - old_cl_index < 0:

            for i in range(abs(new_row - old_row) - 1):
                o_cl_ind -= 1
                o_r_ind -= 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].islower() or board[new_position] == "  ":
                return True

            elif board[new_position].isupper():
                return False

        elif new_column_index - old_cl_index > 0:
            for i in range(abs(new_row - old_row) - 1):
                o_cl_ind += 1
                o_r_ind -= 1
                pos = columns[o_cl_ind] + str(rows[o_r_ind])

                if board[pos].islower() or board[pos].isupper():
                    return False

            if board[new_position].islower() or board[new_position] == "  ":
                return True

            elif board[new_position].isupper():
                return False

        else :
            return False





    def white_pawn():
        if new_row - old_row == 1:
            if old_cl_index == new_column_index:
                if board[new_position].islower():
                    return False

                return True

        return False

    def black_pawn():
        if new_row - old_row == -1:
            if old_cl_index == new_column_index:
                if board[new_position].isupper():
                    return False

                return True

        return False

    def white_king():

        if abs(new_row - old_row) <= 1 and abs(new_column_index - old_cl_index) <= 1:
            if abs(new_row - old_row) == 1:
                if board[new_position].islower():
                    return False
                return True

            elif abs(old_cl_index - new_column_index) == 1:
                if board[new_position].islower():
                    return False
                return True

            elif abs(new_column_index - old_cl_index) == abs(new_row - old_row):
                if board[new_position].islower():
                    return False
                return True

            else:
                return False

        return False


    def black_king():
        if abs(new_row - old_row) <= 1 and abs(new_column_index - old_cl_index) <= 1:
            if abs(new_row - old_row) == 1:
                if board[new_position].isupper():
                    return False
                return True

            elif abs(old_cl_index - new_column_index) == 1:
                if board[new_position].isupper():
                    return False
                return True

            elif abs(new_column_index - old_cl_index) == abs(new_row - old_row):
                if board[new_position].isupper():
                    return False
                return True

            else:
                return False

        return False


    def white_knight():
        if (abs(new_column_index - old_cl_index) == abs(new_row - old_row)) and abs(new_row - old_row) == 1:
            if board[new_position].isupper() or board[new_position].islower():
                return False
            return True

        elif (abs(new_column_index - old_cl_index) == 1 and abs(new_row - old_row) == 2) or (abs(new_column_index - old_cl_index) ==
        2 and abs(new_row -old_row) == 1):
            if board[new_position].islower():
                return False
            return True

        return False

    def black_knight():
        if (abs(new_column_index - old_cl_index) == abs(new_row - old_row)) and abs(new_row - old_row) == 1:
            if board[new_position].isupper() or board[new_position].islower():
                return False
            return True



        elif (abs(new_column_index - old_cl_index) == 1 and abs(new_row - old_row) == 2) or (
                abs(new_column_index - old_cl_index) == 2 and abs(new_row - old_row) == 1):
            if board[new_position].isupper():
                return False
            return True

        return False

    def white_queen():
        if abs(old_row_index - new_row_index) == abs(old_cl_index - new_column_index):
            if new_row - old_row > 0:
                return white_dia()

            elif new_row - old_row < 0:
                return quenn_dia()

        elif new_row > old_row:
            if old_column == new_column:
                return up_vert()
            return False

        elif new_row < old_row:
            if old_column == new_column:
                return down_vert()
            return False

        elif new_row == old_row:
            if new_column_index > old_cl_index:
                return right_hor()

            elif new_column_index < old_cl_index:
                return left_hor()

        return False

    def black_quenn():
        if abs(old_row_index - new_row_index) == abs(old_cl_index - new_column_index):
            if new_row - old_row > 0:
                return bdia()

            elif new_row - old_row < 0:
                return black_dia()

        elif new_row > old_row:
            if old_column == new_column:
                return bupe_vert()
            return False

        elif new_row < old_row:
            if old_column == new_column:
                return bdown_vertical()
            return False

        elif new_row == old_row:
            if new_column_index > old_cl_index:
                return bright_hor()

            elif new_column_index < old_cl_index:
                return bleft_hor()

        return False




























    if lst[1].startswith("r"):  # for rook but can be used for queen too.

        if new_row != old_row:
            if new_row > old_row :
                if old_column == new_column :
                    return up_vert()
                return False

            elif new_row < old_row:
                if old_column == new_column :
                    return down_vert()
                return False

        elif new_row == old_row:  # You have to fill that line
            if new_column_index > old_cl_index :
                return right_hor()

            elif new_column_index < old_cl_index :
                return left_hor()

    elif lst[1].startswith("R"):
        if new_row != old_row:
            if new_row > old_row :
                if old_column == new_column :
                    return bupe_vert()
                return False

            elif new_row < old_row:
                if old_column == new_column :
                    return bdown_vertical()
                return False

        elif new_row == old_row:  # You have to fill that line
            if new_column_index > old_cl_index :
                return bright_hor()

            elif new_column_index < old_cl_index :
                return bleft_hor()




    elif lst[1].startswith("b"):

        if abs(old_row_index - new_row_index) == abs(old_cl_index - new_column_index):

            if new_row - old_row < 0:
                return False

            return white_dia()
        return False

    elif lst[1].startswith("B"):
        if abs(old_row_index - new_row_index) == abs(old_cl_index - new_column_index):

            if new_row - old_row > 0:
                return False

            return black_dia()

        return False

    elif lst[1].startswith("p"):
        return white_pawn()

    elif lst[1].startswith("P"):
        return black_pawn()

    elif lst[1].startswith("k"):
        return white_king()

    elif lst[1].startswith("K"):
        return black_king()

    elif lst[1].startswith("n"):
        return white_knight()

    elif lst[1].startswith("N"):
        return black_knight()

    elif lst[1].startswith("q"):
        return white_queen()

    elif lst[1].startswith("Q"):
        return black_quenn()

for cmd in commands:
    if cmd[0] == "exit":
        print("> exit")
        print("OK")
        break

    elif cmd[0] == "initialize":
        board = initial_board.copy()
        print("> initialize")
        print("OK")
        print("-------------------------")
        print_board(board)
        print("-------------------------")

    elif cmd[0] == "print":
        print("> print")
        print("-------------------------")
        print_board(board)
        print("-------------------------")


    elif cmd[0] == "move":
        old_pst = get_position(cmd[1],board)
        new_pst = cmd[2]
        if is_legal(cmd):
            if board[new_pst].startswith("k") or board[new_pst].startswith("K"):
                print(f"> move {cmd[1]} {cmd[2]}")
                print("FAILED")
                continue

            board[old_pst] = "  "
            board[new_pst] = cmd[1]



            print(f"> move {cmd[1]} {cmd[2]}")
            print("OK")


        else:
            print(f"> move {cmd[1]} {cmd[2]}")
            print("FAILED")




    elif cmd[0] == "showmoves":

        listt = []
        for i in board.keys():

            a = cmd.copy()
            a.append(i)
            old_pst = get_position(cmd[1],board)
            new_pst = a[2]
            if is_legal(a):
                listt.append(a[2])
                if board[a[2]].startswith("k") or board[a[2]].startswith("K"):
                    listt.remove(a[2])


        listt.sort()
        if len(listt) > 0:
            print(f"> showmoves {cmd[1]}")

            print(*listt)

        else :
            print(f"> showmoves {cmd[1]}")
            print("FAILED")





