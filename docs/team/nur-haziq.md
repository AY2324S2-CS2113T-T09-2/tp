# Nur Haziq's Project Portfolio Page

## Project: BinBash 

BinBash is an inventory management system is designed to empower small businesses with robust inventory and item 
management capabilities. It is a CLI based application, written in Java, and it has slightly more than 10k lines of 
code.

My role in this project was primarily focused on the storage aspects of BinBash and ensuring reliable data persistence.

### Contributions to the Project

### Feature 1: Storage Management System

- **What it does**:
  - The `Storage.java` class implements file operations that enables the application to automatically save the current 
  state of inventory items, including details like name, quantity, cost, and expiration dates, and to retrieve this 
  state upon restart.

- **Justification**:
  - Data persistence is critical for any inventory management system. Without it, users would lose inventory 
  information upon application exit, rendering the system unreliable and impractical for real-world use.

- **Highlights**:
  - Designed with the capability to handle multiple item types, leveraging on polymorphism and Java's File I/O systems.
  - Constructed with a parsing logic that accommodates a flexible data structure, allowing for future 
  enhancements and new item attributes.

- **Challenges faced**:
  - Ensuring the system's scalability posed a significant challenge, requiring careful design to allow for the addition 
  of new item types without necessitating a redesign of the entire storage system. 
  - This meant that the storage mechanism had to be straightforward. Additionally, the codebase also had to be kept 
  clean and readable at all times, as this was essential in facilitating the future development and addition of new 
  items by other developers.

### Feature 2: Data Recovery Mechanism

- **What it does**:
  - In the event of data corruption, this mechanism automatically renames the corrupted file to a designated filename
  ('items_corrupted.txt') and creates a new file, allowing the system to continue operations while preserving the 
  corrupted data for user analysis.

- **Justification**:
  - Protecting user data and minimizing downtime are critical in inventory management. This recovery process ensures 
  that users do not experience data loss and can continue to use the application with minimal interruption.

- **Highlights**:
  - The system detects file corruption during data loading and immediately takes steps to secure user data by 
  isolating the corrupt file.
  - A new data file, that does not contain the corrupted item(s), is created on-the-fly to maintain system operability, and 
  the occurrence is logged for system administrators to analyze and address.

### Enhancements to Existing Features:

- **Profit Calculation & List Sorting by Profit**:

  - **What it does**: Extended the `list` feature in the inventory management system to include calculation of profits 
  for retail items and added functionality to sort the list by profit margins of each retail item.
  
  - **Justification**: This enhancement was necessary as the application evolved to handle more complex financial 
  metrics, providing users with valuable insights into the profitability of their inventory items.
  
  - **Highlights**:
    - Implemented methods to calculate total revenue and total costs, along with a function to determine net profit for 
    retail items.
    - Added a new sorting capability that orders retail items by profit, enabling users to quickly identify the most and
    least profitable items in their inventory.

### Code Contributed:
- [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=nur-haziq&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Contributions to JUnit testing:
- Crafted a suite of JUnit tests for the `Storage.java` class, substantially increasing code coverage and ensuring the 
reliability of the storage operations.
- Contributed a significant amount of JUnit tests for the `ItemList` class, validating the integrity and performance of 
methods in the `ItemList` class.
- Contributed in coming up with JUnit tests across various `Command` classes.

### Documentation:
- User Guide:
  - Created the **'Key Definitions'** section of the UG, which aimed to introduce to the user the different item types 
  within BinBash. 
  - Detailed the usage of **flags** and **placeholders**, explaining their roles in command inputs and documenting conditions 
  for valid and invalid data entries, thereby facilitating error-free user interactions. 
  - Presented a structured explanation of the **command format**, illustrating how commands, flags, and placeholders 
  interact, which aids users in mastering command execution for inventory management.
  - Reformatted the `list` command section to condense the information and utilized a tabular format to explain the 
  various ways to sort inventory based on criteria like cost, sale price, profit, and expiry date.
  - Reformatted the summary table of commands encompassing `add`, `search`, `list`, `delete`, `sell`, `restock`, 
  `update`, and `profit`, summarizing their usage and descriptions for quick reference.
  - Improved the existing documentation with a focus on user-friendliness.

<br>

- Developer Guide:
  - Designed the overall sequence diagram to provide developers with a high-level understanding of BinBash's design and
    how the different classes interconnect during application execution.
  - Developed a class diagram for the `Storage` component, specifying its responsibilities like loading data, 
  saving data, handling corrupted files, and parsing data.
  - Added a class diagram of the command classes, highlighting their structure and relationships within the 
  `seedu.binbash.command` package.
  - Described the `DeleteCommand` class' implementation, including constructor design decisions that align with the
  Single Responsibility Principle and the command pattern encapsulation.

### Community Engagement & Contribution to Team Tasks:
- Release of v2.0 of the product
- Participated regularly in the peer review process. [Link to PR's reviewed](https://github.com/AY2324S2-CS2113T-T09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Anur-haziq+-author%3Anur-haziq)
- Actively participated in code reviews for both the iP and PE-D, with contributions recognized as above average in 
terms of quantity of feedback provided.

