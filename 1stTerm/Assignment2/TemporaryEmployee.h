//
// Created by ardab on 24.11.2021.
//

#ifndef ASSIGNMENT2_TEMPORARYEMPLOYEE_H
#define ASSIGNMENT2_TEMPORARYEMPLOYEE_H

#include "Employee.h"
//#include "CircularArrayLinkedList.h"

class TemporaryEmployee  : public Employee
        {

private:

    int m_employee_number;
    int m_employee_type;
    string m_name;
    string m_surname;
    string m_title;
    double m_salary_coefficient;
    Date m_date_of_birth;
    Date m_date_of_appointment;
    int m_length_of_service = 0;



public:
    TemporaryEmployee();

    TemporaryEmployee(int mEmployeeNumber, int mEmployeeType, const string &mName, const string &mSurname,
                      const string &mTitle, double mSalaryCoefficient, const Date &mDateOfBirth,
                      const Date &mDateOfAppointment);

    TemporaryEmployee(int mEmployeeNumber, int mEmployeeType, const string &mName, const string &mSurname,
                      const string &mTitle, double mSalaryCoefficient, const Date &mDateOfBirth,
                      const Date &mDateOfAppointment, int mLengthOfService);


    int getMEmployeeNumber() const {return m_employee_number;}

    void setMEmployeeNumber(int mEmployeeNumber) {m_employee_number = mEmployeeNumber;}

    int getMEmployeeType() const {return m_employee_type;}

    void setMEmployeeType(int mEmployeeType) {m_employee_type = mEmployeeType;}

    const string &getMName() const {return m_name;}

    void setMName(const string &mName) {m_name = mName;}

    const string &getMSurname() const {return m_surname;}

    void setMSurname(const string &mSurname) {m_surname = mSurname;}

    const string &getMTitle() const {return m_title;}

    void setMTitle(const string &mTitle) {m_title = mTitle;}

    double getMSalaryCoefficient() const {return m_salary_coefficient;}

    void setMSalaryCoefficient(double mSalaryCoefficient) {m_salary_coefficient = mSalaryCoefficient;}

    const Date &getMDateOfBirth() const {return m_date_of_birth;}

    void setMDateOfBirth(const Date &mDateOfBirth) {m_date_of_birth = mDateOfBirth;}

    const Date &getMDateOfAppointment() const {return m_date_of_appointment;}

    void setMDateOfAppointment(const Date &mDateOfAppointment) {m_date_of_appointment = mDateOfAppointment;}

    int getMLengthOfService() const {return m_length_of_service;}

    void setMLengthOfService(int mLengthOfService) {m_length_of_service = mLengthOfService;}


    void update(double salary, string title);

   friend ostream& operator<<(ostream& os, const TemporaryEmployee& te);

};


#endif //ASSIGNMENT2_TEMPORARYEMPLOYEE_H
