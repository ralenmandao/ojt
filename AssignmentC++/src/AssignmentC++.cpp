//============================================================================
// Name        : AssignmentC++.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <list>
#include <math.h>
#include <iomanip>

using namespace std;

const string FILE_LOCATION = "src/location.txt";

struct location{
	string city;
	int x;
	int y;
};

bool is_digits(string str);
list<location> readLocationFile();
void printList(list<location> locs);
list<location> addCity(string city,int x ,int y);
void saveList(list<location> locs);
/*
 * get the distance of two location
 */
double getDistance(location loc1, location loc2){
	return sqrt(pow(loc1.x - loc2.x,2) + pow(loc1.y - loc2.y,2));
}

int main() {
	// load all the locations from the location.txt
	list<location> locations = readLocationFile();
	// a list of locations that the user choose
	list<location> journey;
	string choice = "" ;
	string temp = "";
	while(choice != "exit"){
		cout << "a. Travel Route" << endl;
		cout << "b. Print City List" << endl;
		cout << "c. Add City" << endl;
		cout << "d. Remove City" << endl;
		cout << "e. Find two cities with the maximum distance between them" << endl;
		cout << ">> ";
		cin >> choice;
		cout << endl;

		if(choice == "a"){
			while(temp != "z"){
				while(choice != "0"){
					cout << ">> Please choose from which city you wish to start your travel; enter" << endl;
					// Display each city and their number
					for (list<location>::iterator it=locations.begin(); it != locations.end(); ++it){
						cout << distance(locations.begin(), it) + 1<< " for " << it->city << endl ;
					}

					cout << "0 for end of route planning" << endl;
					cout << ">> " ;
					cin >> choice;

					// Check weather the user type a non integer or number that is out of range
					if(!(is_digits(choice))){
						cout << "Wrong input try again" << endl ;
					}else{
						int c = stoi(choice);
						if(((c - 1) >= locations.size() && (c - 1) < 0) && c != 0){
							cout << "Invalid location" << endl ;
						}else if(c != 0){
							// add the chosen city to the journey list
							journey.push_back(*next(locations.begin(), c - 1));
							cout << endl ;
						}
					}
				}
				// if the user choose 1 or no city then go to the main menu
				if(journey.size() == 0 || journey.size() == 1){
					break;
				}

				// the initial min is the distance of the first 2 city
				int min = floor(getDistance(*journey.begin(),*next(journey.begin(), 1)));
				int max = 0;
				// print the names of the chosen location at the top
				cout << endl << "\t" ;
				for (list<location>::iterator it=journey.begin(); it != journey.end(); ++it){
					cout << "\t" << it->city << "\t";
				}
				cout << endl ;

				// print the current location and its distance to other city
				for (list<location>::iterator it=journey.begin(); it != journey.end(); ++it){
					cout << it->city ;
					for(list<location>::iterator it1=journey.begin(); it1 != journey.end(); ++it1){
						int d = floor(getDistance(*it, *it1));
						if(it != it1){
							if(min > d){
								min = d;
							}
							if(max < d){
								max = d;
							}
						}
						cout << "\t\t" << d;
					}
					cout << endl;
				}

				cout << endl << "Min : " << min;
				cout << endl << "Max : " << max;

				cout << endl << endl;
				int total = 0;
				for(int x = 0; x < journey.size() ; x++){
					int d = 0;
					if(x == journey.size() - 1){
						d = floor(getDistance(*next(journey.begin(), x), *journey.begin()));
						cout << next(journey.begin(), x)->city << " -> " << d;
						total += d;
					}else{
						d = floor(getDistance(*next(journey.begin(), x), *next(journey.begin(), x + 1)));
						cout << next(journey.begin(), x)->city << " -> " << d << " -> ";
						total += d;
					}
				}

				cout << endl << "Total travel distance: " << total << endl;
				cout << endl << "a. Choose another List";
				cout << endl << "z. Main menu" << endl << ">> ";
				cin >> temp;

				journey.clear();
				cout << endl;

				if(temp != "a"){
					break;
				}else{
					choice = "";
				}
			}

		}else if(choice == "b"){
			printList(locations);
		}else if(choice == "c"){
			string city;
			int x = 0;
			int y = 0;
			cout << "City >> ";
			cin >> city;
			cout << "X >> ";
			cin >> x;
			cout << "Y >> ";
			cin >> y;
			cout << city << " was added!" << endl << endl;
 			locations = addCity(city,x,y);
		}else if(choice == "d"){
			// Display each city and their number
			for (list<location>::iterator it=locations.begin(); it != locations.end(); ++it){
				cout << distance(locations.begin(), it) + 1<< " for " << it->city << endl ;
			}
			cout << "0 to cancel" << endl;
			cout << ">> " ;
			cin >> choice;
			// Check weather the user type a non integer or number that is out of range
			if(!(is_digits(choice))){
				cout << "Wrong input try again" << endl ;
			}else{
				int c = stoi(choice);
				if(((c - 1) >= locations.size() && (c - 1) < 0) && c != 0){
					cout << "Invalid location" << endl ;
				}else if(c != 0){
					locations.erase(next(locations.begin(), c - 1));
					saveList(locations);
					cout << endl ;
				}
			}
		}
	}
	return 0;
}

/*
 * Read the file and create an array of location
 */
list<location> readLocationFile(){
	ifstream infile(FILE_LOCATION);
	string line;
	list<location> locations;
	location temp;
	while(getline(infile, line)){
		temp.city = line;
		getline(infile, line);
		temp.x = stoi(line);
		getline(infile, line);
		temp.y = stoi(line);
		locations.push_back(temp);
	}
	return locations;
}
// Check whether str can be parse to int
bool is_digits(string str){
    return std::all_of(str.begin(), str.end(), ::isdigit);
}

void printList(list<location> locs){
	for (list<location>::iterator it=locs.begin(); it != locs.end(); ++it){
		cout << "--------------------------------------" << endl ;
		cout << "City : " << it->city << endl;
		cout << "X : " << it->x << endl;
		cout << "Y : " << it->y << endl;
	}
	cout << "--------------------------------------" << endl << endl;
}

list<location> addCity(string city,int x ,int y){
	ofstream myfile;
	  myfile.open (FILE_LOCATION, ios::app);
	  myfile << "\n" << city << "\n";
	  myfile << x << "\n";
	  myfile << y ;
	  myfile.close();
	  return readLocationFile();
}

void saveList(list<location> locs){
	ofstream myfile;
	myfile.open (FILE_LOCATION);
	for (list<location>::iterator it=locs.begin(); it != locs.end(); ++it){
		myfile << it->city << "\n";
		myfile << it->x << "\n";
		myfile << it->y << "\n" ;
	}
	myfile.close();
}
