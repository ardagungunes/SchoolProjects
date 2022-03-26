//
// Created by ardab on 24.11.2021.
//

#ifndef ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
#define ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
#include "PermanentEmployee.h"
#include <iostream>

using namespace std;

struct Node1 {
    PermanentEmployee info;
    Node1 * next;
    Node1 * prev;

};

class DoubleDynamicLinkedList{

private:
    Node1 * head;
    int total;


public:

    DoubleDynamicLinkedList();
    void push(PermanentEmployee permanentEmployee);
    void printList();
    bool findEmployee(int num);
    void deleteEmployee(int num);
    void sort();
    void printReverse();
    void update(int num, string new_title, double new_coefficient);
    void printInformation(int num);
    void sortEmp();
    Node1 * getHead(){return head;}
    int getTotal(){return total;}
    void sortReverse();
    Node1 getLastTitle(string title);

};

#endif //ASSIGNMENT2_DOUBLEDYNAMICLINKEDLIST_H
