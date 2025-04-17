Library Management System
========

This is a Library Management System built using Java. It uses object-oriented programming principles, supports persistent data storage using text files, and enables basic library operations such as managing books, members, and borrowing.

## Features

- Add new books and school books
- Register library members
- Borrow and return books
- Save and load data using plain text files (items.txt, members.txt)
- List all books and members with current status
- Delete books (only if not borrowed)
- Delete members (only if they have no borrowed items)
- Input validation to prevent errors
- Auto-incremented unique IDs for books and members

## Project Structure

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
Meanwhile, the compiled output files will be generated in the `bin` folder by default.

src/
├── Main.java                 // Entry point with interactive menu
├── LibraryManager.java       // Core logic and data management
├── LibraryItem.java          // Abstract class for library items
├── Book.java                 // Book class extending LibraryItem
├── SchoolBook.java           // School-specific book
├── Member.java               // Member data and borrowing logic
├── FileHandler.java          // Load/save from text files
└── InputHandler.java         // Reusable input utilities

## How to Run

Prerequisites
- Java 8 or above installed
- A terminal or IDE (like IntelliJ, Eclipse, or VS Code)

Run Steps
- Compile all files: javac *.java
- Run the program: java Main

## Sample Menu

=== Library Management System ===
1. Add Book
2. Add School Book
3. Register Member
4. Borrow Book
5. Return Book
6. List All Books
7. List All Members
8. Delete Member
9. Delete Book
10. Save Data to File
11. Exit

## File Format
- items.txt (Books and SchoolBooks)
Book, ID, Title, Author, isAvailable
SchoolBook, ID, Title, Subject,isAvailable

- members.txt
MemberID, Name, BorrowedItemID1, BorrowedItemID2,...


## Future Improvements
1. Add search and filter functionality
2. Implement due dates and overdue logic
3. Admin/member login system
