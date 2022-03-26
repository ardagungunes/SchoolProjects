//
// Created by ardab on 25.11.2021.
//

#ifndef ASSIGNMENT2_HELPER_H
#define ASSIGNMENT2_HELPER_H

#include <iostream>
#include <string>
#include "TemporaryEmployee.h"
#include "PermanentEmployee.h"
#include "CircularArrayLinkedList.h"
#include "DoubleDynamicLinkedList.h"

using namespace std;

void userChoices(){

    cout<<"____ Employee Recording System ____"<<endl;
    cout<<"Please select from the following Menu Operation:"<<endl;
    cout<<"1) Appointment of a new employee"<<endl;
    cout<<"2) Appointment of a transferred employee"<<endl;
    cout<<"3) Updating the title and salary coefficient of an employee"<<endl;
    cout<<"4) Deletion of an employee"<<endl;
    cout<<"5) Listing the information of an employee"<<endl;
    cout<<"6) Listing employees ordered by employee number"<<endl;
    cout<<"7) Listing employees ordered by appointment date"<<endl;
    cout<<"8) Listing employees appointed after a certain date"<<endl;
    cout<<"9) Listing employees assigned in a given year"<<endl;
    cout<<"10) Listing employees born before a certain date"<<endl;
    cout<<"11) Listing employees born in a particular month"<<endl;
    cout<<"12) Listing the information of the last assigned employee with a given title"<<endl;

}

void convertDate(int &day1, int &month1, int &year1, int &day2, int &month2, int &year2, string &s_day1,string &s_month1, string &s_year1,
                 string &s_day2, string &s_month2, string &s_year2,string birth_date, string appointment_date);

void convertDate(int &day, int &month, int &year, string &s_day,string &s_month,string &s_year,string &date){

    s_day = date.substr(0,2);
    s_month = date.substr(3,5);
    s_year = date.substr(6,10);



    if(s_day.substr(0,1) == "0"){
        day = stoi(s_day.substr(1,2));
    }else{
        day = stoi(s_day.substr(0,2));
    }
    if(s_month.substr(0,1) == "0"){
        month = stoi(s_month.substr(1,2));
    }else{
        month = stoi(s_month.substr(0,2));
    }

    year = stoi(s_year);

}


void choiceOne(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int employeeNum;
    int employeeType;
    string name;
    string surname;
    string title;
    double coefficient;
    string birth_date;
    string appointment_date;

    //For Date class

    int day1;
    int month1;
    int year1;

    int day2;
    int month2;
    int year2;

    string s_day1;
    string s_month1;
    string s_year1;


    string s_day2;
    string s_month2;
    string s_year2;



    cout<<"Please type the employee number"<<endl;
    cin>>employeeNum;

    if(circularArrayLinkedList.findEmployee(employeeNum) > -1 || doubleDynamicLinkedList.findEmployee(employeeNum)){
        cout << "There is already an employee with this number"<<endl;
        return;
    }
        cout<<"Type the employee type"<<endl;
        cin>>employeeType;

        cin.ignore();

        cout <<"Type the Name"<<endl;
        getline(cin,name);


        cout << "Type the Surname"<<endl;
        getline(cin,surname);


        cout<<"Type title"<<endl;
        getline(cin,title);


        cout << "Type salary coefficient"<<endl;
        cin>>coefficient;

        cout << "Type birth date"<<endl;
        cin>>birth_date;

        cout << "Type appointment date"<<endl;
        cin>>appointment_date;

        convertDate(day1,month1,year1,day2,month2,year2,s_day1,s_month1,s_year1,s_day2,s_month2,s_year2,birth_date,appointment_date);

        if(employeeType == 0){
            circularArrayLinkedList.add(TemporaryEmployee(employeeNum,employeeType,name,surname,title,coefficient,Date(day1,month1,year1),Date(day2,month2,year2)));
        }else{
            doubleDynamicLinkedList.push(PermanentEmployee(employeeNum,employeeType,name,surname,title,coefficient,Date(day1,month1,year1),Date(day2,month2,year2)));
        }



    }

    void choiceTwo(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

        int employeeNum;
        int employeeType;
        string name;
        string surname;
        string title;
        double coefficient;
        string birth_date;
        string appointment_date;
        int length_of_service;

        //For Date class

        int day1;
        int month1;
        int year1;

        int day2;
        int month2;
        int year2;

        string s_day1;
        string s_month1;
        string s_year1;


        string s_day2;
        string s_month2;
        string s_year2;

        cout<<"Please type the employee number"<<endl;
        cin>>employeeNum;

        if(circularArrayLinkedList.findEmployee(employeeNum) > -1 || doubleDynamicLinkedList.findEmployee(employeeNum)){
            cout << "There is already an employee with this number"<<endl;
            return;
        }
        cout<<"Type the employee type"<<endl;
        cin>>employeeType;

        cin.ignore();

        cout <<"Type the Name"<<endl;
        getline(cin,name);
        //cout<<endl;

        cout << "Type the Surname"<<endl;
        getline(cin,surname);
        //cout<<endl;

        cout<<"Type title"<<endl;
        getline(cin,title);
        //cout<<endl;

        cout << "Type salary coefficient"<<endl;
        cin>>coefficient;

        cout << "Type birth date"<<endl;
        cin>>birth_date;

        cout << "Type appointment date"<<endl;
        cin>>appointment_date;

        cout << "Type the length of service days"<<endl;
        cin>>length_of_service;

        convertDate(day1,month1,year1,day2,month2,year2,s_day1,s_month1,s_year1,s_day2,s_month2,s_year2,birth_date,appointment_date);

        if(employeeType == 0){
            circularArrayLinkedList.add(TemporaryEmployee(employeeNum,employeeType,name,surname,title,coefficient,Date(day1,month1,year1),Date(day2,month2,year2),length_of_service));
        }else{
            doubleDynamicLinkedList.push(PermanentEmployee(employeeNum,employeeType,name,surname,title,coefficient,Date(day1,month1,year1),Date(day2,month2,year2),length_of_service));
        }


}


void choiceThree(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int employeeNum;
    string new_title;
    double new_coefficient;

    cout<<"Please Type the employee number"<<endl;
    cin>>employeeNum;
	cin.ignore();

    if(circularArrayLinkedList.findEmployee(employeeNum) == -1 && !doubleDynamicLinkedList.findEmployee(employeeNum)){
        cout<<"There isn't an employee with this number"<<endl;
        return;
    }

    cout<<"Please type the new title"<<endl;
    getline(cin,new_title);

    cout<<"Please type the new salary coefficient"<<endl;
    cin>>new_coefficient;

    if(circularArrayLinkedList.findEmployee(employeeNum) > -1){


        circularArrayLinkedList.changeTitleSalary(employeeNum,new_title,new_coefficient);

    }else if(doubleDynamicLinkedList.findEmployee(employeeNum)){
        doubleDynamicLinkedList.update(employeeNum,new_title,new_coefficient);
    }

}

void choiceFour(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int employeeNum;

    cout<<"Please Type the employee number"<<endl;
    cin>>employeeNum;

    if(circularArrayLinkedList.findEmployee(employeeNum) == -1 && !doubleDynamicLinkedList.findEmployee(employeeNum)){
        cout<<"There isn't an employee with this number"<<endl;
        return;
    }

    if(circularArrayLinkedList.findEmployee(employeeNum) > -1){
        circularArrayLinkedList.discardEmployee(employeeNum);
    }
    else if(doubleDynamicLinkedList.findEmployee(employeeNum)){
        doubleDynamicLinkedList.deleteEmployee(employeeNum);
    }

    cout<<"Deletion process performed"<<endl;


}

void choiceFive(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int employeeNum;

    cout<<"Please Type the employee number"<<endl;
    cin>>employeeNum;

    if(circularArrayLinkedList.findEmployee(employeeNum) == -1 && !doubleDynamicLinkedList.findEmployee(employeeNum)){
        cout<<"There isn't an employee with this number"<<endl;
        return;
    }

    if(circularArrayLinkedList.findEmployee(employeeNum) > -1){
        circularArrayLinkedList.printInformation(employeeNum);
    }
    else if(doubleDynamicLinkedList.findEmployee(employeeNum)){
        doubleDynamicLinkedList.printInformation(employeeNum);
    }

}

void choiceSix(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList  &doubleDynamicLinkedList){

    circularArrayLinkedList.printSortedSix(doubleDynamicLinkedList);

}

void choiceSeven(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    circularArrayLinkedList.printSortedSeven(doubleDynamicLinkedList);

}

void choiceEight(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){
    int day;
    int month;
    int year;

    string s_day;
    string s_month;
    string s_year;
    string date;

    cout<<"Please Type the date"<<endl;
    cin>>date;

    convertDate(day,month,year,s_day,s_month,s_year,date);

    Date dt(day,month,year);

    circularArrayLinkedList.printSortedEight(doubleDynamicLinkedList,dt);

}

void choiceNine(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int year;

    cout<<"Please type the year"<<endl;
    cin>>year;

    circularArrayLinkedList.sortedNine(doubleDynamicLinkedList,year);

}

void choiceTen(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int day;
    int month;
    int year;

    string s_day;
    string s_month;
    string s_year;
    string date;

    cout<<"Please Type the date"<<endl;
    cin>>date;

    convertDate(day,month,year,s_day,s_month,s_year,date);

    Date dt(day,month,year);

    circularArrayLinkedList.sortedTen(doubleDynamicLinkedList,dt);

}

void choiceEleven(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    int month;

    cout<<"Please type the month"<<endl;
    cin>>month;

    circularArrayLinkedList.sortedEleven(doubleDynamicLinkedList,month);


}

void choiceTwelve(CircularArrayLinkedList &circularArrayLinkedList, DoubleDynamicLinkedList &doubleDynamicLinkedList){

    string title;

    cout<<"Please type the title"<<endl;
    cin>>title;

    circularArrayLinkedList.printTwelve(doubleDynamicLinkedList,title);


}



void convertDate(int &day1, int &month1, int &year1, int &day2, int &month2, int &year2, string &s_day1,string &s_month1, string &s_year1,
                 string &s_day2, string &s_month2, string &s_year2,string birth_date, string appointment_date){

    s_day1 = birth_date.substr(0,2);
    s_month1 = birth_date.substr(3,5);
    s_year1 = birth_date.substr(6,10);

    s_day2 = appointment_date.substr(0,2);
    s_month2 = appointment_date.substr(3,5);
    s_year2 = appointment_date.substr(6,10);

    if(s_day1.substr(0,1) == "0"){
        day1 = stoi(s_day1.substr(1,2));
    }else{
        day1 = stoi(s_day1.substr(0,2));
    }
    if(s_month1.substr(0,1) == "0"){
        month1 = stoi(s_month1.substr(1,2));
    }else{
        month1 = stoi(s_month1.substr(0,2));
    }

    year1 = stoi(s_year1);

    if(s_day2.substr(0,1) == "0"){
        day2 = stoi(s_day2.substr(1,2));
    }else{
        day2 = stoi(s_day2.substr(0,2));
    }
    if(s_month2.substr(0,1) == "0"){
        month2 = stoi(s_month2.substr(1,2));
    }else{
        month2 = stoi(s_month2.substr(0,2));
    }

    year2 = stoi(s_year2);



}

#endif //ASSIGNMENT2_HELPER_H
