//
// Created by ardab on 22.10.2021.
//

#ifndef ASSIGNMENT_1_PART2_H
#define ASSIGNMENT_1_PART2_H

#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <fstream>


int  grid2_size(std::ifstream &inputFile){
    int grid_size;

    std::string size;

    getline(inputFile,size);

    grid_size = std::stoi(size);


    return grid_size;


}

void print_board(int grid_size, int **arr, std::ofstream &outputFile){
    for(int i = 0; i < grid_size; i++){
        for(int j = 0; j < grid_size; j++){
            outputFile << arr[i][j] << " ";
        }
        outputFile<<std::endl;
    }
}

void create_board(std::ifstream &inputFile, int grid_size, int **arr){
    //std::vector<int> vec;
    std::string val;
    std::string line;

    for(int i = 0; i < grid_size; i++){
        std::vector<int> vec;
        getline(inputFile, line);
        std::stringstream str(line);
        while(getline(str, val, ' ')){
            vec.push_back(stoi(val));
        }

        for(int j = 0; j < grid_size; j++){
            arr[i][j] = vec.at(j);
        }
    }





}

//This function places the bomb according to "input file2" and calculates the total points.
int placing_bombs(std::ifstream &inputFile, int grid_size, int **arr){
    int total_points {0};
    std::string val;
    std::string line;

   while(getline(inputFile, line)){
       int row;
       int column;
       std::vector<int> vec;
       std::stringstream str(line);


       while(getline(str, val, ' ')){
           vec.push_back(std::stoi(val));

       }


       row = vec.at(0);
       column = vec.at(1);

       int pointOfBalloon = arr[row][column];
       total_points += pointOfBalloon;
       arr[row][column] = 0;

       for(int i = 0; i < grid_size; i++){
           if(arr[row][i] == pointOfBalloon){
               total_points += pointOfBalloon;
               arr[row][i] = 0;
           }

           if(arr[i][column] == pointOfBalloon){
               total_points += pointOfBalloon;
               arr[i][column] = 0;
           }
       }

       // These while loops checking for diagonal elements.


       int cur_row = row;
       int cur_column = column;

    while (true){
        cur_row+=1;
        cur_column+=1;
        if(cur_row >= 0 && cur_row < grid_size && cur_column >= 0 && cur_column < grid_size){
            if(arr[cur_row][cur_column] == pointOfBalloon){
                total_points += pointOfBalloon;
                arr[cur_row][cur_column] = 0;
            }
        }

        else{
            break;
        }
    }

       cur_row = row;
       cur_column = column;
       while (true){
           cur_row-=1;
           cur_column-=1;
           if(cur_row >= 0 && cur_row < grid_size && cur_column >= 0 && cur_column < grid_size){
               if(arr[cur_row][cur_column] == pointOfBalloon){
                   total_points += pointOfBalloon;
                   arr[cur_row][cur_column] = 0;
               }
           }

           else{
               break;
           }
       }

       cur_row = row;
       cur_column = column;

       while (true){
           cur_row+=1;
           cur_column-=1;
           if(cur_row >= 0 && cur_row < grid_size && cur_column >= 0 && cur_column < grid_size){
               if(arr[cur_row][cur_column] == pointOfBalloon){
                   total_points += pointOfBalloon;
                   arr[cur_row][cur_column] = 0;
               }
           }

           else{
               break;
           }
       }

       cur_row = row;
       cur_column = column;

       while (true){
           cur_row-=1;
           cur_column+=1;
           if(cur_row >= 0 && cur_row < grid_size && cur_column >= 0 && cur_column < grid_size){
               if(arr[cur_row][cur_column] == pointOfBalloon){
                   total_points += pointOfBalloon;
                   arr[cur_row][cur_column] = 0;
               }
           }

           else{
               break;
           }
       }

   }

   return total_points;
}



#endif //ASSIGNMENT_1_PART2_H
