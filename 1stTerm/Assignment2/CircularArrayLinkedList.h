//
// Created by ardab on 24.11.2021.
//

#ifndef ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
#define ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
//#include "Employee.h"
#include "TemporaryEmployee.h"
#include "DoubleDynamicLinkedList.h"

//using namespace std;


// I used 2 links for sorting
struct Node{
    TemporaryEmployee info;
    int next;
};

class CircularArrayLinkedList{

private:

    int head;



    int total_employee;

    Node node[20];//circular singly linked list



public:
    CircularArrayLinkedList();
    void add(TemporaryEmployee temporaryEmployee);
    bool isFull();
    int findEmployee(int num);
    void discardEmployee(int num);
    void printList();
    void sort();
    void changeTitleSalary(int,string,double);
    void printInformation(int num);
    void printSortedSix(DoubleDynamicLinkedList doubleDynamicLinkedList);
    void printSortedSeven(DoubleDynamicLinkedList doubleDynamicLinkedList);
    void sortDate();
    void sortDateReverse();
    void printSortedEight(DoubleDynamicLinkedList doubleDynamicLinkedList, Date &dt);
    void sortedNine(DoubleDynamicLinkedList doubleDynamicLinkedList, int year);
    void sortedTen(DoubleDynamicLinkedList doubleDynamicLinkedList, Date &dt);
    void sortedEleven(DoubleDynamicLinkedList doubleDynamicLinkedList, int month);
    void printTwelve(DoubleDynamicLinkedList doubleDynamicLinkedList,string title);
    Node getLastTitle(string title);









};




#endif //ASSIGNMENT2_CIRCULARARRAYLINKEDLIST_H
