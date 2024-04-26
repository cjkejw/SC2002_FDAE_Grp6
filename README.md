# SC2002_FDAE_Grp6 
## Fastfood Ordering and Management System (FOMS)

## About 
This is our `first year semester 2 project`. 
We are tasked with writing a Fastfood Ordering and Management System that allows the process of ordering and collecting for customers, and order and staff management for Staff of the Company. 
The goal of the project is to adopt Obect-Oriented Concepts (OOP) to built a `resuable`, `easy to maintain` and `extendable` program to the best of our ability. 

## User Guide 
- src --> Java source codes of our project
- UML --> our UML Class Diagram
  1. `Boundary UML` shows all the `Bounday Classes`
  2. `Controller UML` shows all the `Controller Classes`
  3. `Entity UML` shows all the `Entity Classes`
  4. `Final UML` shows `all the different Classses` together and how they `interact` with one another
- JavaDoc --> our java documentation of everything.
  To access, follow these steps:
  1. `Download` the file
  2. Enter `JavaDoc`
  3. Enter `src`
  4. Enter `JavaDocTest`
  
  From there you may choose to view any package folder to see the documentation of each classes

 
───▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄───
───█▒▒░░░░░░░░░▒▒█───
────█░░█░░░░░█░░█────
─▄▄──█░░░▀█▀░░░█──▄▄─
█░░█─▀▄░░░░░░░▄▀─█░░█
█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█
█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█
█░░║║║╠─║─║─║║║║║╠─░░█
█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█
█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█      Source: https://emojicombos.com/welcome

## OOP Concepts Applied
- Inheritance
- Polymorphism
- Exception Handling
For the in-depth breakdown, please refer to our report.

## Feature of our program
#### Login System 
- Each default password for users is `password`

#### Customer
Customer actions include:
- Browsing menu
- Placing and customizing order
- Making payments
- Tracking order status, and collecting food after order status is updated to "Ready to pick up"

#### Staff
Staff actions include:
- Displaying new orders
- Viewing details of particular order
- Processing orders (updating order status from "Pending" to "Ready to pick up")

#### Manager
Manager actions include:
- All staff actions
- Displaying staff list in the branch supervised by the specific manager
- Adding, editting, or removing menu items, price and availability (menu items and prices may vary among the different branches)

#### Admin
Admin actions include:
- Adding, editting or removing Staff accounts
- Displaying staff list (filtered by branch, role, gender or age range)
- Assigning managers to each branch within the staff quota constraints
- Promoting staff to manager
- Transferring staff/ manager among branches
- Adding/ removing payment method
- Opening/ closing branch

<br>

## Contributors:
### Implementation code: 
- @cjkejw Wang Shi Ying
- @thuvathetuba Thuvaarakesh Kirupara
- @hildil Tio Hilda

#### UML and Architecture:
- @CatilonyZhang Zhang Yichi




