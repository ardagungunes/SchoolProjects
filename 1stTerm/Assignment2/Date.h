//
// Created by ardab on 24.11.2021.
//

#ifndef ASSIGNMENT2_DATE_H
#define ASSIGNMENT2_DATE_H

#include <string>
using namespace std;

class Date{

private:
    int m_day;
    int m_month;
    int m_year;
    long numForComparison;

    std::string m_date;


public:
    Date();
    Date(int day, int month, int year);
    bool operator > (const Date &obj) const;
    bool operator < (Date const &obj) const;
    bool operator == (Date const &obj) const;
    bool operator >= (Date const &obj) const;
    bool operator <= (Date const &obj) const;
    friend ostream& operator<<(ostream& os, const Date& dt);

    int getMDay() const;

    int getMMonth() const;

    int getMYear() const;

};

#endif //ASSIGNMENT2_DATE_H
