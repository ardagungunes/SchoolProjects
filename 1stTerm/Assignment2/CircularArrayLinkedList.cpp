//
// Created by ardab on 25.11.2021.
//

#include "CircularArrayLinkedList.h"

#include <iostream>
using namespace std;

CircularArrayLinkedList::CircularArrayLinkedList() {

    head = 0;
    total_employee = 0;

    for(int i = 0; i < 19; i++){
        node[i].next = i + 1;
    }
    node[19].next = -1;
}

bool CircularArrayLinkedList::isFull() {
    return(total_employee == 20);
}

void CircularArrayLinkedList::add(TemporaryEmployee temporaryEmployee) {

    if(!isFull()) {

        if (total_employee == 0) {

            node[head].info = temporaryEmployee;

    }   else {
            node[total_employee - 1].next = total_employee;
            node[total_employee].info = temporaryEmployee;
            node[total_employee].next = head;

    }
        total_employee++;
        sort();

}

}

int CircularArrayLinkedList::findEmployee(int num) {
    int cur = head;
    Node temp = node[head];
    for(int i = 0; i <total_employee; i++){
        if(temp.info.getMEmployeeNumber() == num){
            return cur;
        }
        cur++;
        temp = node[temp.next];
    }
    return -1;

}

void CircularArrayLinkedList::changeTitleSalary(int numm,string new_title, double new_salary){
    Node temp = node[head];
    for(int i = 0; i<total_employee; i++){
        if(numm == temp.info.getMEmployeeNumber()){
            node[i].info.setMTitle(new_title);
            node[i].info.setMSalaryCoefficient(new_salary);
            return;
        }
    }
}

void CircularArrayLinkedList::discardEmployee(int num) {

    if(findEmployee(num) > - 1){
        if(total_employee == 1){
            node[head].info = node[head + 1].info;
        }else{
            for(int i = findEmployee(num); i < total_employee;i++){
                node[i].info = node[i+1].info;
            }
            node[total_employee - 2].next = head;
        }
        total_employee--;

        sort();
    }

}

void CircularArrayLinkedList::printList() {

    Node temp = node[head];

    for(int i = 0; i < total_employee; i++){

        cout<<temp.info.getMEmployeeNumber()<<endl;
        temp = node[temp.next];

    }

}

void CircularArrayLinkedList::sort()
{
    int curr = head;
    int ind;
    TemporaryEmployee temp;

    if(total_employee > 1){

        for(Node current = node[head];current.next != 0;current = node[current.next]){


            ind = curr + 1;
            for(Node index = node[current.next];index.next != 1; index = node[index.next] ){

                if(current.info.getMEmployeeNumber() > index.info.getMEmployeeNumber()){
                    temp = current.info;
                    current.info = index.info;
                    index.info = temp;

                    node[curr].info = current.info;
                    node[ind].info = temp;
                }
                ind++;
            }

            curr++;

        }

    }


}

void CircularArrayLinkedList::sortDate() {

    int curr = head;
    int ind;
    TemporaryEmployee temp;

    if(total_employee > 1){

        for(Node current = node[head];current.next != 0;current = node[current.next]){


            ind = curr + 1;
            for(Node index = node[current.next];index.next != 1; index = node[index.next] ){

                if(current.info.getMDateOfAppointment() > index.info.getMDateOfAppointment()){
                    temp = current.info;
                    current.info = index.info;
                    index.info = temp;

                    node[curr].info = current.info;
                    node[ind].info = temp;
                }
                ind++;
            }

            curr++;

        }

    }

}

void CircularArrayLinkedList::printInformation(int num) {
    Node temp = node[head];

    for(int i = 0; i < total_employee; i++) {
        if(temp.info.getMEmployeeNumber() == num){
            cout << temp.info;
            return;
        }
        temp = node[temp.next];
    }

}

void CircularArrayLinkedList::printSortedSix(DoubleDynamicLinkedList doubleDynamicLinkedList) {
    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;

    Node temp1 = node[head];
    doubleDynamicLinkedList.sortEmp();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            cout<<temp1.info;
            temp1 = node[temp1.next];
        }else if(checkForCircular == total_employee){
            cout<<temp2->info;
            temp2 = temp2->next;
        }
        else{
            if((temp1.info.getMEmployeeNumber() > temp2->info.getMEmployeeNumber())){
                cout<<temp2->info;
                temp2 = temp2->next;
                checkForDouble++;
            }else if((temp1.info.getMEmployeeNumber() < temp2->info.getMEmployeeNumber())){
                cout<<temp1.info;
                temp1 = node[temp1.next];
                checkForCircular++;
            }
        }


        checkForLoop++;
    }

doubleDynamicLinkedList.sort();
}

void CircularArrayLinkedList::sortDateReverse() {

    int curr = head;
    int ind;
    TemporaryEmployee temp;

    if(total_employee > 1){

        for(Node current = node[head];current.next != 0;current = node[current.next]){


            ind = curr + 1;
            for(Node index = node[current.next];index.next != 1; index = node[index.next] ){

                if(current.info.getMDateOfAppointment() < index.info.getMDateOfAppointment()){
                    temp = current.info;
                    current.info = index.info;
                    index.info = temp;

                    node[curr].info = current.info;
                    node[ind].info = temp;
                }
                ind++;
            }

            curr++;

        }

    }

}

void CircularArrayLinkedList::printSortedSeven(DoubleDynamicLinkedList doubleDynamicLinkedList) {
    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;
    sortDate();

    Node temp1 = node[head];
    //doubleDynamicLinkedList.sortEmp();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            cout<<temp1.info;
            temp1 = node[temp1.next];
        }else if(checkForCircular == total_employee){
            cout<<temp2->info;
            temp2 = temp2->next;
        }
        else{
            if((temp1.info.getMDateOfAppointment() >= temp2->info.getMDateOfAppointment())){
                cout<<temp2->info;
                temp2 = temp2->next;
                checkForDouble++;
            }else if((temp1.info.getMDateOfAppointment() <= temp2->info.getMDateOfAppointment())){
                cout<<temp1.info;
                temp1 = node[temp1.next];
                checkForCircular++;
            }
        }


        checkForLoop++;
    }
    sort();
}

void CircularArrayLinkedList::printSortedEight(DoubleDynamicLinkedList doubleDynamicLinkedList, Date &dt) {

    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;
    sortDateReverse();

    Node temp1 = node[head];
    doubleDynamicLinkedList.sortReverse();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            if(temp1.info.getMDateOfAppointment() > dt) {
                cout << temp1.info;
                temp1 = node[temp1.next];
            }else{
                temp1 = node[temp1.next];
            }
        }else if(checkForCircular == total_employee){
            if(temp2->info.getMDateOfAppointment() > dt) {
                cout << temp2->info;
                temp2 = temp2->next;
            }else{
                temp2 = temp2->next;
            }
        }
        else{
            if((temp1.info.getMDateOfAppointment() <= temp2->info.getMDateOfAppointment())){
                if(temp2->info.getMDateOfAppointment() > dt) {
                    cout << temp2->info;
                    temp2 = temp2->next;
                    checkForDouble++;
                }
                else{
                    temp2 = temp2->next;
                    checkForDouble++;
                }
            }else if((temp1.info.getMDateOfAppointment() >= temp2->info.getMDateOfAppointment())){
                if(temp1.info.getMDateOfAppointment() > dt) {
                    cout << temp1.info;
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }else{
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }
            }
        }


        checkForLoop++;
    }
    sort();
    doubleDynamicLinkedList.sort();

}

void CircularArrayLinkedList::sortedNine(DoubleDynamicLinkedList doubleDynamicLinkedList, int year) {

    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;
    sortDate();

    Node temp1 = node[head];
    //doubleDynamicLinkedList.sortReverse();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            if(temp1.info.getMDateOfAppointment().getMYear() == year) {
                cout << temp1.info;
                temp1 = node[temp1.next];
            }else{
                temp1 = node[temp1.next];
            }
        }else if(checkForCircular == total_employee){
            if(temp2->info.getMDateOfAppointment().getMYear() == year) {
                cout << temp2->info;
                temp2 = temp2->next;
            }else{
                temp2 = temp2->next;
            }
        }
        else{
            if((temp1.info.getMDateOfAppointment() >= temp2->info.getMDateOfAppointment())){
                if(temp2->info.getMDateOfAppointment().getMYear() == year) {
                    cout << temp2->info;
                    temp2 = temp2->next;
                    checkForDouble++;
                }
                else{
                    temp2 = temp2->next;
                    checkForDouble++;
                }
            }else if((temp1.info.getMDateOfAppointment() <= temp2->info.getMDateOfAppointment())){
                if(temp1.info.getMDateOfAppointment().getMYear() == year) {
                    cout << temp1.info;
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }else{
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }
            }
        }


        checkForLoop++;
    }
    sort();
    doubleDynamicLinkedList.sort();

}

void CircularArrayLinkedList::sortedTen(DoubleDynamicLinkedList doubleDynamicLinkedList, Date &dt) {

    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;
    //sortDate();

    Node temp1 = node[head];
    doubleDynamicLinkedList.sortEmp();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            if(temp1.info.getMDateOfBirth() < dt) {
                cout << temp1.info;
                temp1 = node[temp1.next];
            }else{
                temp1 = node[temp1.next];
            }
        }else if(checkForCircular == total_employee){
            if(temp2->info.getMDateOfBirth() < dt) {
                cout << temp2->info;
                temp2 = temp2->next;
            }else{
                temp2 = temp2->next;
            }
        }
        else{
            if((temp1.info.getMEmployeeNumber() > temp2->info.getMEmployeeNumber())){
                if(temp2->info.getMDateOfBirth() < dt) {
                    cout << temp2->info;
                    temp2 = temp2->next;
                    checkForDouble++;
                }
                else{
                    temp2 = temp2->next;
                    checkForDouble++;
                }
            }else if((temp1.info.getMEmployeeNumber() < temp2->info.getMEmployeeNumber())){
                if(temp1.info.getMDateOfBirth() < dt) {
                    cout << temp1.info;
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }else{
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }
            }
        }


        checkForLoop++;
    }
    sort();
    doubleDynamicLinkedList.sort();

}

void CircularArrayLinkedList::sortedEleven(DoubleDynamicLinkedList doubleDynamicLinkedList, int month) {

    int doubleTotal = doubleDynamicLinkedList.getTotal();
    int checkForCircular = 0;
    int checkForDouble = 0;
    int min;
    int checkForLoop = 0;
    //sortDate();

    Node temp1 = node[head];
    //doubleDynamicLinkedList.sortReverse();
    Node1 * temp2 = doubleDynamicLinkedList.getHead();

    while(checkForLoop < (total_employee + doubleTotal)){

        if(checkForDouble == doubleTotal){
            if(temp1.info.getMDateOfBirth().getMMonth() == month) {
                cout << temp1.info;
                temp1 = node[temp1.next];
            }else{
                temp1 = node[temp1.next];
            }
        }else if(checkForCircular == total_employee){
            if(temp2->info.getMDateOfBirth().getMMonth() == month) {
                cout << temp2->info;
                temp2 = temp2->next;
            }else{
                temp2 = temp2->next;
            }
        }
        else{
            if((temp1.info.getMEmployeeNumber() > temp2->info.getMEmployeeNumber())){
                if(temp2->info.getMDateOfBirth().getMMonth() == month) {
                    cout << temp2->info;
                    temp2 = temp2->next;
                    checkForDouble++;
                }
                else{
                    temp2 = temp2->next;
                    checkForDouble++;
                }
            }else if((temp1.info.getMEmployeeNumber() < temp2->info.getMEmployeeNumber())){
                if(temp1.info.getMDateOfBirth().getMMonth() == month) {
                    cout << temp1.info;
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }else{
                    temp1 = node[temp1.next];
                    checkForCircular++;
                }
            }
        }


        checkForLoop++;
    }
    sort();
    doubleDynamicLinkedList.sort();

}

void CircularArrayLinkedList::printTwelve(DoubleDynamicLinkedList doubleDynamicLinkedList,string title) {

Node circularLast = getLastTitle(title);
Node1 doubleLast  = doubleDynamicLinkedList.getLastTitle(title);

if(total_employee == 0){
    cout << doubleLast.info;
}else if(doubleDynamicLinkedList.getTotal() == 0){
    cout<<circularLast.info;
}else {

    if (circularLast.info.getMDateOfAppointment() > doubleLast.info.getMDateOfAppointment()) {

        cout << circularLast.info;

    } else if (doubleLast.info.getMDateOfAppointment() > circularLast.info.getMDateOfAppointment()) {

        cout << doubleLast.info;
    }
}

}

Node CircularArrayLinkedList::getLastTitle(string title) {


    sortDateReverse();
    Node temp = node[head];
    for(int i = 0; i< total_employee; i++){

        if(temp.info.getMTitle() == title){
            return temp;
        }
        temp = node[temp.next];
    }

    sort();

}




