# DS and Algorithms - HashTable, HashMap, or Dictionary
![hash_table](https://sarabostani-public.s3.amazonaws.com/ds-and-algo/img/hash_table.png)
## Overview
This assignment will focus on very basic CRUD operations of a hash table (map or dictionary) using linear probing.
## Instructions
Similar to other exercises, follow the comments in the source code and implement the code that is missing. If everything works correctly, you should be able to run `Main.java` and all tests should pass as well.

There is a private method in `HashMap.java` called `linearProbe(String key)`. Since you need to perform a linear probe for 
each method, **optionally** consider completing that method and use it in `get`, `update`, and `delete`. The method `put` is slightly
different since it puts items in the spots where a previous item has been "deleted".
You may modify `linearProbe` (e.g. with a boolean flag) to account for that fact and re-use it for the `put` method as
well or re-implement linear probe in the `put` method.
