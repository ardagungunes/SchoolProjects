//
// Created by ardab on 25.10.2021.
//

#ifndef ASSIGNMENT_1_PART1_H
#define ASSIGNMENT_1_PART1_H

#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <fstream>

std::vector<std::vector<int>> check_link(int row, int column, int value, int **arr, int grid_size, std::vector<std::vector<int>> &links,std::vector<std::vector<int>> connected);


// initialize the array elements with 0
void initialize (int **arr, int size){
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
            arr[i][j] = 0;
        }
    }
}

void placing_balloons(std:: ifstream &inputFile, int grid_size, int **arr){
    std::string val;
    std::string line;

    while(getline(inputFile, line)){
        int value;
        int row;
        int column;


        std::vector<int> vec;
        std::stringstream str(line);

        while(getline(str, val, ' ')){
            vec.push_back(std::stoi(val));
            //std::cout << val <<" ";
        }

        //std::cout << "\n";

        value = vec.at(0);
        row = vec.at(1);
        column = vec.at(2);
        arr[row][column] = value;





        int copy_value = 0;

        while(copy_value != value) {

            std::vector<std::vector<int>> link = {{row, column}};
            std::vector<std::vector<int>> con; //= {{row,column}};

            std::vector<std::vector<int>> items = check_link(row, column, value, arr, grid_size, link, con);



            if (items.size() >= 3) {
                for (auto item: items) {

                    arr[item[0]][item[1]] = 0;
                }
                arr[row][column] = value + 1;
                copy_value = value;
                value++;
            }

            else{copy_value = value;}

        }






    }

}

std::vector<std::vector<int>> check_link(int row, int column, int value, int **arr, int grid_size, std::vector<std::vector<int>> &links,std::vector<std::vector<int>> connected){
    int cur_row = row;
    int cur_column = column;
    //int check_cur = cur;
    int check_value = value;




    if(row - 1 >= 0){
        std::vector<int> list = {row - 1,column};
        int flag = 0;
        for(auto vec : links){
            if(vec == list){
                flag++;
            }
        }

        if(flag == 0 && arr[row - 1][column] == value){
            links.push_back(list);
            connected.push_back(list);

        }
    }


    if(row + 1 < grid_size){
        std::vector<int> list = {row + 1,column};
        int flag = 0;
        for(auto vec : links){
            if(vec == list){
                flag++;
            }
        }

        if(flag == 0 && arr[row + 1][column] == value){

            links.push_back(list);
            connected.push_back(list);

        }
    }


    if(column - 1 >= 0){
        std::vector<int> list = {row ,column - 1};
        int flag = 0;
        for(auto vec : links){
            if(vec == list){
                flag++;
            }
        }

        if(flag == 0 && arr[row][column - 1] == value){

            links.push_back(list);
            connected.push_back(list);

        }
    }

    if(column + 1 < grid_size){
        std::vector<int> list = {row ,column + 1};
        int flag = 0;
        for(auto vec : links){
            if(vec == list){
                flag++;
            }
        }

        if(flag == 0 && arr[row][column + 1] == value){
            links.push_back(list);
            connected.push_back(list);

        }
    }



        if(connected.size() != 0){
            int r = connected.at(0).at(0);
            int c = connected.at(0).at(1);

            connected.erase(connected.begin());
            check_link(r,c,value,arr,grid_size,links,connected);
        }






    return links;
}


#endif //ASSIGNMENT_1_PART1_H
