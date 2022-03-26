//
// Created by ardab on 26.12.2021.
//

#ifndef ASSI4_HELPER_H
#define ASSI4_HELPER_H

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <algorithm>

using namespace std;

struct Node {

    Node *nodes[26];
    bool isEnd;
    string val = "-100";

};


// This function creates the node
Node* getNode(){

    Node *node = new Node;

    node->isEnd = false;

    for (int i = 0; i < 26; i++){

        node->nodes[i] = NULL;

    }

    return node;

}

//Inserts given key, value pair into the trie
void insertKey(Node *root, string key, string value, ofstream &outputFile){

Node *curr = root;

    for(int i = 0; i < key.size(); i++){

        int ind = key[i] - 'a';

        if(curr->nodes[ind] == NULL){
            curr->nodes[ind] = getNode();
        }

        curr = curr->nodes[ind];

    }

    if(curr->val != "-100"){

        if(curr->val == value){

             outputFile << "\""<< key << "\"" << " already exist" <<endl;

        }else if(curr->val != value){

            outputFile<< "\""<< key << "\"" << " was updated" <<endl;
        }

    }else{

        outputFile<< "\""<< key << "\"" << " was added" <<endl;

    }

    curr->val = value;
    curr->isEnd = true;

}

//Finds if the given key exist in the trie
void search(Node *root, string key, ofstream &outputFile){

    Node *curr = root;
    int counter = 0;
    int first_check_ind = key[0] -'a';

    if(curr->nodes[first_check_ind] == NULL){

        outputFile <<"no record"<<endl;
        return;

    }

    for(int i = 0; i < key.size(); i++){

        int ind = key[i] - 'a';

        if(curr->nodes[ind] != NULL){
            counter++;
            curr = curr->nodes[ind];
        }
        else{

            break;

        }

    }

    if(counter == key.size()){

        if(curr->isEnd){

            outputFile << "\"" <<  "The english equivalent is " << curr->val << "\"" <<endl;

        }else{

            outputFile << "\"" << "not enough Dothraki word" << "\"" << endl;

        }

    }else if(counter < key.size()){
        outputFile << "\"" <<"incorrect Dothraki word" << "\""<<endl;
    }


}

bool hasChild(Node *node){

    for(int i = 0; i < 26; i++){

        if(node->nodes[i] != NULL){
            return true;
        }

    }
    return false;

}

bool hasAtLeastTwoChild(Node *node){

    int ct = 0;
    for(int i = 0; i < 26; i++){

        if(node->nodes[i] != NULL){
            ct++;
        }

        if(ct == 2){
            return true;
        }

    }
    return false;

}

Node * del(Node *root,string key, int level){


if(level == key.size()){


if(hasChild(root)){

    root->val = "-100";
    root->isEnd = false;

}

if(!hasChild(root)){
    delete(root);
    root = NULL;
}
return root;

}



int ind = key[level] - 'a';
root->nodes[ind] = del(root->nodes[ind],key,level + 1);

if(!hasChild(root) && root->val == "-100"){

    delete(root);
    root = NULL;

}
    return root;
}

void delControl(Node *root,string key, ofstream &outputFile){
    int lvl = 0;
    Node *curr = root;
    int counter = 0;
    int first_check_ind = key[0] -'a';

    if(curr->nodes[first_check_ind] == NULL){

        outputFile <<"no record"<<endl;
        return;

    }

    for(int i = 0; i < key.size(); i++){

        int ind = key[i] - 'a';

        if(curr->nodes[ind] != NULL){
            counter++;
            curr = curr->nodes[ind];
        }
        else{

            break;

        }

    }

    if(counter == key.size()){

        if(curr->isEnd){

            outputFile << "\"" <<  key << "\" "<< "deletion is successful" << endl;
            del(root,key,lvl);

        }else{

            outputFile << "\"" << "not enough Dothraki word" << "\"" << endl;

        }

    }else if(counter < key.size()){
        outputFile << "\"" <<"incorrect Dothraki word" << "\""<<endl;
    }



}
// I used backtracking to print strings.
void list(Node *root,ofstream &outputFile, char key[],int level,int tab_ct){

    if(root == NULL){

        return;
    }
    int flag = 0;

    if(hasAtLeastTwoChild(root) && level != 0){

        for(int x = 0; x < tab_ct; x++){
            outputFile << "\t";
        }
        outputFile<<"-";
       for(int k = 0; k < level; k++){
           outputFile << key[k];

       }

       if(root->isEnd){
           outputFile << "(" << root->val <<")";
           flag++;
       }
       outputFile<<endl;
       tab_ct++;
    }

    if(root->isEnd && flag == 0){
        //cout << tab_ct;
        for(int l = 0; l < tab_ct; l++){

            outputFile<<"\t";
        }
        outputFile<<"-";
        for(int i = 0; i < level; i++){
            outputFile << key[i];
        }
        outputFile << "(" << root->val << ")" << endl;



    }

    for(int j = 0; j < 26; j++){

        if(root->nodes[j] != NULL){
            //cout << i + 'a';
            key[level] = j + 'a';
            list(root->nodes[j],outputFile,key,level + 1,tab_ct);
        }

    }

}


void listControl(Node *root,ofstream &outputFile){
    char *key = new char;
    int lvl = 0;
    int tab_count = 0;
    list(root,outputFile,key,lvl,tab_count);


}

void listCommand(Node *root,ofstream &outputFile){

    listControl(root,outputFile);

}
void insertCommand(Node *root,vector<string> &vec,ofstream &outputFile){


    string str = vec.at(1);
    str.erase(remove(str.begin(),str.end(), ')'),str.end());

    vector<string> myVec;
    stringstream stringstream1(str);

    string variable;

    while(getline(stringstream1,variable,',')){
        myVec.push_back(variable);
    }

    insertKey(root,myVec[0],myVec[1],outputFile);



}
void deleteCommand(Node *root,vector<string> &vec,ofstream &outputFile){

    string str = vec.at(1);
    str.erase(remove(str.begin(),str.end(), ')'),str.end());

    delControl(root,str,outputFile);


}

void searchCommand(Node *root,vector<string> &vec, ofstream &outputFile){

    string str = vec.at(1);
    str.erase(remove(str.begin(),str.end(), ')'),str.end());

    search(root,str,outputFile);

}

void doCommands(Node *root,ifstream &inputFile, ofstream &outputFile){

    string line;

    while(getline(inputFile,line)){

        vector<string> vec;
        stringstream str(line);

        string val;

        if(line.substr(0,1) != "l") {
            while (getline(str, val, '(')) {
                vec.push_back(val);
            }
        }

        if(line.substr(0,1) == "i"){
            insertCommand(root,vec,outputFile);
        }else if(line.substr(0,1) == "d"){
            deleteCommand(root,vec,outputFile);
        }else if(line.substr(0,1) == "s"){
            searchCommand(root,vec,outputFile);
        }else if(line.substr(0,1) == "l"){
            listCommand(root,outputFile);
        }

    }

}



#endif //ASSI4_HELPER_H
