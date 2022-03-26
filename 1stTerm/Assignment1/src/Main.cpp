#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>

#include "Part1.h"
#include "Part2.h"

using namespace std;

int main(int argc, char* argv[]) {

    // output file

    ofstream outputFile(argv[3]);

    //For Part1

    ifstream Input1(argv[1]);

    int grid_size1 = grid2_size(Input1);

    int **grid1 = new int*[grid_size1];

    for(int x = 0; x < grid_size1; x++){
        grid1[x] = new int[grid_size1];
    }

    initialize(grid1, grid_size1);


    placing_balloons(Input1,grid_size1,grid1);
    outputFile << "PART 1: \n";
    print_board(grid_size1, grid1,outputFile);




    //For Part2

    ifstream Input2(argv[2]);

    int grid_size = grid2_size(Input2);

    int **arr = new  int*[grid_size];

    for(int i = 0; i < grid_size; i++){
        arr[i] = new int[grid_size];
    }



    create_board(Input2,grid_size, arr);
    int total_points = placing_bombs(Input2,grid_size,arr);
    outputFile << "\n";
    outputFile << "PART 2:" << "\n";
    print_board(grid_size,arr,outputFile);
    outputFile << "Final Point : " << total_points << "p";



Input1.close();
Input2.close();
outputFile.close();

    return 0;
}
