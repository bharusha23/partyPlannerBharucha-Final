# partyPlannerBharucha-Final
AP Computer Science Project

This program manages a seating arrangement for a conference dinner between employees of different companies. Since this conference dinner is intended to be a mixer for professionals to share experiences, the program they need will arrange attendees at 10 tables, with 10 people per table. Each table can only have one employee from each company. This program should be resuable for other events.

This program contains these functionalities: It can upload a file of people that contains a person's last name, their first name, and the number that corresponds to what company they work for. It allows the user to upload more guests individually. It then checks that there aren't more people from one company then there are tables to put them out. It then checks that there aren't more people than there is space for in total. It then places people into tables such that no two people from the same company are at the same table. Next, it prints out a roster list of all the guests, separated first by table and then by company. Finally, it provides the user with a getter method. This intakes the last and first name of any guest and outputs their name, the table they are at, and which seat they are sitting in. 

I started by working on uploading the file by using a File i/o and a try-catch method that I first learned from a Data worksheet in my AP Computer Science class, and supplemented it by using W3Schools resources. Once the file was uploaded, I had to create a class to organize all the raw data into something useful. That's when I created my person class, which intakes a person's last name, first name, and the number that corresponds to their company. I then created an ArrayList of all the people being created by the data that the file gives. 

When it came to storing the company names with the corresponding numbers, I decided it was much easier to just put it manually into an ArrayList than creating some type of company class, and it was pretty simple. Since the index of an ArrayList starts at 0, but the first company's number was 1, I created a null-spot in my ArrayList so they could both index at 1. 

Setting up the checks on the number of people was relatively easy. It just took a few loops and a constant check of the total number of people or the number of people per company. The only tricky thing was remember to reset the index back by one each time a person was removed, since that changes the index of all the people in the ArrayList afterwards. 

One thing I debated was how to assign tables. Should I use an ArrayList or an Array? Ultimately, since the number of tables and seats per table were fixed, it was simpler to use a 2D array to assign tables. First I sorted my ArrayList of all the guests in company order and that the tables keep looping instead of restarting at table 1 everytime a new company's employees were being sorted, to ensure that there was space for all the companies. This was necessary because before, I would have one table totally fill up with guests before company eleven was being sorted. Since company eleven had 10 employees, they needed an open seat in all ten tables, which wasn't happening originally. Once I got that worked out, this was finished very quickly. 

Finally, I added the roster printing and getter methods. All of these were very simple. Printing rosters by table simply meant going through the table and printing out the people row by row. For printing rosters by company, I just searched through the 2D array for people from the specified company and printed them out as soon as I found it. The getter method was also very similar to rosters by company, but instead of searching for a company number, I was searching for a name. 

Overall, this was a very fun project and rewarding when it finally worked. While not too challenging conceptually, there are a few sticky logical areas that make it hard, and the time contraint made it much more difficult. 
