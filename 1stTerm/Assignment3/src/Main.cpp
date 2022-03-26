#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <sstream>
#include <iterator>
#include <map>
#include <stack>
#include <fstream>
#include "Helper.h"

using namespace std;



int main(int argc, char *argv[]) {

    TransitionRule transitionRule1;


    vector<string> inputAlphabet;
    vector<string> stackAlphabet;
    vector<string> finalStates;
    vector<string> states;
    vector<TransitionRule> myTransitions;


    //map<int, int> mapp;
    //mapp.insert(pair<int ,int>(1 ,2 ));
    //mapp.at(1) = 6;
    //cout<<mapp.at(1)<<endl;

    map<string, vector<TransitionRule>> myMap;

    string startingState;

    ofstream output(argv[3]);
    ifstream dpda(argv[1]);
    ifstream dpda_input(argv[2]);



    generateStates(dpda,states,finalStates,startingState);
    generateInputStackAlphabet(dpda,inputAlphabet,stackAlphabet);
    fillMap(myMap,states);
    if(generateTransitionRules(dpda,myMap,states) && checkInputs(myMap,inputAlphabet) && checkSymbols(myMap,stackAlphabet) &&
                                                                                         checkSymbols2(myMap, stackAlphabet)){

        readInputLines(dpda_input,myMap,startingState,output,finalStates);

    }else{

        output << "Error [1]:DPDA description is invalid!";

    }

    /*if(checkInputs(myMap,inputAlphabet)){

        cout <<"Arda" <<endl;

    }
     */













dpda.close();
dpda_input.close();
output.close();


















    /*
    map<int, vector<int>> dct;

    dct.insert(pair<int,vector<int>>(1,{1,2,3}));
    stack<int> arda;
    arda.push(1);
    arda.push(2);
    cout<< arda.top() << endl;

    auto itr = dct.find(1);

    cout<<itr->first << " " <<itr->second[0]<<endl;


    vector<string> myVec;

    string myStr = "q0,q1,q2,q3,q4 => (q0), [q0], [q1]";
    cout<<myStr<<endl;
    myStr.erase(remove(myStr.begin(), myStr.end() , ' '), myStr.end());

    stringstream streamData(myStr);

    string value;

    while(getline(streamData, value, '=')){

        myVec.push_back(value);

    }
    myVec[1].erase(remove(myVec[1].begin(), myVec[1].end(), '>'), myVec[1].end());

    for(auto & i : myVec){
        cout << i << endl;
    }

    cout<<myStr<<endl;
    */


    return 0;
}
