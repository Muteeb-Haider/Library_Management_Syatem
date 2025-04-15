# University Library Management System

## Overview

The **University Library Management System** automates the management of library operations, including the handling of physical and digital items, member transactions, and repairs. It tracks borrowing history, item conditions, and member loyalty, while offering search functionalities and reporting features.

## Features

- **Item Management**: 
  - Supports physical and digital items (books, theses, research papers).
  - Allows multiple copies of items and creation of digital copies.
  - Tracks borrowing history and item conditions.

- **Member Management**:
  - Stores member details (ID, name, email, category: student, faculty, alumni).
  - Manages borrowing limits, with loyalty rewards for undamaged returns.
  - Tracks active borrowed items, initially limited to 3 items per member.

- **Transaction Management**:
  - Tracks borrowing and returning transactions.
  - Handles item conditions (preserved or damaged).
  - Manages repairs and archiving for severely damaged items.

- **Repair Section**:
  - Manages repairs for damaged items and tracks the repair process.
  - Determines whether an item should be repaired or archived based on damage severity.

- **Search and Reporting**:
  - Search for items by title and author.
  - Identify popular items (most borrowed, most downloaded).
  - Track the most active members based on borrowing frequency.

## Getting Started

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/university-library-management.git
   cd university-library-management
Compile and Run:

Ensure you have JDK 11 or higher installed.

Compile the Java files:

bash
Copy
Edit
javac *.java
Run the system:

bash
Copy
Edit
java LibraryManagementSystem
Input Files:

The system reads from items.txt and members.txt to initialize the library with items and members.

Example input files:

items.txt: Contains details about the library items (ID, title, author, page count, etc.).

members.txt: Contains member information (ID, name, email, category).
