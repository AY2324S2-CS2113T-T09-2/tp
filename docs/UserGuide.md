# BinBash User Guide

## Introduction

BinBash is a CLI-based **Inventory Management System** that is targeted at small retail business owners. 
BinBash aims to make the inventory management process streamlined, error-free and highly efficient, through its extensive suite of inventory management features.
BinBash also enables users to gain valuable insights on their inventory, through comprehensive profit reporting features which facilitate efficient sales and trends analysis.

This document aims to provide a comprehensive and in-depth guide on how to install, configure and use BinBash. For those who just want to get started right away, head to [Getting Started](#getting-started).

### What's New in BinBash 1.0

Our long awaited first release adds everything you would expect of an inventory management system, including:
* Adding of items
* Removing of items
* Searching for items
* Listing your entire inventory
* and more (head to [Features](#features) to find out!)

---

## Table of Contents
1. [Introduction](#introduction)
2. [Table of Contents](#table-of-contents)
3. [BinBash Overview](#binbash-overview)
4. [How to Use this Guide](#how-to-use-this-guide)
5. [Getting Started](#getting-started)
6. [Features](#features)
    - [Notes on the Command format](#notes-on-the-command-format)
    - [Adding an item: `add`](#adding-an-item-add)
    - [Searching for an item: `search`](#searching-for-an-item-search)
    - [Listing current inventory: `list`](#listing-current-inventory-list)
    - [Selling an item: `sell`](#selling-an-item-sell)
    - [Restocking an item: `restock`](#restocking-an-item-restock)
    - [Updating an item: `update`](#updating-an-item-update)
    - [Deleting an item: `delete`](#deleting-an-item-delete)
    - [Calculating the total profit: `profit`](#calculating-the-total-profit-profit)
    - [Exiting the application: `bye`](#exiting-the-application-bye)
    - [Saving and Loading data](#saving-and-loading-data)
7. [Command Summary](#command-summary)
8. [FAQ](#faq)
9. [Glossary](#glossary)

---

## BinBash Overview

Welcome to BinBash, an **Inventory Management System** designed to streamline your stock control processes. 
BinBash offers a simple and lightweight solution that makes managing your items fast and efficient, 
thus allowing you more time to focus on your other business needs.
It's built to handle the complexities of inventory tracking so that you don't have to. 

Through BinBash, you can **add**, as well as **track** a variety of items that are present in your inventory. 
If necessary, you can also **delete** these items if they are no longer present.
BinBash also **alerts** you when the quantities of items are running low in your inventory, and allows you to **restock** these items when required.
Furthermore, if items are **sold**, BinBash tracks the sale of these items and generates a **profit analysis**, 
thus providing you with valuable insight on the sales velocity of items in your inventory!

This guide will walk you through each feature, providing clear instructions and examples to ensure that you 
can make the most out of BinBash. Let's get your inventory organized efficiently and effectively!

* [Back to table of contents](#table-of-contents)
---

## How to Use this Guide

Throughout the course of this guide we will describe the various inventory management capabilities BinBash is capable of, and how you might perform them using keyboard typed commands.
The format and expected outputs of these commands will be enumerated in turn. Don't worry if you can't get the hang of them immediately! We'll provide some concrete examples to better illustrate their use cases.

> ❗ This is a warning box that may appear in the guide. Any interactions with the application that can result in issues or errors will be detailed in sections like this one here.

> ℹ️ This is an info box that may appear in the guide. Any additional information about a feature will be detailed in sections like this one here.

If you are new to BinBash (or new to command line-based applications in general), we highly recommend that you read through the rest of this guide sequentially, following the order of sections in this guide.
In particular, we suggest that you take note of the [command format](#notes-on-the-command-format) used by BinBash, as it would provide you with a better understanding of how BinBash processes your commands.

If you consider yourself a power user, feel free to jump to the [Command Summary](#command-summary) section. 
However, if you ever need more clarification on the features provided by a specific command, do head over to the [Features](#features) section, as detailed explanations of each command will be provided there.

* [Back to table of contents](#table-of-contents)
---

## Getting Started

1. Ensure that you have Java `11` or above installed on your computer. Refer to our [FAQ](#faq) for more details.
2. Head over to our [GitHub Page](https://github.com/AY2324S2-CS2113T-T09-2/tp/releases) and download the latest version of `binbash.jar`.
3. Move the downloaded `binbash.jar` file to an empty folder of your choice. This folder will now serve as the home folder for BinBash.
4. Open the terminal/command prompt for your system.
   1. If you're on Windows, press `Windows Key + R` and type in `cmd`. Press enter to launch the command prompt.
   2. If you're on Mac, click on `Launchpad` > `Other` > `Terminal`. Alternatively, click on the Spotlight icon in your menu bar, and type in `Terminal`.
   3. If you're on Linux, open the terminal in your Linux distribution.
5. Navigate to the folder containing `binbash.jar` using the terminal/command prompt.
6. Type `java -jar binbash.jar` into the command prompt, and press enter. If the application has started successfully, you should see the following output:<br>
    ```text
    -------------------------------------------------------------
     ____  _       ____            _
    | __ )(_)_ __ | __ )  __ _ ___| |__
    |  _ \| | '_ \|  _ \ / _` / __| '_ \
    | |_) | | | | | |_) | (_| \__ \ | | |
    |____/|_|_| |_|____/ \__,_|___/_| |_|
    
    Welcome to BinBash!
    -------------------------------------------------------------
    -------------------------------------------------------------
    Here are your metrics:
    Total Cost: 0.00
    Total Revenue: 0.00
    Net Profit: 0.00
    
    -------------------------------------------------------------
    ```

Awesome! You've now successfully started the BinBash application!

Now, you can head over to the [Features](#features) section to learn how to use the application.

#### Possible Issues During Startup

Have problems loading up BinBash? Fret not, here's how to troubleshoot some of them:

1. **File is Corrupted Error**:<br>
   If you encounter an error that says the file is corrupted, you should first take a look at your `items.txt` file. It's possible that the content of the file has become invalid. Here's what you can do:
    - **Delete and Recreate**: If you're unsure about the formatting, simply delete the `items.txt` file. BinBash will create a new one when you restart the application.
    - **Rectify the Content**: If you have important data you can't lose, open the `items.txt` file and fix any formatting issues. Make sure each task follows the correct structure BinBash expects. Refer to the [FAQ](#faq) for more details on the expected save file format.

2. **Issues with Data Directory or File**:<br>
   If you encounter an error about not being able to create or read/write from the data directory or file, this usually means there's a permissions issue on your system. Here's how to handle it:
    - **Check Permissions**: Ensure that BinBash has the right permissions to access the folders it needs. Right-click on the directory and check its properties to make sure reading and writing are allowed.

3. **Unable to Access System Terminal**:<br>
   Ensure that you're using a recent version of your Operating System's Terminal application. For Windows, consider using Powershell or Terminal instead of Command Prompt.

* [Back to table of contents](#table-of-contents)
---

## Features

### Notes on the Command format

Before we dive into the features that BinBash has to offer, lets start with a little introduction. BinBash tracks four 
types of items in your inventory list. They are:

1. `Retail Item`: These are items that are intended for sale to customers. Retail items typically include products that are stocked and sold for profit.
    - **Example**: A pair of sneakers up for sale.

2. `Operational Item`: These items are used by the store for its daily operations but are not sold to customers.
    - **Example**: An operational item could be a printer cartridge for a company printer.

3. `Perishable Retail Item`: These are retail items that have an expiry date and need to be sold before they spoil.
This category is crucial for businesses dealing with food products or other perishable goods.
    - **Example**: A perishable retail item could be a carton of milk to be sold, which has an expiry date.

4. `Perishable Operational Item`: Similar to perishable retail items, these are operational items that have a limited shelf life.
    - **Example**: A bottle of disinfectant used to sanitize the office, with an expiry date.

Before we dive into the different commands you can use, let's get familiar with a concept that's central to our command structure: flags!

In BinBash, flags are like little markers that help us identify and differentiate the information you provide. They're 
like signposts that guide the command to understand what each piece of data represents.

Now, let's take a look at the flags you can use:

| Flag  | Description                                                                                         |
|-------|-----------------------------------------------------------------------------------------------------|
| `-re` | Signifies a Retail item type                                                                        |
| `-op` | Signifies an Operational item type                                                                  |
| `-n`  | Name of the item                                                                                    |
| `-i`  | Index of the item as displayed in the inventory list                                                |
| `-d`  | Description of the item                                                                             |
| `-q`  | Quantity                                                                                            |
| `-e`  | Expiry date                                                                                         |
| `-c`  | Cost price (the cost that you bought the item for)                                                  |
| `-s`  | Sale price (the price that you are selling it for)                                                  |
| `-t`  | Threshold (the lower limit of your item quantity, below which you'll be alerted of depleting stock) |


> #### ℹ️ Note:
> - Only one item type flag can be specified for each item. This means that you can only use either `-re` or `-op` but
> not both at the same time. 
> - The `-e` flag should be provided if the item that you are adding is a Perishable item. That is to say, it will expire by 
> the provided expiry date.
> - The `-s` flag should be provided if the item that you are adding is a Retail item. This means that this item is
> meant to be sold.
> - The flags can be placed in any order. There is no specific order that you have to abide by.
> - Words in `UPPER_CASE` are the arguments that are meant to be supplied by you. 
> For example, in `add -n ITEM_NAME`, `ITEM_NAME` would represent the name of the item you are adding (e.g., `add -n apple`).

If you're starting to feel overwhelmed by all these flags, don't worry! Continue reading to know more about BinBash's
Command auto-suggestions feature that can help you out. It's like having a little helper right at your fingertips!

### Command auto-suggestions

BinBash's command suggestion feature can be activated by pressing the `tab` key which either:
1. Suggests a lists of all valid options on first press; then cycles through this list on subsequent presses; or
2. Autocompletes the command if only 1 valid option is found
Each suggestion is accompanied by a brief description.

To use this feature on command flags, type `-` before pressing `tab`.
As before, each flag is accompanied by an explanation.

To exit command suggestion mode simply press any other key.

> ℹ️ This feature is particularly handy when first learning to use BinBash. Starting from a blank input, press tab to see every command.
> Likewise within each command, type `-` followed by `tab` to give a quick overview of all possible options.

* [Back to table of contents](#table-of-contents)
---

### Adding an item: `add`

> This allows you to add an item to your inventory.

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

- `add -re -n lego -d toys -q 350 -s 102.00 -c 34.32 -t 50`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [R] lego
       description: toys
       quantity: 350
       cost price: $34.32
       sale price: $102.00
       threshold: 50
   -------------------------------------------------------------
   ```
- `add -re -n hammer -d tools -q 20 -s 9.00 -c 4.39 -t 10`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [R] hammer
       description: tools
       quantity: 20
       cost price: $4.39
       sale price: $9.00
       threshold: 10
   -------------------------------------------------------------
   ```

#### Adding a Perishable Retail item

Format: `add -re -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -c COST_PRICE 
-t THRESHOLD`

* The command to add a Perishable Retail item is similar to adding a Retail item.
* An additional flag , `-e`, is used here to include the `expiry date`, hence signifying a Perishable Retail item.
  > ℹ️ Ensure that the provided date is in `DD-MM-YYYY` format. For example, **20 January 2024** is represented as `20-01-2024`

Examples:

- `add -re -n apple -d fruit -q 50 -e 12-12-2024 -s 1.00 -c 0.39 -t 10`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [P][R] apple
       description: fruit
       quantity: 50
       cost price: $0.39
       sale price: $1.00
       threshold: 10
       expiry date: 12-12-2024
   -------------------------------------------------------------
   ```
- `add -re -n tuna fish -d seafood -q 5 -e 02-11-2024 -s 10 -c 4.50`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [P][R] tuna fish
       description: seafood
       quantity: 5
       cost price: $4.50
       sale price: $10.00 
       threshold: 1
       expiry date: 02-11-2024
   -------------------------------------------------------------
   ```

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

- `add -op -n light bulbs -d lighting -q 5 -c 2.30 -t 3`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [O] light bulbs
       description: lighting
       quantity: 5
       cost price: $2.30
   -------------------------------------------------------------
   ```

#### Adding a Perishable Operational item

Format: `add -op -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -t THRESHOLD`

* The command to add a Perishable Operational item is similar to adding an Operational item.
* An additional flag , `-e`, is used here to include the `expiry date`, hence signifying a Perishable Operational item.
  > ℹ️ Ensure that the provided date is in `DD-MM-YYYY` format. For example, **20 January 2024** is represented as `20-01-2024`

Examples:

- `add -op -n milk -d to make cofee -q 2 -e 03-10-2024 -c 1.30`<br>
   ```text
   -------------------------------------------------------------
   Noted! I have added the following item into your inventory:
    
   [P][O] milk
       description: to make cofee
       quantity: 2
       cost price: $1.30
       expiry date: 03-10-2024
   -------------------------------------------------------------
   ```

* [Back to table of contents](#table-of-contents)
---

### Searching for an item: `search`

> This allows you to search for items in your inventory, filtering results through a number of user-defined fields.

Format: `search -n NAME_QUERY -d DESCRIPTION_QUERY -q QUANTITY_RANGE -c COST_PRICE_RANGE -s SALE_PRICE_RANGE -e EXPIRY_DATE_RANGE -l NUMBER_OF_RESULTS`

- At least one of `-n`, `-d`, `-q`, `-c`, `-s`, or `-e` must be set.
- `NAME_QUERY` and `DESCRIPTION_QUERY` perform a case-insensitive search on the name and description fields of inventory items respectively.
- `QUANTITY_RANGE` takes the form `{min_quantity}..{max_quantity}` where at least one of `min_quantity` or `max_quantity` is required.
  > ℹ️ For example, if we intend to search for items with a quantity between 20 and 30, we should format our query as:<br> `-q 20..30`.
- `COST_PRICE_RANGE` and `SALE_PRICE_RANGE` take the form `{price_lower_bound}..{price_upper_bound}` where at least one of `price_lower_bound` or `price_upper_bound` is required.
  > ℹ️ For example, if we intend to search for items that cost between $20 and $30, we should format our query as:<br>`-c 20..30`.
- `EXPIRY_DATE_RANGE` is similar to the above range arguments: except dates need to be specified in the format `dd.MM.YYYY`.
  > ℹ️ For example, if we intend to search for items with an expiry date between 20 January 2024 and 30 January 2024, we should format our query as:<br> `-e 20.01.2024..30.01.2024`.
- Shows the first `NUMBER_OF_RESULTS` results if set, else all matching results are shown.

**Examples:**

- `search -n snake plant`
  Will return all items with names containing **snake plant**, such as "snake plant" and "snake plant seeds".
- `search -c ..5 -l 6`
  Will return the first 6 items that cost up to $5.00.
- `search -s 20..30`
  Will return all items with sale prices between $20 and $30 (inclusive).
- `search -e 11.11.2023.. -l 1`
  Will return the first item that expires on or after 11 November 2023.
- `search -q 50.. -e 17.09.2023..23.11.2023`
  Will return all items with current quantity at or above 50 and that expire between 17 September and 23 November 2023 (inclusive).

* [Back to table of contents](#table-of-contents)
---

### Listing current inventory: `list`

> This allows you to list out all items that you have in your inventory list. 
> You can also use this command to display your inventory list, sorted based on cost price, sale price, profit, or expiry date.

> ℹ️ Note:
> Indexes of the items listed, whether in a sorted list or unsorted list, can be used as references for `delete` and `update` commands.

#### List Inventory (Unsorted)

View the inventory in the order items were added:

Format: `list`

#### List Inventory (Sorted)

BinBash also allows you to sort your inventory based on different criteria using the following flags:

| Flag | Description                                |
|------|--------------------------------------------|
| `-c` | Sort by cost price                         |
| `-s` | Sort by sale price                         |
| `-p` | Sort by profit                             |
| `-e` | Sort by expiry date (for perishable items) |

Format: `list -FLAG`

Example: 

Say you have the following items in your inventory.
- Item A, Cost Price: $5, Sale Price: $15, Profit: $10, Expiry Date: 2023-05-01
- Item B, Cost Price: $10, Sale Price: $12, Profit: $2, Expiry Date: 2023-04-15
- Item C, Cost Price: $8, Sale Price: $20, Profit: $12, Expiry Date: 2023-06-20

After using `list -c`, the sorted list by cost price will be:
- Item A, Cost Price: $5
- Item C, Cost Price: $8
- Item B, Cost Price: $10

If we were to use `list -e` however, the sorted list by expiry date will then be:
- Item B, Expiry Date: 2023-04-15
- Item A, Expiry Date: 2023-05-01
- Item C, Expiry Date: 2023-06-20

Similarly, you can use `list -s` and `list -p`, to sort by sale price and profit respectively.


* [Back to table of contents](#table-of-contents)
---

### Selling an item: `sell`

> This allows you to decrement the quantity of an item after it has been sold.

#### Selling an item using item name

Format: `sell -n ITEM_NAME -q ITEM_QUANTITY`

* Both flags `-n` and `-q` are mandatory.
* The flag `-n` is used, meaning that the `item name` is used as an identifier to identify the item you wish to sell.
* The quantity given to this command represents the amount of item that you want to sell. This amount will be reduced
  from the existing quantity of the item in the inventory list.

Examples: 

- `sell -n oranges -q 20` This will deduct the quantity of "oranges" in your inventory list by 20.
- `sell -n lego bricks -q 219` This will deduct the quantity of "lego bricks" in your inventory list by 219.

#### Selling an item using item index

Format: `sell -n ITEM_INDEX -q ITEM_QUANTITY`

> ℹ️ To determine the `index` of an item in your inventory, call the `list` command first, and
> note down the number displayed next to your item of interest.

* Both flags `-i` and `-q` are mandatory.
* The flag `-i` is used, meaning that the `item index` is used as an identifier to identify the item you wish to sell.

Examples:
- `sell -i 1 -q 50` This will decrease the quantity of the item at index 1 in your inventory list by 50.
- `sell -i 3 -q 35` This will decrease the quantity of the item at index 3 in your inventory list by 35.


> ℹ️ Note:
> - Only one item identifier flag, `-n` or `-i`, can be used with the `sell` command to identify the item that you want to sell.
> - There must be a minimum of one flag used, excluding the `-n` or `-i` flag.

* [Back to table of contents](#table-of-contents)
---

### Restocking an item: `restock`

> This allows you to increment the quantity of an item after it has been restocked.

#### Restocking an item using item name

Format: `restock -n ITEM_NAME -q ITEM_QUANTITY`

* Both flags `-n` and `-q` are mandatory.
* The flag `-n` is used, meaning that the `item name` is used as an identifier to identify the item you wish to restock.
* The quantity given to this command represents the amount of item that you want to restock. This amount will be added
  to the existing quantity of the item in the inventory list.

Examples:

- `restock -n apples -q 50` This will add the quantity of "apples" in your inventory list by 50.
- `restock -n kaya spread -q 35` This will add the quantity of "kaya spread" in your inventory list by 35.

#### Restocking an item using item index

Format: `restock -n ITEM_INDEX -q ITEM_QUANTITY`

> ℹ️ To determine the `index` of an item in your inventory, call the `list` command first, and 
> note down the number displayed next to your item of interest.

* Both flags `-i` and `-q` are mandatory.
* The flag `-i` is used, meaning that the `item index` is used as an identifier to identify the item you wish to update.

Examples:
- `restock -n 2 -q 10` This will add the quantity of the item at index 2 in your inventory list by 10.
- `restock -n 1 -q 65` This will add the quantity of the item at index 1 in your inventory list by 65.


> ℹ️ Note:
> - Only one item identifier flag, `-n` or `-i`, can be used with the `restock` command to identify the item that you
    > want to update.
> - There must be a minimum of one flag used, excluding the `-n` or `-i` flag.

* [Back to table of contents](#table-of-contents)
---

### Updating an item: `update`

> This command allows you to modify the details of an existing item in the inventory. You can identify the item that you want to update, by
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

> ℹ️ To determine the `index` of an item in your inventory, call the `list` command first, and note down the number displayed next to your item of interest.

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


> ℹ️ Note:
> - Only one item identifier flag, `-n` or `-i`, can be used with the `update` command to identify the item that you
> want to update.
> - There must be a minimum of one flag used, excluding the `-n` or `-i` flag.

* [Back to table of contents](#table-of-contents)
---

### Deleting an item: `delete`

> This lets you delete an item from the inventory. You can identify an item by its name, or its index number (as displayed in the inventory list).

#### Deleting an item using item index

Format: `delete -i ITEM_INDEX`

> ℹ️ To determine the `index` of an item in your inventory, call the `list` command first, and note down the number displayed next to your item of interest.

* `ITEM_INDEX` must be specified.
* `ITEM_INDEX` specified must exist in the inventory, otherwise no item will be deleted.
* Index of items can be viewed using the `list` command.

Examples:
- `list` followed by`delete -i 1` Deletes the item with index of 1.
- `list` followed by `delete -i 4` Deletes the item with index of 4.

#### Deleting an item using item name

Format: `delete -n ITEM_NAME`

* `ITEM_NAME` must be specified.
* `ITEM_NAME` specified must be the exact name of the item.
* `ITEM_NAME` is case-sensitive. Capital letters are treated differently from lower case letters, e.g "apple" is different from "APPLE"
* If there are no items with item names matching `ITEM_NAME`, no items will be deleted.
* If there are items with the same `ITEM_NAME`, only the first instance of item with `ITEM_NAME` will be deleted.
* Item names of items in the inventory can be viewed using the `list` command.

Examples:
- `list` followed by `delete -n cookie` Deletes the first item named "cookie".

> ❗Item name is case-sensitive. So items with names as "COOKIE", "Cookie", etc.  will not be deleted.

- `list` followed `delete -n tissue paper` Deletes the first item named "tissue paper".

> ❗Item name is case-sensitive. So items with names as "TISSUE PAPER", "Tissue Paper", etc. will not be deleted.

* [Back to table of contents](#table-of-contents)
---

### Calculating the total profit: `profit`

> This command allows you to view the total profit that you've earned, based on the revenue and cost of items in your inventory.

Format: `profit`

This command computes the total profit by subtracting the total cost from the total revenue of all items in your inventory.
The output will display the total profit in the format as seen below:
```text
-------------------------------------------------------------
Total profit: $6907.40

-------------------------------------------------------------
```

* [Back to table of contents](#table-of-contents)
---

### Exiting the application: `bye`

> This command allows you to exit the application.

After a long day at work, it's time to take a rest! You can safely quit the application using this command.

Format: `bye`

> ℹ️ BinBash will save the state of your current inventory, and you can always come back to it later.

* [Back to table of contents](#table-of-contents)
---

### Saving and Loading data

Unsure as to how you can save your BinBash data? Don't worry! Your data is automatically saved to your local storage. No manual saving of data is required.

Similarly, your saved data will be automatically loaded into BinBash when you start the application. If no previous save data was found, the application starts on a clean state.

> ❗ For advanced users, BinBash data is stored locally as a `.txt` file in your BinBash install location:<br>`<Location of binbash.jar>/data/items.txt`.
> 
> Do exercise caution when directly editing this file, as BinBash **will not load** corrupted data (i.e., data that is not formatted correctly). 
> 
> We highly recommended that you take a backup of your save file before editing it.

* [Back to table of contents](#table-of-contents)
---

## Command Summary

| **Commands** | **Usage**                                                                                                                                                                                              |
|-------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**     | `add -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRATION_DATE -s SALE_PRICE -c COST_PRICE` `add -op -n ITEM_NAME -d ITEM_DESCRIPTION -q ITEM_QUANTITY -e EXPIRY_DATE -s SALE_PRICE -t THRESHOLD` |
|**search**   | `search KEYWORD`                                                                                                                                                                                       |
| **list**    | `list` `list -c` `list -s` `list -e`                                                                                                                                                                   |
| **delete**  | `delete ITEM_INDEX` , `delete ITEM_NAME`                                                                                                                                                               |
| **profit**  | `profit`                                                                                                                                                                                               |
| **bye**     | `bye`                                                                                                                                                                                                  |
| **sell**    | `sell -n ITEM_NAME -q ITEM_QUANTITY` `sell -n ITEM_INDEX -q ITEM_QUANTITY`                                                                                                                             

* [Back to table of contents](#table-of-contents)
---

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
1. On your current computer, locate the BinBash save file. The save file can be found at `<Location of binbash.jar>/data/items.txt`. Make a copy of this file.
2. Ensure that BinBash has been installed on the other computer. Refer to [this section](#getting-started) for more details.
3. On the other computer, create the `/data` folder in the BinBash install location if it does not exist.
4. Then, paste the copied save file in this folder. If an existing save file already exists, choose to overwrite it.
5. Start up BinBash, and execute the [`list` command](#listing-current-inventory-list) to check that your data has been loaded successfully.

**Q**: Do I need an Internet connection to use BinBash? <br>
**A**: You do not need an Internet connection. BinBash can be used offline.

**Q**: I want to modify my `items.txt` file. How should I format my items?<br>
**A**: Firstly, we recommend that you take a backup of your current `items.txt` file before editing it.

Then, open the file in any text editor of your choice (you can use `Notepad` on Windows). Feel free to add, modify or remove rows, but do ensure that they adhere to this format:
```text
ITEM_TYPE|ITEM_NAME|ITEM_DESCRIPTION|QUANTITY|ITEM_COST_PRICE|TOTAL_UNITS_PURCHASED|ITEM_THRESHOLD|ITEM_EXPIRATION_DATE|ITEM_SALE_PRICE|TOTAL_UNITS_SOLD|
```

If your item does not contain a certain attribute (e.g, no `ITEM_SALE_PRICE`), replace its value with a whitespace.

* [Back to table of contents](#table-of-contents)
---

## Glossary

### Bash
A computer program that provides a text-based interface and environment for user input. Also, the name of a programming language commonly used for scripting and operating system job control.

### Command Prompt / Command Line / Terminal
A means of interacting with a computer through keyboard typed lines of text, also known as commands. This is in contrast to the currently more popular graphical user interface (GUI), which uses visual elements that users can directly manipulate to perform their desired actions.

### Java
Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
