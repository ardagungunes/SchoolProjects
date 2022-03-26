#include <iostream>
#include <string>
#include "Employee.h"
#include "TemporaryEmployee.h"
#include "PermanentEmployee.h"
#include "CircularArrayLinkedList.h"
#include "DoubleDynamicLinkedList.h"
#include "Helper.h"
#include <chrono>

using namespace std;

using namespace std::chrono;


int main() {
    CircularArrayLinkedList circularArrayLinkedList;
    DoubleDynamicLinkedList doubleDynamicLinkedList;
    while(true) {
        cout<<endl<<endl<<endl;

        userChoices();

        string input;

        cin>>input;

        if(input == "1"){
            choiceOne(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "2"){
            choiceTwo(circularArrayLinkedList, doubleDynamicLinkedList);
        }else if(input == "3"){
            choiceThree(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "4"){
            choiceFour(circularArrayLinkedList, doubleDynamicLinkedList);
        }else if(input == "5"){
            choiceFive(circularArrayLinkedList, doubleDynamicLinkedList);
        }else if(input == "6"){
            choiceSix(circularArrayLinkedList, doubleDynamicLinkedList);
        }else if(input == "7"){
            choiceSeven(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "8"){
            choiceEight(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "9"){
            choiceNine(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "10"){
            choiceTen(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "11"){
            choiceEleven(circularArrayLinkedList,doubleDynamicLinkedList);
        }else if(input == "12"){
            choiceTwelve(circularArrayLinkedList,doubleDynamicLinkedList);
        }else{
            break;
        }

    }




    return 0;
}
