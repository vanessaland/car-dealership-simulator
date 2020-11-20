# car-dealership-simulator
## About
This was my final project for my class, CPS209: Computer Science II. It is a program that simulates a car dealership software. It can simulate the process of a car transaction such as purchases and returns, with transaction IDs, specific sales representatives, and full description of the car type. The car data can be sorted, filtered and shortlisted based on different attributes.

## How it's made
I used object oriented and modular programming in Java. I used the Eclipse IDE to create this. The output and usage of the program is to be done in the terminal. My final mark for this assignment was 100%.

## Features & Commands

### Basic Usage
- You can use this by first compiling the 'CarDealershipSimulator.java' file and running it
- Then you can use these commands to use the program:
   
|Feature |Command|
|---|---|
|Add cars from file to the program|ADD|
|List cars|L|
|List yearly sales|SALES|
|List sales team|TEAM|
|List top sales person|TOPSP|
|List statistics|STATS|
|Quit program|Q|

### Sorting
- You can sort the cars by price, safety rating, and maximum range:

|Sort by|Command|
|---|---|
|Price|SPR|
|Safety Rating|SSR|
|Maximum Range|SMR|

Below is an example of sorting by price:
![sorting-example](https://github.com/vanessaland/car-dealership-simulator/blob/master/output-screenshots/sortprice.png?raw=true)

### Filtering
- You can filter and shortlist the cars by price, electric, all-wheel drive, and clear all filters:

|Filter by|Command|
|---|---|
|Price|FPR|
|Electric|FEL|
|All-wheel drive|FAW|
|Clear Filters|FCL|

Below is an example of filtering and shortlisting only the cars with price between 0 and 55000:
![filtering-example](https://github.com/vanessaland/car-dealership-simulator/blob/master/output-screenshots/filterprice.png?raw=true)

### Transactions
- You can buy or return cars
- **Buy** cars by entering the command with the VIN. The transaction receipt displays the transaction ID, date, type of transaction, salesperson name, VIN, model, and price. There is error handling if you enter the wrong VIN.
- **Return** cars by entering the command with the original purchase transaction. The receipt displays the transaction ID, date, type of transaction, salesperson name, VIN, model, and price. There is error handling if you enter the wrong transaction ID.

|Transaction|Command|
|---|---|
|Buy|BUY|
|Return|RET|

Below is an example of buying vehicle 471:
![buy-example](https://github.com/vanessaland/car-dealership-simulator/blob/master/output-screenshots/filterprice.png?raw=true)

Below is an example of attempting to return an invalid transaction, then returning transaction 26:
![return-example](https://github.com/vanessaland/car-dealership-simulator/blob/master/output-screenshots/return.png?raw=true)

