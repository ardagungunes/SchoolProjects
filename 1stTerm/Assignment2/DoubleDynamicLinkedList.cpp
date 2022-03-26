//
// Created by ardab on 26.11.2021.
//
#include "DoubleDynamicLinkedList.h"


DoubleDynamicLinkedList::DoubleDynamicLinkedList() {
    head = nullptr;
    total = 0;


}


void DoubleDynamicLinkedList::push(PermanentEmployee permanentEmployee) {

    Node1 * new_node = new Node1();
    new_node->info = permanentEmployee;


    if(head == nullptr){

        head = new_node;

        head->next = nullptr;
        head->prev = nullptr;


    }

    else {

        new_node->next = head;

        head->prev = new_node;
        head = new_node;
    }

    total ++;
    sort();

}

void DoubleDynamicLinkedList::printList() {
    Node1* temp = head;

    for(int i = 0; i < total;i++){
        cout << temp->info.getMEmployeeNumber() << endl;

        temp = temp->next;
    }

}

bool DoubleDynamicLinkedList::findEmployee(int num) {


    Node1 * temp= head;
    for(int i = 0;i < total;i++){
        if(total > 0 && temp->info.getMEmployeeNumber() == num){
            return true;
        }
        temp=temp->next;
    }

    return false;

}

void DoubleDynamicLinkedList::update(int num, string new_title, double new_coefficient) {

    Node1 * temp = head;

    for(int i = 0; i < total; i++){
        if(temp->info.getMEmployeeNumber() == num){
            temp->info.setMTitle(new_title);
            temp->info.setMSalaryCoefficient(new_coefficient);
            return;
        }
        temp = temp->next;
    }

}

void DoubleDynamicLinkedList::deleteEmployee(int num) {
    Node1 * temp = head;

    if(findEmployee(num) ){

        for(int i = 0; i < total; i++){
            if(temp->info.getMEmployeeNumber() == num){

                if((temp) == head){
                    head = head->next;
                }if(temp->next != nullptr){
                    temp->next->prev = temp->prev;

                }if(temp->prev != nullptr){
                    temp->prev->next = temp->next;
                }
                delete temp;
                break;
            }
            temp = temp->next;
        }
        total--;
        sort();
    }

}

void DoubleDynamicLinkedList::sort() {
    PermanentEmployee temp;
    if(total > 1){
        for(Node1 * current = head;current->next!= nullptr;current = current->next){

            for(Node1 *index = current->next;index!= nullptr;index = index->next){

        //Change this condition
                if(current->info.getMDateOfAppointment() > index->info.getMDateOfAppointment()){
                    temp = current->info;
                    current->info = index->info;
                    index->info = temp;

                }
            }
        }
    }

}

void DoubleDynamicLinkedList::sortReverse() {

    PermanentEmployee temp;
    if(total > 1){
        for(Node1 * current = head;current->next!= nullptr;current = current->next){

            for(Node1 *index = current->next;index!= nullptr;index = index->next){

                //Change this condition
                if(current->info.getMDateOfAppointment() < index->info.getMDateOfAppointment()){
                    temp = current->info;
                    current->info = index->info;
                    index->info = temp;

                }
            }
        }
    }

}

void DoubleDynamicLinkedList::printReverse() {

    Node1 * temp = head;

    while(temp->next != nullptr){
        temp = temp->next;
    }


    while(temp!= nullptr){
        cout<<temp->info.getMEmployeeNumber() << endl;
        temp = temp->prev;
    }

}

void DoubleDynamicLinkedList::printInformation(int num) {

    Node1 * temp = head;

    while(temp!= nullptr){
        if(temp->info.getMEmployeeNumber() == num){
            cout<<temp->info;
            return;
        }
        temp = temp->next;
    }

}

void DoubleDynamicLinkedList::sortEmp() {

    PermanentEmployee temp;
    if(total > 1){
        for(Node1 * current = head;current->next!= nullptr;current = current->next){

            for(Node1 *index = current->next;index!= nullptr;index = index->next){

                //Change this condition
                if(current->info.getMEmployeeNumber() > index->info.getMEmployeeNumber()){
                    temp = current->info;
                    current->info = index->info;
                    index->info = temp;

                }
            }
        }
    }

}

Node1 DoubleDynamicLinkedList::getLastTitle(string title) {

    sortReverse();
    Node1 *temp = head;

    for(int i = 0; i < total; i++){

        if(temp->info.getMTitle() == title){
            return *temp;
        }
        temp = temp->next;

    }

    sort();

}

