//
// Created by ardab on 8.12.2021.
//
#include <iostream>
#include <vector>
#include <stack>
#include <string>
#include <sstream>
#include <map>

using namespace std;

struct TransitionRule{

    string current_state;
    string input;
    string popped;
    string next_state;
    string pushed;

};

#ifndef ASSIGNMENT_HELPER_H
#define ASSIGNMENT_HELPER_H


void generateStates(ifstream &file, vector<string> &states, vector<string> &finalStates, string &startingState){
    vector<string> splitted;
    vector<string> secondPart;


    string line;

    getline(file,line);

    line.erase(remove(line.begin(), line.end() , ' '), line.end());
    line.erase(remove(line.begin(), line.end(), 'Q'), line.end());
    line.erase(remove(line.begin(), line.end(), ':'), line.end());
    line.erase(remove(line.begin(), line.end(), '>'), line.end());

    //cout<<line<<endl;

    stringstream streamData(line);

    string value;

    while(getline(streamData,value, '=')){
        splitted.push_back(value);
    }

    stringstream streamData1(splitted[0]);

    string val;

    while(getline(streamData1,val,',')){
        states.push_back(val);
    }

    stringstream streamData2(splitted[1]);

    string vall;

    while (getline(streamData2,vall,',')){
        secondPart.push_back(vall);
    }

    string start;

    for(auto str : secondPart){
        if(*str.begin() == '('){
            start = str;
            start.erase(remove(start.begin(), start.end(), '('), start.end());
            start.erase(remove(start.begin(), start.end(), ')'), start.end());
            startingState = start;
        }else{
            str.erase(remove(str.begin(), str.end(), '['), str.end());
            str.erase(remove(str.begin(), str.end(), ']'), str.end());
            finalStates.push_back(str);
        }
    }
    start.erase(remove(start.begin(), start.end(), '('), start.end());
    start.erase(remove(start.begin(), start.end(), ')'), start.end());

    //cout<<start<<endl;


}

void generateInputStackAlphabet(ifstream &file, vector<string> &inputAlphabet, vector<string> &stackAlphabet){

    string line;
    getline(file, line);

    line.erase(remove(line.begin(),line.end(), 'A'),line.end());
    line.erase(remove(line.begin(),line.end(), ':'),line.end());

    stringstream stringstream1(line);

    string value;

    while(getline(stringstream1,value,',')){
        inputAlphabet.push_back(value);
    }

    string line2;
    getline(file, line2);

    line2.erase(remove(line2.begin(),line2.end(), 'Z'),line2.end());
    line2.erase(remove(line2.begin(),line2.end(), ':'),line2.end());

    stringstream stringstream2(line2);

    string val;

    while (getline(stringstream2,val,',')){
        stackAlphabet.push_back(val);
    }




}

void fillMap(map<string, vector<TransitionRule>> &myMap, vector<string> &states){

    for(int i = 0; i<states.size(); i++){

        myMap.insert(pair<string,vector<TransitionRule>>(states.at(i),{}));

    }

}

bool generateTransitionRules(ifstream &file, map<string, vector<TransitionRule>> &myMap, vector<string> &states){

    string line;

    while(getline(file,line)){

        vector<string> transitionProperties;

        line.erase(remove(line.begin(),line.end(),'T'),line.end());
        line.erase(remove(line.begin(),line.end(),':'),line.end());

        stringstream stringstream1(line);

        string value;

        while(getline(stringstream1,value,',')){
            transitionProperties.push_back(value);
        }

        TransitionRule transitionRule;

        transitionRule.current_state = transitionProperties.at(0);
        transitionRule.input = transitionProperties.at(1);
        transitionRule.popped = transitionProperties.at(2);
        transitionRule.next_state = transitionProperties.at(3);
        transitionRule.pushed = transitionProperties.at(4);

        try {
            myMap.at(transitionProperties.at(0)).push_back(transitionRule);
            myMap.at(transitionRule.next_state);
        }
        catch(...){

            return false;

        }



    }
        return true;
}

bool checkInputs(map<string, vector<TransitionRule>> &myMap, vector<string> &inputAlphabet){


    map<string, vector<TransitionRule>>::iterator itr;
    for(itr = myMap.begin(); itr!=myMap.end(); ++itr){

        int flag = 0;
        for(int i = 0; i < itr->second.size(); i++){

            if(itr->second.at(i).input == "e"){
                flag++;
                continue;
            }

            for(int j = 0; j < inputAlphabet.size(); j++){



                if(itr->second.at(i).input == inputAlphabet.at(j)){
                    flag++;

                }

            }

        }

        if(flag != itr->second.size()){

            return false;
        }

    }

    return true;

}


bool checkSymbols(map<string, vector<TransitionRule>> &myMap, vector<string> &stackAlphabet){


    map<string, vector<TransitionRule>>::iterator itr;
    for(itr = myMap.begin(); itr!=myMap.end(); ++itr){

        int flag = 0;
        for(int i = 0; i < itr->second.size(); i++){

            if(itr->second.at(i).pushed == "e"){
                flag++;
                continue;
            }

            for(int j = 0; j < stackAlphabet.size(); j++){



                if(itr->second.at(i).pushed == stackAlphabet.at(j)){
                    flag++;

                }

            }

        }

        if(flag != itr->second.size()){

            return false;
        }

    }

    return true;


}

bool checkSymbols2(map<string, vector<TransitionRule>> &myMap, vector<string> &stackAlphabet){

    map<string, vector<TransitionRule>>::iterator itr;
    for(itr = myMap.begin(); itr!=myMap.end(); ++itr){

        int flag = 0;
        for(int i = 0; i < itr->second.size(); i++){

            if(itr->second.at(i).popped == "e"){
                flag++;
                continue;
            }

            for(int j = 0; j < stackAlphabet.size(); j++){



                if(itr->second.at(i).popped == stackAlphabet.at(j)){
                    flag++;

                }

            }

        }

        if(flag != itr->second.size()){

            return false;
        }

    }

    return true;

}

void reverseeStack(stack<string> myStack, stack<string> reversedStack, ofstream &outputFile){


    while(!myStack.empty()){
        string top = myStack.top();
        reversedStack.push(top);
        myStack.pop();
    }

    while(!reversedStack.empty()){
        outputFile << reversedStack.top();
        reversedStack.pop();

        if(!reversedStack.empty()){
            outputFile<<",";
        }
    }



}

void readInputLines(ifstream &file, map<string, vector<TransitionRule>> &myMap, string &startingState, ofstream &outputFile, vector<string> &finalStates){



    string line;

    while(getline(file, line)){

        // look at this line after;
        if(line.empty()){

            outputFile<<endl;
            int ccc = 0;
            for(int l = 0; l < finalStates.size(); l++){
                if(finalStates.at(l) == startingState){
                    ccc++;
                    break;
                }
            }
            if(ccc != 0){
            outputFile<<"ACCEPT"<<endl<<endl;}
            else{
                outputFile<<"REJECT"<<endl<<endl;
            }

        }else{
            //cout<<line<<endl;
            vector<string> inputs;

            stringstream stringstream1(line);

            string value;

            while(getline(stringstream1,value,',')){

                inputs.push_back(value);

            }
            stack<string> myStack;
            stack<string> reverseStack;
            //TransitionRule transitionRule;
            string state = startingState;
            int counter = 0;
                while(true) {
                    int flag = 0;
                    //int counter = 0;
                    //string inputCharacter = inputs.at(counter);
                    vector<TransitionRule> vec = myMap.at(state);
                    vector<TransitionRule> available;

                    for (auto item: vec) {


                            //cout<<counter<<endl;
                            if (!myStack.empty()) {
                                if(counter < inputs.size()) {
                                if ((item.input == inputs.at(counter) || item.input == "e") &&
                                    (item.popped == myStack.top() || item.popped == "e")) {
                                    available.push_back(item);
                                    //cout<<"Arda"<<endl;
                                    flag++;


                                }} else {
                                    //cout<<"llll"<<endl;
                                    if (item.input == "e" && (item.popped == myStack.top() || item.popped == "e")) {
                                        available.push_back(item);
                                        flag++;
                                    }
                                }
                            } else {
                                if (counter < inputs.size()) {
                                    if ((item.input == inputs.at(counter) || item.input == "e") &&
                                        (item.popped == "e")) {
                                        available.push_back(item);
                                        //cout<<"arda"<<endl;
                                        flag++;
                                    }
                                }else{
                                    if(item.input == "e" && (item.popped == "e")){
                                        available.push_back(item);
                                        flag++;
                                    }
                                }
                            }






                    }

                    if(flag == 0){

                        if(counter != inputs.size()){
                            //cout<<"Gridimi"<<endl;
                            outputFile << "REJECT"<<endl<<endl;
                        }else{
                            int sum = 0;
                            for(int x = 0; x < finalStates.size(); x++){
                                if(finalStates.at(x) == state && (myStack.empty() || (myStack.size() == 1 && myStack.top() == "$"))){
                                    outputFile << "ACCEPT"<<endl<<endl;
                                    sum++;
                                    break;
                                }
                            }
                            if(sum == 0){
                                //cout<<"Girdimi"<<endl;
                                outputFile<<"REJECT"<<endl<<endl;
                            }
                        }
                        break;
                    }else{
                        int ct = 0;
                        TransitionRule transitionRule;
                        for(auto element : available) {
                            if (counter < inputs.size()){
                                if (!myStack.empty()) {
                                    //cout << counter << endl;
                                    if (element.input == inputs.at(counter) &&
                                        (element.popped == myStack.top() || element.popped == "e")) {
                                        transitionRule = element;
                                        ct++;
                                        counter++;
                                        break;
                                    }
                                } else {
                                    if (element.input == inputs.at(counter) && (element.popped == "e")) {
                                        transitionRule = element;
                                        ct++;
                                        counter++;
                                        break;
                                    }
                                }
                        }
                        }
                        if(ct == 0){
                            for(auto ind : available) {

                                if (!myStack.empty()) {
                                    if (ind.input == "e" && (ind.popped == myStack.top() || ind.popped == "e")) {
                                        transitionRule = ind;
                                        break;
                                    }
                                }else{
                                    if(ind.input == "e" && (ind.popped == "e")){
                                        transitionRule = ind;
                                        break;
                                    }
                                }
                            }
                        }


                        if(transitionRule.popped != "e" && !myStack.empty()){
                            myStack.pop();
                        }
                        if(transitionRule.pushed != "e") {
                            myStack.push(transitionRule.pushed);
                        }

                        outputFile<<transitionRule.current_state<<","<<transitionRule.input<<","<<transitionRule.popped<<" => " << transitionRule.next_state <<","<<
                        transitionRule.pushed <<" [STACK]:";
                        reverseeStack(myStack,reverseStack,outputFile);
                        outputFile<<endl;

                        state = transitionRule.next_state;



                    }
                }

        }

    }



}

#endif //ASSIGNMENT_HELPER_H
