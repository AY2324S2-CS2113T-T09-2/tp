# PureUsagi's Project Portfolio Page

## Project: BinBash

BinBash is a CLI-based **Inventory Management System** that is targeted at small retail business owners.
BinBash aims to make the inventory management process streamlined, error-free and highly efficient, through its 
extensive suite of inventory management features.
BinBash also enables users to gain valuable insights into their inventory, through comprehensive profit reporting 
features which facilitate efficient sales and trends analysis. 

Given below are my contributions to the project.

### Summary of Contributions:

* **New Feature**: Added the `delete` command to remove items.
  * **What it does**: Allows the user to remove one item from the inventory at a time. The item can be identified using its
id shown by the list command or the name of the item.
  * **Justification**: This feature is vital to the product as it allows users to remove items that are no longer being kept
in their inventory, reasons can include: no longer being sold due to poor sales or items have expired and need to be
disposed of. 

* **New Feature**: Added the sorting functionality for the `list` command.
  * **What it does**: Allows the user to specify the order of how items should be shown by the list command. The user can 
specify whether the list is sorted by cost price, sale price or expiry date. Additionally, the index of items shown in
the sorted list can also be used as references for other commands like `delete`, `update` `sell` or `restock`.
  * **Justification**: This feature improves the readability by allowing it to be sorted in a way that is desired by the
user. In situations where the inventory is very large, the user cannot be expected to scroll through the unsorted list
just to find a few items. Sorting the list is a quality-of-life feature that greatly enhances the experience of the 
user when using the product.
  * **Highlights**: This implementation requires an overhaul of the existing list feature and also affects the 
`delete` and `update` commands due to the changes on the item indexes displayed to the user.
As such, the implementation requires a full understanding of how indexes are used in the product and necessary measures 
are taken to ensure that the implementation of the sort feature does not affect the correctness of other commands. The 
implementation was also challenging as changes to key classes such as the parser had to be made, which required the 
understanding of how the 3rd party was integrated into the product and had to be replicated for the sorting 
implementation.

* **New Feature**: Added logging class to generate logs to a log file.
  * **What it does**: Allows developers to easily create logs when adding code to the repository which automatically 
saves them in the log file.
  * **Justification**: Developers may want a way to easily write logs to a log file without the need to handle 
functionality and exceptions in the default Java logger.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=pureusagi&breakdown=true)

* **Contributions to the UG**:
  * `delete` command section.
  * `list` command section.

* **Contributions to the DG**:
  * `delete` command section and sequence diagram.
  * `list` command section and sequence diagram.
  * Logging section.

* **Contributions to team-based tasks**:
  * Set up the team/org repo.
  * Release of v1.0 of the product.
  * Assist in issue management.
  * Assist in reviewing and approving GitHub pull requests.
  * Updating user/developer docs that are not specific to a feature.
    * Edit the target user section of the developer guide.
    * Edit the value proposition section of the developer guide.
    * Edit the command summary section of the user guide.
    * Add section delimiters using horizontal rules of the user guide.
    * Add links to the table of contents at the end of each section of the user guide.
    * Assist in adding warnings to some commands.

* **Community Engagement**
  * Conduct testing for other teams' applications during PE-D and provided bug reports.

* **Review contributions**:
  * [List of GitHub pull requests reviewed (filtered)](https://github.com/AY2324S2-CS2113T-T09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3APureUsagi+-author%3APureUsagi+)