# Wong Yi Hao - Project Portfolio Page

## Project: BinBash

BinBash is a CLI-based **Inventory Management System** that is targeted at small retail business owners.
BinBash aims to make the inventory management process streamlined, error-free and highly efficient, through its
extensive suite of inventory management features.
BinBash also enables users to gain valuable insights into their inventory, through comprehensive profit reporting
features which facilitate efficient sales and trends analysis.

Given below are my contributions to the project.

## Summary of Contributions

* **New Feature**: Added the ability to add different types of items to the inventory (`ItemList`).
    * **What it does**: Allows the user to add different types of `Item` to their inventory. These include `RetailItem`, 
    `PerishableRetailItem`, `OperationalItem` and `PerishableOperationalItem`. These different types of `Item` can take 
    in multiple parameters, and this allows for the creation of items with different attributes.
    * **Justification**: This feature is a core functionality of an inventory management application, as a user needs to
    be able to add items to their inventory. Additionally, users also require the ability to add different types of items
    to their inventory for tracking purposes. With this feature, users can track Retail Items (items to be sold), as well
    as Operational Items (items meant for internal, day-to-day operational use within the shop) in their inventory.
    * **Highlights**: This feature required some class attributes to be set as "optional", due to the fact that 
    users are not required to provide certain parameters when creating an item (e.g., item quantity is not a required 
    parameter for item creation). A couple of different approaches were trialled (including an approach which involved 
    Optional class attributes) to determine the most appropriate solution for this problem, before it was decided that
    the current approach is best.

* **New Feature**: Implemented the Apache Commons CLI Library for enhanced parsing of user commands.
    * **What it does**: Allows the user to input commands with the help of options/flags, and also, gives the user the
    freedom to input arguments in any order they desire. This feature negates the need for the application to enforce
    the usage of RegEx-parsable command formats.
    * **Justification**: This is a crucial quality-of-life feature for the application, as the usage of this parsing 
    library enables the user to input options and arguments in any order they wish, which allows for greater flexibility
    and ease of use. Furthermore, as the application is designed to accept optional arguments for certain commands,
    this library also makes it easier for us to retrieve parsed arguments from the user.
    * **Highlights**: This feature came about due to our application's requirement to allow optional arguments.
    Initially, an approach using RegEx parsing was adopted, where it was enforced that the users input commands in a
    RegEx-parsable format. However, it soon became tedious to account for the optional arguments using RegEx, and a lot of 
    time was sunk into developing a working solution. Eventually it was agreed upon that this library would be adopted,
    as it could adequately address the issues we faced with respect to input parsing, and it also provided the 
    quality-of-life features that we were looking to implement in the application.

* **New Feature**: Added the ability for the user to safely exit the application (`bye` command).
    * **What it does**: Allows the user to safely exit the application.
    * **Justification**: This is a basic feature of the application, as users need to exit the application normally
    without inducing any exceptions.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=yhwong20&breakdown=true)

* **Project Management**:
    * Assisted with the management (creation and closure) of issues for various milestones.
    * Provided the built JAR file for release v2.0. 

* **Documentation**:
    * User Guide:
      * Added the documentation for the `bye` command.
      * Added the **Getting Started** and **FAQ** section.
      * Added and maintained the table of contents to ensure navigability of the document.
      * Added call-out boxes throughout the document to improve emphasis and clarity.
      * Implemented changes to improve user-centric tone, readability and clarity.
    * Developer Guide:
      * Added the Design - Architecture section, as well as adding the overall architecture diagram of the application.
      * Added the Design - Data Component section, as well as adding the Data Component diagram and Item Class diagram.
      * Added the Features - Add Item to Inventory section, as well as adding the sequence diagram for the add functionality.
      * Added the Parser Class diagram, as well as the sequence diagram for the parsing functionality.
      * Added the **Acknowledgements**, **Setting up, getting started** and **Instructions for manual testing** sections.
      * Updated User stories.
      * Added and maintained the table of contents to ensure navigability of the document.

* **Review contributions**:
    * [List of GitHub pull requests reviewed (filtered)](https://github.com/AY2324S2-CS2113T-T09-2/tp/pulls?q=is%3Apr+reviewed-by%3AYHWong20+-author%3AYHWong20+)
