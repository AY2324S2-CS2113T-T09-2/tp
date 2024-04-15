# Nur Haziq's Project Portfolio Page
## Project: BinBash 

BinBash is an inventory management system is designed to empower small businesses with robust inventory and item 
management capabilities. It is a CLI based application, written in Java, and it has slightly more than 10k lines of 
code. My role in this project was primarily focused on the storage aspects of BinBash and ensuring reliable data persistence.

### Contributions to the Project
### Feature 1: Storage Management System
- **What it does**:
  - Implements file operations in Storage.java to auto-save and retrieve inventory items including name, quantity, cost, and expiration dates.
- **Justification**:
  - Data persistence is critical for any inventory management system. Without it, users would lose inventory 
  information upon application exit, rendering the system unreliable and impractical for real-world use.
- **Highlights**:
  - Handles multiple item types using polymorphism and Java's File I/O.
  - Features flexible parsing logic for future enhancements (such as addition of new item types)
- **Challenges faced**:
  - Ensuring the system's scalability posed a significant challenge, requiring careful design to allow for the addition 
  of new item types without necessitating a redesign of the entire storage system. 
  - This meant that the storage mechanism had to be straightforward. Additionally, the codebase also had to be kept 
  clean and readable at all times, as this was essential in facilitating the future development and addition of new 
  items by other developers.

### Feature 2: Data Recovery Mechanism
- **What it does**:
  - On detecting data corruption (when loading), app renames corrupted file and creates a new one to maintain operability.
- **Justification**:
  - Protecting user data and minimizing downtime are critical in inventory management. This recovery process ensures 
  that users do not experience data loss and can continue to use the application with minimal interruption.
- **Highlights**:
  - Securely isolates corrupt files and logs incidents for analysis.

### Enhancements to Existing Features:
- **Profit Calculation & List Sorting by Profit**:
  - **What it does**: Adds profit calculation, and allow sorting by profit margins.
  - **Justification**: Offer insights to users for their inventory profitability.
  - **Highlights**:
    - Implements revenue and cost calculations, with a sorting function for profitability.

### Code Contributed:
- [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=nur-haziq&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Contributions to JUnit testing:
- Developed comprehensive JUnit tests for Storage.java,ItemList.java and Command classes, enhancing code reliability and performance.

### Documentation:
- User Guide:
  - Created the **'Key Definitions'** section of the UG, which aimed to introduce to the user the different item types,
  the concepts of **flags** and **placeholders**.
  - Reformatted the `list` command section to condense the information.
  - Reformatted the summary table of commands encompassing `add`, `search`, `list`, `delete`, `sell`, `restock`, 
  `update`, and `profit`, summarizing their usage and descriptions for quick reference.
  - Improved the existing documentation with a focus on user-friendliness.
- Developer Guide:
  - Designed the overall sequence diagram.
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

