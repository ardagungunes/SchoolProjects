import sys
arguments = sys.argv[1:]

if len(arguments) > 2:
    print("kaBOOM: run for your life!")
    print()
    print("- Game Over -")
    exit()

elif len(arguments) < 2 :
    print("IndexError: number of input files less than expected.")
    print()
    print("- Game Over -")
    exit()

try :
    if arguments[0] != "operands.txt":

        raise IOError(f"IOError: cannot open {arguments[0]}")

    elif arguments[1] != "comparison_data.txt":

        raise IOError(f"IOError: cannot open {arguments[1]}")


except IOError as msg:
    print(msg)
    exit()


operands = open(arguments[0], "r")
comparisons = open(arguments[1], "r")

for line in operands.readlines():
    list1 = []
    line = line.strip("\n")
    lst = line.split()
    lst2 = []
    for i in comparisons.readline().rstrip("\n").split():
        lst2.append(i)

    try:


        div = float(lst[0])
        nondiv = float(lst[1])
        fromm = float(lst[2])
        to = float(lst[3])
        div,nondiv,fromm,to = int(div),int(nondiv),int(fromm),int(to)


        if div == 0 or nondiv == 0:
            raise ZeroDivisionError("You can’t divide by 0.")

        if len(lst) > 4:
            print("------------")
            print("kaBOOM: run for your life!")
            continue



        for x in range(fromm,to + 1):
            if x % div == 0 and x % nondiv != 0:
                list1.append(str(x))


        #lst2 = list(map(int,lst2))







        if sorted(list1) == sorted(lst2):
            print("-------------")
            print("My Results:",*list1)
            print("Results to Compare:",*lst2)
            print("Goool!!!")

        else :
            print("-------------")
            print("My Results:", *list1)
            print("Results to Compare:", *lst2)
            print("AssertionError: results don't match.")




    except ValueError:
        print("-------------")
        print("Value Error: only numeric input is accepted.")
        print("Given input:",*lst)

    except IndexError:
        print("-------------")
        print("Index Error: number of operands less than expected.")
        print("Given input:", *lst)

    except ZeroDivisionError as msg:
        print("-------------")
        print(f"ZeroDivisionError {msg}")
        print("Given input:", *lst)

    except:
        print("kaBOOM: run for your life!")
print()
print("- Game Over -")












