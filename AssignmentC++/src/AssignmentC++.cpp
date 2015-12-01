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

const string FILE_LOCATION = "location.txt";
const string USERNAME = "admin";
const string PASSWORD = "pass";

struct location{
	string city;
	int x;
	int y;
};

bool isNumber(string s);
list<location> readLocationFile();
void printList(list<location> locs);
list<location> addCity(string city,int x ,int y);
void saveList(list<location> locs);
list<location> printPreffered(list<location> locs);
double getDistance(location loc1, location loc2);
void printTravelRoute(list<location> locs);
void printDistanceWithMinMax(list<location> journey);
bool auth();

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
		cout << "z. Exit" << endl;
		cout << ">> ";
		cin >> choice;
		cout << endl;

		if(choice == "a"){
			// a loop if the user wants to select another travel route
			while(temp != "z"){
				// a loop if the user stil lwants to select a city
				while(choice != "0"){
					cout << ">> Please choose a city" << endl;
					// Display each city and their number
					for (list<location>::iterator it=locations.begin(); it != locations.end(); ++it){
						cout << distance(locations.begin(), it) + 1<< " for " << it->city << endl ;
					}

					cout << "0 for end of route planning" << endl;
					cout << ">> " ;
					cin >> choice;

					// Check weather the user type a non integer or number that is out of range
					if(!(isNumber(choice))){
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

				cout << endl;
				// if the user choose 1 or no city then go to the main menu
				if(journey.size() == 0 || journey.size() == 1){
					break;
				}

				// print the names of the chosen location at the top
				cout << endl << "\t\t" ;
				for (list<location>::iterator it=journey.begin(); it != journey.end(); ++it){
					cout << setfill('-') << setw(10) << left << it->city;
				}
				cout << endl ;

				// print the distance and their min and max
				printDistanceWithMinMax(locations);
				// print the travel route
				printTravelRoute(journey);

				cout << endl << "Suggested Travel Route : " << endl << endl;
				journey = printPreffered(journey);
				// print the preffered travel route
				printTravelRoute(journey);

				cout << endl << "a. Choose another List";
				cout << endl << "z. Main menu" << endl << ">> ";
				cin >> temp;

				// clear the list for the next travel
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
			if(!auth()) continue;
			cout << "Authentication Succesful!" << endl << endl;
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
			if(!auth()) continue;
			cout << "Authentication Succesful!" << endl << endl;
			// Display each city and their number
			for (list<location>::iterator it=locations.begin(); it != locations.end(); ++it){
				cout << distance(locations.begin(), it) + 1<< " for " << it->city << endl ;
			}
			cout << "0 to cancel" << endl;
			cout << ">> " ;
			cin >> choice;
			// Check weather the user type a non integer or number that is out of range
			if(!(isNumber(choice))){
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
			cout << endl;
		}else if(choice == "e"){
			int max = 0;
			location loc1;
			location loc2;
			int d = 0;
			for (list<location>::iterator it=locations.begin(); it != locations.end(); ++it){
				for (list<location>::iterator it1=locations.begin(); it1 != locations.end(); ++it1){
						if(it != it1){
							d = floor(getDistance(*it,*it1));
							if(d > max){
								max = d;
								loc1 = *it;
								loc2 = *it1;
							}
						}
				}
			}

			cout << loc1.city << " -> " << d << " -> " << loc2.city << endl << endl;
		}else if(choice == "z"){
			return 0;
		}
	}
	return 0;
}

// Read the file and create an array of location
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

// print the list of cities
void printList(list<location> locs){
	for (list<location>::iterator it=locs.begin(); it != locs.end(); ++it){
		cout << "--------------------------------------" << endl ;
		cout << "City : " << it->city << endl;
		cout << "X : " << it->x << endl;
		cout << "Y : " << it->y << endl;
	}
	cout << "--------------------------------------" << endl << endl;
}

// add a city in the file
list<location> addCity(string city,int x ,int y){
	ofstream myfile;
	  myfile.open (FILE_LOCATION, ios::app);
	  myfile << "\n" << city << "\n";
	  myfile << x << "\n";
	  myfile << y ;
	  myfile.close();
	  return readLocationFile();
}

// save the list of locations into the file
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

list<location> printPreffered(list<location> locs){
	int low = 0;
	list<location> pref;
	list<location>::iterator temp;
	list<location>::iterator lowest;
	while (!locs.empty()){
		if(locs.size() == 2){
			pref.push_back(locs.front());
			pref.push_back(locs.back());
			break;
		}
		pref.push_front(locs.front());
		temp = pref.begin();
		locs.pop_front();
		int low = floor(getDistance(*temp, *locs.begin()));
		lowest = locs.begin();
		for (list<location>::iterator it=locs.begin(); it != locs.end(); ++it){
			int d = floor(getDistance(*temp, *it));
			if(d < low){
				low = d;
				lowest = it;
			}
		}
		locs.erase(lowest);
		locs.push_front(*lowest);
	}
	return pref;
}

// Check if a string is a number
// @ http://stackoverflow.com/questions/5655142/how-to-check-if-input-is-numeric-in-c
bool isNumber(string s )
{
  bool hitDecimal=0;
  for( char c : s )
  {
    if( c=='.' && !hitDecimal ) // 2 '.' in string mean invalid
      hitDecimal=1; // first hit here, we forgive and skip
    else if( !isdigit( c ) )
      return 0 ; // not ., not
  }
  return 1 ;
}

// Get the distance of two location
double getDistance(location loc1, location loc2){
	return sqrt(pow(loc1.x - loc2.x,2) + pow(loc1.y - loc2.y,2));
}

// print the travel route
void printTravelRoute(list<location> journey){
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
}

// print the distance and min and max
void printDistanceWithMinMax(list<location> journey){
	// the initial min is the distance of the first 2 city
	int min = floor(getDistance(*journey.begin(),*next(journey.begin(), 1)));
	int max = 0;
	// print the current location and its distance to other city
	for (list<location>::iterator it=journey.begin(); it != journey.end(); ++it){
		cout <<  setfill('-') << setw(16)<< it->city ;
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
			cout << setfill('-') <<  setw(10) << d;
		}
		cout << endl;
	}

	cout << endl << "Min : " << min << endl << "Max : " << max << endl << endl;
}

// Authenticate if the username and password match it will return true
bool auth(){
	string user, pass;
	cout << "User >> ";
	cin >> user;
	cout << "Pass >> ";
	cin >> pass;
	cout << endl;
	if(user != USERNAME || pass != PASSWORD){
		cout << endl << "Invalid Username or password" << endl;
		return false;
	}
	return true;
}
