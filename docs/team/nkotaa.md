# Ng Kota - Project Portfolio Page

## Project: BinBash

BinBash is an Inventory Management System targeted at small retail business owners.
Users interact with it through a CLI. It is written in Java, and has around 4k (functional) LoC.

Given below are my contributions to the project.

- **New Feature**: Added the ability to search through an inventory. ([#127](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/127))
    - What it does: Allows the user to search for an item based on any number of fields such as name, description, quantity, cost price etc.
    - Justification: This feature is a fundamental requirement of any inventory management system as users would want to search for items, such as when they are trying to find soon to expire items.
    - Highlights: Single responsibility principle was applied to make use of a helper class that contained methods for searching per field. Furthermore as the command that takes the most number of possible option arguments, the corresponding command parser had to be made much more robust.
    - Credits: Inspiration was taken from [@nur-haziq](https://github.com/nur-haziq) to allow numerical based fields to take a range and not just a single value.

- **New Feature**: Identified and implemented an external library to augment the text based UI beyond standard Java console. ([#161](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/161))
    - What it does: present dynamic command and option descriptions; enable command completion and cycling through suggestion on `tab` key.
    - Justification: Our application should be faster than a corresponding GUI for fast typists.
    - Highlights: To avoid redeclaring options and descriptions, I extended the abstraction class called by sub-parsers to store these values. This was done while minimising coupling and enhancing maintainability of each class.
    - Credits: [JLine3](https://github.com/jline/jline3)

- **Code contributed**: [RepoSense](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=nkotaa&breakdown=true)

- **Enhancements**:
    - Revamped Parser implementation, increasing extensibility with the help of a utility class. This approach went on to be uniformly adopted across all command parsers. ([#106](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/106), [#110](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/110))
    - Significantly strengthened program input validation and error handling, resolving issues identified during PE-D as well as others found. ([#256](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/256), [#275](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/275))
    - General refactoring of code base to increase expressiveness of code. ([#79](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/79), [#267](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/267))

- **Documentation**:
    - User Guide
        - Updated section for `search` feature [#150](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/150)
    - Developer Guide
        - Added component details and class diagram for `Ui` [#122](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/122)
        - Added details and sequence diagrams for `search` feature [#169](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/169)

- **Project Management**:
    - Shared a first draft of the user guide and skeleton draft of the developer guide for contributors to start working on. ([#58](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/58), [#115](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/115))
    - Triaged more than 40 out of 50 PE-D bugs, closing duplicates, giving appropriate labels and assigning them to respective members.

- **Team Contributions**
    - Reviewed major PRs: [Migration to Commons CLI parser library](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/108#pullrequestreview-1962307487), [Changes to item](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/97#pullrequestreview-1957041235) and [classes](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/126#pullrequestreview-1968282135), [Implementation of update and notification features](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/139#pullrequestreview-1970164721)
    - Contributed to implementation ideas for other features
        - Provided idea to [@PureUsagi](https://github.com/PureUsagi) for maintaining a sorted item index list in implementation of `list` feature.
        - Discussed refactors to `update` feature with [@imanamirshah](https://github.com/imanamirshah) saving ~80 LoC.
        - Suggested refinements to [#266](https://github.com/AY2324S2-CS2113T-T09-2/tp/pull/266)

- **Community Contributions**
    - Shared tip in forums to get course conforming formatting for PlantUML Class Diagrams. [Forum link](https://github.com/nus-cs2113-AY2324S2/forum/issues/35)
