# BinBash User Guide

## Introduction

This document aims to provide a comprehensive and in-depth guide on how to install, configure and use BinBash. For those who just want to get started right away, head to [Getting Started](#getting-started).

### What's New in BinBash 1.0

Our long awaited first release adds everything you would expect of an inventory management system, including:
* Adding of items
* Removing of items
* Searching for items
* Listing your entire inventory
* and more (head to [Features](#features) to find out!)

## Table of Contents
1. [Introduction](#introduction)
2. [Table of Contents](#table-of-contents)
3. [BinBash Overview](#binbash-overview)
4. [How to Use this Guide](#how-to-use-this-guide)
5. [Getting Started](#getting-started)
6. [Features](#features)
    - [Adding an item: `add`](#adding-an-item-add)
    - [Searching for an item: `search`](#searching-for-an-item-search)
    - [Listing current inventory: `list`](#listing-current-inventory-list)
    - [Selling an item: `sell`](#selling-an-item-sell)
    - [Restocking an item: `restock`](#restocking-an-item-restock)
    - [Updating an item: `update`](#updating-an-item-update)
    - [Deleting an item: `delete`](#deleting-an-item-delete)
    - [Exiting the application: `bye`](#exiting-the-application-bye)
    - [Saving and Loading data](#saving-and-loading-data)
7. [Possible issues during startup](#possible-issues-during-startup)
8. [Command Summary](#command-summary)
9. [FAQ](#faq)
10. [Glossary](#glossary)

## BinBash Overview

Welcome to BinBash, an **Inventory Management System** designed to streamline your stock control processes. 
BinBash offers a simple and lightweight solution that makes managing your items fast and efficient.
It's built to handle the complexities of inventory tracking so that you don't have to. 
This guide will walk you through each feature, providing clear instructions and examples to ensure that you 
can make the most out of BinBash. Let's get your inventory organized efficiently and effectively!

## How to Use this Guide

Though we make no assumptions on your technical familiarity, it would help if this isn't your first time seeing a command prompt.

Throughout the course of this guide we will describe the various inventory management capabilities BinBash is capable of, and how you might perform them using keyboard typed commands.
The format and expected outputs of these commands will be enumerated in turn. Don't worry if you can't get the hang of them immediately! We'll provide some concrete examples to better illustrate their use cases.

If you consider yourself a power user, we feel it is self-explanatory to jump to the [Command Summary](#command-summary).

## Getting Started

To run BinBash, ensure that your computer meets the following minimum system requirements:
* Operating System: Windows, macOS, or Linux
* Java `11` is installed. Refer to our [FAQ](#faq) for more details.

1. Ensure that you have Java `11` or above installed on your computer.
2. Head over to our [GitHub Page](https://github.com/AY2324S2-CS2113T-T09-2/tp/releases) and download the latest version of `BinBash.jar`.
3. Move the downloaded `BinBash.jar` file to an empty folder of your choice. This folder will now serve as the home folder for BinBash.
4. Open the terminal/command prompt for your system.
   1. If you're on Windows, press `Windows Key + R` and type in `cmd`. Press enter to launch the command prompt.
   2. If you're on Mac, click on `Launchpad` > `Other` > `Terminal`. Alternatively, click on the Spotlight icon in your menu bar, and type in `Terminal`.
   3. If you're on Linux, open the terminal in your Linux distribution.
5. Navigate to the folder containing `BinBash.jar` using the terminal/command prompt.
6. Type `java -jar BinBash.jar` into the command prompt, and press enter.

Awesome! You've now successfully started the BinBash application!

Now, you can head over to the [Features](#features) section to learn how to use the application.

## Features

Before we dive into the features that BinBash has to offer, lets start with a little introduction. BinBash tracks four 
types of items in our inventory list. They are:
 - Retail item
 - Operational Item
 - Perishable Retail Item
 - Perishable Operational Item

 Our commands use flags to identify and differentiate the information that you give to BinBash. The following are a 
 list of flags that you can use:
 
- `-re` : to signify a Retail item type
- `-op` : to signify an Operational item type
- `-n` : name of the item
- `-i` : index of the item as displayed in the inventory list
- `-d` : description of the item
- `-q` : quantity
- `-e` : expiry date
- `-c` : cost price, which is the cost that you bought the item for
- `-s` : sale price, which is the price that you are selling it for
- `-t` : threshold, the lower limit of your item quantity, below which you will be alerted of depleting stock

> #### Note:
> - Only one item type flag can be specified for each item. This means that you can only use either `-re` or `-op` but
> not both at the same time. 
> - The `-e` flag determines if the item that you are adding is a Perishable item. This means that items with an expiry 
> date are perishable.
> - The `-s` flag determines if the item that you are adding is a Retail item. This means that an item with a sale price 
> are retail items that are meant to be sold.
> - The flags can be placed in any order. There is no specific order that you have to abide by.

### Adding an item: `add`

> Adds an item to the inventory.

#### Adding a Retail item

Format: `add -re -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -s SALE_PRICE -c COST_PRICE -t THRESHOLD`

* `-re` specifies that this is a Retail item.
* `ITEM_NAME`, `ITEM_DESCRIPTION`, `SALE_PRICE` and `COST_PRICE` must be specified.
* All other fields are optional.
* If `ITEM_QUANTITY` is not specified, a default value of `0` will be assigned to it.
* If `THRESHOLD` is not specified, a default value of `1` will be assigned to it.
* There is no need to include the currency. A `$` sign will be appended to the prices.
* Retail items do not have an `expiry date` field, hence the flag `-e` is not used.

Examples:

- `add -re -n lego -d toys -q 350 -s 102.00 -c 34.32 -t 50`
Adds an item called "lego" into your inventory list with the description "toys" that has a quantity of 350. 
It costs $34.32 per unit item and is sold for $102.00 each. The threshold set for this item is at 50.
- `add -re -n hammer -d tools -q 20 -s 9.00 -c 4.39 -t 10`
Adds an item called "hammer" into your inventory list with the description "tools" that has a quantity of 20.
It costs $4.39 per unit item and is sold for $9.00 each. The threshold set for this item is at 10.

#### Adding a Perishable Retail item

Format: `add -re -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -c COST_PRICE 
-t THRESHOLD`

* The command to add a Perishable Retail item is similar to adding a Retail item.
* An additional flag , `-e`, is used here to include the `expiry date`, hence signifying a Perishable Retail item.

Examples:

- `add -re -n apple -d fruit -q 50 -e 12-12-2024 -s 1.00 -c 0.39 -t 10`
Adds an item called "apple" into your inventory list with the description "fruit" that has a quantity of 50 and will
expire on 12 December 2024. It costs $0.39 per unit item and is sold for $1.00 each. The threshold set for this item is 
at 10.
- `add -re -n tuna fish -d seafood -q 5 -e 02-11-2024 -s 10 -c 4.50`
Adds an item called "tuna fish" into your inventory list with the description "seafood" that has a quantity of 5 and 
will expire on 02 November 2024. It costs $4.50 per unit item and is sold for $10.00 each. The threshold will be
automatically set at 1.

#### Adding an Operational item

Format: `add -op -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -s SALE_PRICE -t THRESHOLD`

* `-op` specifies that this is an Operational Item.
* `ITEM_NAME`, `ITEM_DESCRIPTION` and `SALE_PRICE` must be specified.
* All other fields are optional.
* If `ITEM_QUANTITY` is not specified, a default value of `0` will be assigned to it.
* If `THRESHOLD` is not specified, a default value of `1` will be assigned to it.
* There is no need to include the currency. A `$` sign will be appended to the prices.
* `-s` and `-e` are not used as there are no `sale price` and `expiry date` fields for an Operational Item.

Examples:

- `add -op -n light bulbs -d lighting -q 5 -c 2.30 -t 3`
Adds an item called "light bulbs" into your inventory list with the description "lighting" that has a quantity of 5. 
It costs $2.30 per unit item. The threshold set for this item is at 3.

#### Adding a Perishable Operational item

Format: `add -op -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -t THRESHOLD`

* The command to add a Perishable Operational item is similar to adding an Operational item.
* An additional flag , `-e`, is used here to include the `expiry date`, hence signifying a Perishable Operational item.

Examples:

- `add -op -n milk -d to make cofee -q 2 -e 03-10-2024 -c 1.30`
Adds an item called "milk" into your inventory list with the description "to make coffee" that has a quantity of 2. It
will expire on 3 October 2024. It costs $1.30 per unit item. The threshold is automatically set at 1.

### Searching for an item: `search`

> Searches for items in the inventory, filtering results through a number of user-defined fields.

Format: `search -n NAME_QUERY -d DESCRIPTION_QUERY -q QUANTITY_RANGE -c COST_PRICE_RANGE -s SALE_PRICE_RANGE -e EXPIRY_DATE_RANGE -l NUMBER_OF_RESULTS`

- At least one of -n, -d, -q, -c, -s, or -e must be set.
- NAME_QUERY and DESCRIPTION_QUERY perform a case-insensitive search on the name and description fields of inventory items respectively.
- QUANTITY_RANGE takes the form {min_quantity}..{max_quantity} where at least one of min_quantity or max_quantity is required.
- COST_PRICE_RANGE and SALE_PRICE_RANGE take the form {price_lower_bound}..{price_upper_bound} where at least one of price_lower_bound or price_upper_bound is required.
- EXPIRY_DATE_RANGE is similar to the above range arguments: except dates need to be specified in the format dd-MM-YYYY.
- Shows the first NUMBER_OF_RESULTS results if set, all matching results otherwise.

**Examples:**

- `search -n snake plant`
  Will return all items with names containing snake plant such as "snake plant" and "snake plant seeds".
- `search -c ..5 -l 6`
  Will return the first 6 items that cost up to $5.00.
- `search -s 20..30`
  Will return all items with sale prices between $20 and $30 (inclusive).
- `search -e 11.11.2023.. -l 1`
  Will return the first item that expires on or after 11 November 2023.
- `search -q 50.. -e 17.09.2023..23.11.2023`
  Will return all items with current quantity at or above 50 and that expire between 17 September and 23 November 2023 (inclusive).

### Listing current inventory: `list`

>Shows a list of all tasks in your task list.

**Format:** `list`

### Selling an item: `sell`

> Decrements the quantity of an item after it has been sold.

Format: `sell -n ITEM_NAME -q ITEM_QUANTITY`

* Both flags are mandatory.
* The quantity given to this command represents the amount of item that you want to sell. This amount will be deducted
from the existing quantity of the item in the inventory list.

Examples: 

- `sell -n oranges -q 20` This will deduct the quantity of "oranges" in your inventory list by 20.
- `sell -n lego bricks -q 219` This will deduct the quantity of "lego bricks" in your inventory list by 219.

### Restocking an item: `restock`

> Increments the quantity of an item after it has been restocked.

* Both flags are mandatory.
* The quantity given to this command represents the amount of item that you want to restock. This amount will be added
  to the existing quantity of the item in the inventory list.

Examples:

- `restock -n apples -q 50` This will add the quantity of "apples" in your inventory list by 50.
- `restock -n kaya spread -q 35` This will add the quantity of "kaya spread" in your inventory list by 35.

### Updating an item: `update`

> Modifies the details of an existing item in the inventory. You can identify the item that you want to update by
> specifying the name of the object, or its index number as displayed in the inventory list.

#### Updating an item using item name

Format: `update -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -c COST_PRICE
-t THRESHOLD`

* The flag `-n` is used, meaning that the `item name` is used as an identifier to identify the item you wish to update.
This flag is required.
* Using the `item name` identifier will only update the first occurring item in the list should there be any duplicates.
* All other flags are optional, depending on what details you wish to update.

Examples:
- `update -n banana -d ripe fruit -q 30 -e 10-10-2024 -c 0.50`
Updates the description of the item named "banana" to "ripe fruit", its quantity to 30, its expiry date to 10 October 
2024 and its cost price to $0.50. Other information remain unchanged.
- `update -n "printer paper" -s 15.00 -t 5`
Updates the sale price of the item named "printer paper" to $15.00 and its threshold to 5.
- `update -n "chicken sandwich" -q 50 -e 01-01-2025 -t 10`
Updates the quantity of the item named "chicken sandwich" to 50, its expiry date to 1 January 2025 and its threshold 
to 10.

 
#### Updating an item using item index

Format: `update -i ITEM_INDEX -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -c COST_PRICE
-t THRESHOLD`

* The flag `-i` is used, meaning that the `item index` is used as an identifier to identify the item you wish to update.
* To know the `item index`, we encourage you to first use the command `list` to find out the index of your item of 
interest.
* All other flags are optional,depending on what details you wish to update.

Examples:
- `update -i 2 -d "office supplies" -s 20.00`
Updates the description of the item at index 2 to "office supplies" and its sale price to $20.00. Other information 
remains unchanged.
- `update -i 4 -q 10 -c 2.00 -t 2`
Updates the quantity of the item at index 4 to 10, its cost price to $2.00, and its threshold to 2.


> Note:
> - Only one item identifier flag, `-n` or `-i`, can be used with the `update` command to identify the item that you
> want to update.
> - There must be a minimum of one flag used, excluding the `-n` flag.



### Deleting an item: `delete`

> Deletes an item from the inventory. Item Identifier can be either item index or item name.

#### Deleting an item using item index

Format: `delete ITEM_INDEX`

* `ITEM_INDEX` must be specified.
* `ITEM_INDEX` specified must exist in the inventory, otherwise no item will be deleted
* Index of items can be viewed using the `list` command.

Examples:
* `delete 1` 
* `delete 4`

#### Deleting an item using item name

Format: `delete ITEM_NAME`

* `ITEM_NAME` must be specified.
* `ITEM_NAME` specified must be the exact name of the item.
* If there are no items with item names matching `ITEM_NAME`, no items will be deleted.
* Item names of items in the inventory can be viewed using the `list` command.

Examples:
* `delete cookie`
* `delete tissue paper`

### Calculating the total profit: `profit`

> Calculates the total profit based on the revenue and cost of items in your inventory.

Format: `profit`

This command computes the total profit by subtracting the total cost from the total revenue of all items in your inventory.
The output will display the total profit in the format: Total profit: $XXX.XX

### Exiting the application: `bye`

> Exits the application.

After a long day at work, it's time to take a rest!
Fret not, BinBash will save the state of your current inventory, and you can always come back to it later.

Format: `bye`

### Saving and Loading data

Unsure as to how you can save your BinBash data? Don't worry! Your data is automatically saved to your local storage after any command that modifies your inventory. No manual saving of data is required.

Similarly, your saved data will be automatically loaded into BinBash when you start the application. If no previous save data was found, the application starts on a clean state.

> **Caution**: For advanced users, BinBash data is stored locally as a `.txt` file in your BinBash install location (`<Location of BinBash.jar>/data/items.txt`). Do exercise caution when directly editing this file; BinBash **will not load** your save file if it is corrupted. 
> It is highly recommended to take a backup of the file before editing it.

## Possible Issues During Startup

Have problems loading up BinBash? Fret not, here's how to troubleshoot some of them:

1. **File is Corrupted Error**
   If you encounter an error that says the file is corrupted, you should first take a look at your `data.txt` file. It's possible that the content of the file has become invalid. Here's what you can do:
   - **Delete and Recreate**: If you're unsure about the formatting, simply delete the `data.txt` file. BinBash will create a new one when you restart the application.
   - **Rectify the Content**: If you have important data you can't lose, open the `data.txt` file and fix any formatting issues. Make sure each task follows the correct structure BinBash expects.

2. **Issues with Data Directory or File**
   If you encounter an error about not being able to create or read/write from the data directory or file, this usually means there's a permissions issue on your system. Here's how to handle it:
   - **Check Permissions**: Ensure that BinBash has the right permissions to access the folders it needs. Right-click on the directory and check its properties to make sure reading and writing are allowed.

## Command Summary

| **Commands** | **Usage**                                                                                        |
|--------------|--------------------------------------------------------------------------------------------------|
| **add**      | `add n/ITEM_NAME d/ITEM_DESCRIPTION q/ITEM_QUANTITY e/EXPIRATION_DATE s/SALE_PRICE c/COST_PRICE` |
| **search**   | `search KEYWORD`                                                                                 |
| **list**     | `list`                                                                                           |
| **delete**   | `delete ITEM_INDEX`                                                                              |
| **bye**      | `bye`                                                                                            |

## FAQ

**Q**: How do I know if I have Java `11` installed on my computer? <br>
**A**: Using the terminal/command prompt, type in `java -version`. If Java `11` is installed, you should see a result that is similar to this:
```bash
$ java -version
openjdk version "11.0.22" 2024-01-16
OpenJDK Runtime Environment ... (build ...)
OpenJDK 64-Bit Server VM ... (build ...)
```
If not, refer to Oracle's [guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) on installing Java `11` for your operating system.

**Q**: Can I move my BinBash data to another computer? <br>
**A**: Absolutely! Here's a step-by-step guide on how you can do this:
1. On your current computer, locate the BinBash save file. The save file can be found at `<Location of BinBash.jar>/data/items.txt`. Make a copy of this file.
2. Ensure that BinBash has been installed on the other computer. Refer to [this section](#getting-started) for more details.
3. On the other computer, create the `/data` folder in the BinBash install location if it does not exist.
4. Then, paste the copied save file in this folder. If an existing save file already exists, choose to overwrite it.
5. Start up BinBash, and execute the [`list` command](#listing-current-inventory-list) to check that your data has been loaded successfully.

**Q**: Do I need an Internet connection to use BinBash? <br>
**A**: You do not need an Internet connection. BinBash can be used offline.

## Glossary

### Bash
A computer program that provides a text-based interface and environment for user input. Also, the name of a programming language commonly used for scripting and operating system job control.

### Command Prompt / Command Line / Terminal
A means of interacting with a computer through keyboard typed lines of text, also known as commands. This is in contrast to the currently more popular graphical user interface (GUI), which uses visual elements that users can directly manipulate to perform their desired actions.

### Java
From Wikipedia:
> Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
