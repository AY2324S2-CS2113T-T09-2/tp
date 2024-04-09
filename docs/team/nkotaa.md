# Ng Kota - Project Portfolio Page

## Project: BinBash

BinBash is a CLI-based **Inventory Management System** that is targeted at small retail business owners.
BinBash aims to make the inventory management process streamlined, error-free and highly efficient, through its
extensive suite of inventory management features.
BinBash also enables users to gain valuable insights into their inventory, through comprehensive profit reporting
features which facilitate efficient sales and trends analysis.

Given below are my contributions to the project.

### Summary of Contributions

#### Code contributed

[Summary](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=nkotaa&breakdown=true)

**UI**: Implemented text interface to read user inputs and print program outputs

**Search feature**: Added the ability to search for an item through any number of item-specific fields, such as name, description, quantity, sale price.
- For fields holding numerical values, extended feature to allow searching by range.
- Furthered usefulness by allowing for filtering through number of desired results.
- Implemented using a `SearchAssistant` class to be shared using other features such as Update.

**Command Parsers**: Wrote Delete and Search command parsers.

**CommandOptionAdder**

#### Enhancemennts Implemented

**Use of JLine library**: Identified and implemented the use of an external library to greatly augment command based user interface, providing features such as
- Automatic command suggestions
- Dynamically presented command and option descriptions
- Command completion to nearest match or cycle through matches on `tab` key
- Option suggestions to nearest match or cycle through matches on `tab` key

**General refactoring**: Heavily refactored parsers and exception classes

#### Contributions to Documentation

**Implemented skeleton UG & DG**: Shared a first draft of the user guide and skeleton draft of the developer guide for contributors to start working on.

**UG: Updated section for search feature**

**DG: Added sequence diagram for search feature**

#### Review & Mentoring Contributions

**Reviewed major changes to code base**
- [Migration to Commons CLI parser library](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/108#pullrequestreview-1962307487)
- [Implementation of update and notification features](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/139#pullrequestreview-1970164721)

**Suggested code improvements to team members**
- Provided idea for maintaining a sorted item index list in implementation of List feature.
- Suggested refactors to Update feature saving ~80 LOC.

#### Contributions Beyond Project Team

**Shared tip in forums**: Shared code to get course conforming formatting for PlantUML Class Diagrams.
