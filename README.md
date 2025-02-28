# CSC 207: Text Editor

**Author**: Annie Li

## Resources Used

+ NetBeans
+ Java"23.0.2"
+ https://osera.cs.grinnell.edu/ttap/data-structures-labs/text-editor.html

## Changelog

_(TODO: fill me in with a log of your committed changes)_


## Part 2: Analyzing the Simple String Buffer
    public void insert(char ch) {
        String temp = "";
        for (int i = 0; i < cursor; i++) {
            temp += buffer.charAt(i);
        }
        temp += ch;
        for (int i = cursor + 1; i < buffer.length(); i++) {
            temp += buffer.charAt(i);
        }
        cursor++;
        buffer = temp;
    }

The relevant input to the insert function is char ch and 
the input to my math model is the length of the String buffer.

The critical operations are access the char of the buffer, 
assignment to temp and assignment to the buffer.

Assume n is the length of the buffer and c is the position of cursor
The first loop, where we copy first c chars to temp, has the runtime of c.
The second loop, where we copy chars after the cursor to temp, has the runtime of n-c.
We also assign ch to temp at the cursor position and assign temp to buffer. Thus, 
the runtime is n+2.

Therefore, our Big-O is O(n).
