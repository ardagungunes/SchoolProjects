#include <iostream>
#include <fstream>
#include <vector>
#include "Helper.h"

using namespace std;

int main(int argc, char **argv) {

    ifstream inputFile(argv[1]);
    ofstream outputFile(argv[2]);

    Node *root = getNode();

    doCommands(root,inputFile,outputFile);




    inputFile.close();
    outputFile.close();

    return 0;
}
