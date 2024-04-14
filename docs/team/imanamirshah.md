# Syed Iman's Project Portfolio Page

## Project: BinBash

BinBash is a CLI-based Inventory Management System that is targeted at small retail business owners. BinBash aims to 
make the inventory management process streamlined, error-free and highly efficient, through its extensive suite of 
inventory management features. BinBash also enables users to gain valuable insights into their inventory, through 
comprehensive profit reporting features which facilitate efficient sales and trends analysis.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature**: Implemented `AddCommand` for users to add items to the inventory
  - What it does: Allows user to utilise the `add` command to add new items into the inventory list. This is a very 
  basic feature that allows users to use the application.
  - Justification: This is a vital component as users will require a way to access the relevant methods to add items 
  from the `ItemList` class. The `AddCommand` class will facilitate in the construction of an `add` command to execute
  user requests.


- **New Feature:** Implemented `sell` and `restock` features as well as an alert feature that notifies users of low
stock
  - What it does: Allows users to utilise the `sell` and `restock` commands to make increments or decrements to the 
  quantity of the items for the respective operations. A `threshold` field is also maintained for all items to track if 
  its quantity falls under it when sold. This generates a warning to alert users that stocks are depleting.
  - Justification: These are also core components as it facilitates the management of the inventory for business 
  operations. Furthermore, the alert function is a good-to-have feature that automates processes for users as it 
  eliminates the need for manual counting. This helps to optimise their processes.
  - Highlights: Initially, these features were implemented with the use of item names as the query. However, due to the 
  possibility of duplicates, we require a query identifier that is unique. Hence, the method to sell or restock item is 
  overloaded to take in either item name or item index. Querying by item name is retained as it can be used to effect
  all duplicates if user wishes to. In our final version, duplicate item names are not allowed. Hence, this query method 
  will be retained just as an alternative way users can carry out this command.


- **New Feature:** Implemented `update` feature
  - What it does: Allows users to utilise the `update` command to make edits to the data of any item.
  - Justification: This is a basic optimisation for the program as it eliminates the need to delete and create a new 
  entry to fix any discrepancies.
  - Highlights: Similarly, this feature was implemented with the use of item names as the query initially but altered 
  to also take in item index to handle duplicates. This was also particular tricky to implement due to the different 
  nature of items resulting in certain item types having unique fields such as expiry date and sale price. As a result, 
  several checks have to be implemented to catch exceptions to prevent the program from crashing should an invalid 
  request be given.


- **Code Contributed:** [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=imanamirshah&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Documentation:**
  - User Guide
    - Provided content and notes on the different flags that users can use with the commands. 
    - Added elaboration on different item types for the `add` feature.
    - Introduced the `update`, `sell` and `restock` features, including relevant notes and examples.
  - Developer Guide
    - Described the implementations of the `update`, `sell` and `restock`.
    - Added the sequence diagrams for the above features.


- **Team Contributions:**

  - Assisted in creation and management of issues of user stories as well as issues for milestones.
  - Assisted in completing some User Guide sections not specific to any feature such as the notes on use of flags.
  - Generated the PDF for User Guide for submission.
  - Generated README.md for repository webpage.


- **Review Contributions:** [List of GitHub pull requests reviewed (filtered)](https://github.com/AY2324S2-CS2113T-T09-2/tp/pulls?q=is%3Apr+reviewed-by%3Aimanamirshah)

---

