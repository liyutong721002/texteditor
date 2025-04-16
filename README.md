# CSC 207: Text Editor

**Author**: Annie Li

## Resources Used

+ NetBeans
+ Java"23.0.2"
+ https://osera.cs.grinnell.edu/ttap/data-structures-labs/text-editor.html

## Changelog


1. The code passes all autograder tests. 
2. Fix deleting characters and adding to the middle of the string without overwriting.
3. Fix style errors

commit 9057ab5f6fd29b22a232a140e18da6790d13b203 (HEAD -> main, origin/main)
Author: yutong <yutong@localhost>
Date:   Fri Feb 28 16:51:30 2025 -0600

    Main Method Update

commit cb166a4c6ec480a12de7b72efdf12cc4e3f1527a
Author: yutong <yutong@localhost>
Date:   Fri Feb 28 16:47:33 2025 -0600

    GapBuffer and its Tests completed                                                                                                               
                                                                                                                                                    
commit 4889f7a31d73bf6a833184c1929598b03990332b                                                                                                     
Author: yutong <yutong@localhost>                                                                                                                   
Date:   Sat Feb 22 14:30:57 2025 -0600                                                                                                              
                                                                                                                                                    
    SimpleStringBuffer Tests and GapBuffer Tests Complete.                                                                                          
                                                                                                                                                    
commit 45ae66a41557fa24939de81cc79d1647f1acbfcc                                                                                                     
Author: yutong <yutong@localhost>                                                                                                                   
Date:   Sat Feb 22 14:29:08 2025 -0600                                                                                                              
                                                                                                                                                    
    GapBuffer and its Tests completed                                                                                                               
                                                                                                                                                    
commit 71f5472612c7fde046e248c6d06731618b257ce3                                                                                                     
Author: yutong <yutong@localhost>                                                                                                                   
Date:   Fri Feb 21 15:42:35 2025 -0600                                                                                                              
                                                                                                                                                    
    SimpleStringBuffer Tests completed                                                                                                              
                                                                                                                                                    
commit c5f0a169bf40c023feb295cf0068f0ba93530eb5                                                                                                     
Author: yutong <yutong@localhost>                                                                                                                   
Date:   Fri Feb 14 13:34:54 2025 -0600                                                                                                              
                                                                                                                                                    
    SimpleStringBuffer Completed                                                                                                                    
                                                                                                                                                    
commit 93d54471994d9500c8c3fdc6d8c8eab0df6dc20e                                                                                                     
Author: yutong <yutong@localhost>                                                                                                                   
Date:   Fri Feb 14 13:22:15 2025 -0600                                                                                                              
                                                                                                                                                    
    SimpleStringBuffer completed                                                                                                                    
                                                                                                                                                    
commit 32a90495f40bd92ce905d4d78fbdab4dbaa6d5f9                                                                                                     
Author: Peter-Michael Osera <osera@cs.grinnell.edu>                                                                                                 
Date:   Thu Feb 13 12:40:05 2025 -0600                                                                                                              
                                                                                                                                                    
    Project files                                                                                                                                   
                                                                                                                                                    
commit 02dc92144ecc088bcefb4a9798df0934efe300c1                                                                                                     
Author: Peter-Michael Osera <osera@cs.grinnell.edu>                                                                                                 
Date:   Thu Feb 13 12:39:53 2025 -0600                                                                                                              
                                                                                                                                                    
    initial commit                                                                                                                                  
(END)                                                                                                                                               



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
