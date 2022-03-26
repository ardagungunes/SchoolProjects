//
// Created by ardab on 26.11.2021.
//
#include "TemporaryEmployee.h"
#include <iostream>


TemporaryEmployee::TemporaryEmployee() {

}

TemporaryEmployee::TemporaryEmployee(int mEmployeeNumber, int mEmployeeType, const string &mName,
                                     const string &mSurname, const string &mTitle, double mSalaryCoefficient,
                                     const Date &mDateOfBirth, const Date &mDateOfAppointment) : m_employee_number(
        mEmployeeNumber), m_employee_type(mEmployeeType), m_name(mName), m_surname(mSurname), m_title(mTitle),
                                                                                                 m_salary_coefficient(
                                                                                                         mSalaryCoefficient),
                                                                                                 m_date_of_birth(
                                                                                                         mDateOfBirth),
                                                                                                 m_date_of_appointment(
                                                                                                         mDateOfAppointment) {
    m_length_of_service = 0;

}

void TemporaryEmployee::update(double salary, string title) {
m_salary_coefficient = salary;
m_title = title;
}

TemporaryEmployee::TemporaryEmployee(int mEmployeeNumber, int mEmployeeType, const string &mName,
                                     const string &mSurname, const string &mTitle, double mSalaryCoefficient,
                                     const Date &mDateOfBirth, const Date &mDateOfAppointment, int mLengthOfService)
        : m_employee_number(mEmployeeNumber), m_employee_type(mEmployeeType), m_name(mName), m_surname(mSurname),
          m_title(mTitle), m_salary_coefficient(mSalaryCoefficient), m_date_of_birth(mDateOfBirth),
          m_date_of_appointment(mDateOfAppointment), m_length_of_service(mLengthOfService) {}


ostream& operator<<(ostream& os, const TemporaryEmployee& te)
{

    os<<"---------------------------Information of an Employee----------------------------"<<std::endl;
    os << "Employee Number : " << te.getMEmployeeNumber() << "\t" << "Employee Type : " << te.getMEmployeeType() << "\t" <<
    "Name and Surname : " << te.getMName() << " " << te.getMSurname() << "\n" << "Title : " << te.getMTitle() << " \t" <<
    "Salary Coefficient : " << te.getMSalaryCoefficient() << "\t" << "Date of Birth : " << te.getMDateOfBirth() << "\n" <<"Date of Appointment : " << te.getMDateOfAppointment() <<
    "\t" << "Length of Service Days : " << te.getMLengthOfService() << std::endl;
    os<<"------------------------------------------------------------------"<<std::endl<<std::endl;


    return os;
}






