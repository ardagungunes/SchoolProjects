//
// Created by ardab on 26.11.2021.
//

#include "Date.h"
#include <string>
#include <iostream>

Date::Date() {


}

Date::Date(int day, int month, int year) {

m_day = day;
m_month = month;
m_year = year;

numForComparison = ((m_day) + (12*m_month) + (365*m_year));

}

bool Date::operator>(const Date &obj) const {


    if(numForComparison > obj.numForComparison){
        return true;
    }
    return false;
}

bool Date::operator<(const Date &obj) const {


    if(numForComparison < obj.numForComparison){
        return true;
    }

    return false;

}

bool Date::operator==(const Date &obj) const {


    if(numForComparison == obj.numForComparison){
        return true;
    }
        return false;
}

bool Date::operator>=(const Date &obj) const {

    if(numForComparison >= obj.numForComparison){
        return true;
    }

    return false;

}

bool Date::operator<=(const Date &obj) const {

    if(numForComparison <= obj.numForComparison){
        return true;
    }

    return false;

}

ostream& operator<<(ostream& os, const Date& dt)
{
    os << dt.m_day << "-" << dt.m_month << "-" << dt.m_year;
    return os;
}

int Date::getMDay() const {
    return m_day;
}

int Date::getMMonth() const {
    return m_month;
}

int Date::getMYear() const {
    return m_year;
}



