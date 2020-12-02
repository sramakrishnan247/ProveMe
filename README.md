# ProveMe: A Theorem Prover for Propositional Logic
![Java CI with Maven](https://github.com/sramakrishnan247/ProveMe/workflows/Java%20CI%20with%20Maven/badge.svg)

### About
This project proves sentences by [resolution](https://en.wikipedia.org/wiki/Resolution_(logic)) refutation given an input Knowledge base.

### Requirements 🔧
* Java version 8 or higher.

### Installation 🔌
1. Press the **Fork** button (on the top right corner of the page) to save a copy of this project on your account.

2. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:

       git clone https://github.com/sramakrishnan247/ProveMe
3. Import the project using Intellij Ide(or any other Ide or your favorite text editor)
4. Save your file in "./src/main/resources/your_file.txt";
5. Give your filename and run the application from Main.java :D

### Input format:
The input file starts with a line “Knowledge Base:” and then follows with sentences separated by blank lines. Once the KB is loaded, add the sentences that reads "Prove the following sentences by refutation:" followed by the sentences that need refutation. You can find sample input files in src/main/resources/.


### Sample run

### Input

```
Knowledge Base:

( Rain && Outside ) => Wet

( Warm && ~Rain ) => Pleasant

~Wet

Outside

Warm

Prove the following sentences by refutation:

Pleasant

Rain
```

### Output
```
KB expressions
( Rain && Outside ) => Wet
( Warm && ~Rain ) => Pleasant
~Wet
Outside
Warm

Sentences to be proved:
Pleasant
Rain

Theorem Prover working...
-------------------------------------------------



Knowledge base
[~Rain v ~Outside v Wet , ~Warm v Rain v Pleasant , ~Wet , Outside , Warm ]

To prove: Pleasant

Resolution as follows...

Resolving...
~Rain v ~Outside v Wet 
~Warm v Rain v Pleasant 
------------------------------------------
~Outside v Wet v ~Warm v Pleasant 

Resolving...
~Pleasant 
~Outside v Wet v ~Warm v Pleasant 
------------------------------------------
~Outside v Wet v ~Warm 

Resolving...
~Outside v Wet v ~Warm 
Outside 
------------------------------------------
Wet v ~Warm 

Resolving...
Wet v ~Warm 
Warm 
------------------------------------------
Wet 

Resolving...
Wet 
~Wet 
------------------------------------------


KB entails: Pleasant




Knowledge base
[~Rain v ~Outside v Wet , ~Warm v Rain v Pleasant , ~Wet , Outside , Warm ]

To prove: Rain

Resolution as follows...

Resolving...
Outside 
~Rain v ~Outside v Wet 
------------------------------------------
~Rain v Wet 

Resolving...
~Wet 
~Rain v Wet 
------------------------------------------
~Rain 

Resolving...
~Warm v Rain v Pleasant 
~Rain 


------------------------------------------
~Warm v Pleasant 

Resolving...
~Warm v Pleasant 
Warm 
------------------------------------------
Pleasant 

KB contains: 
[~Rain , Pleasant ]
No new clauses were added to KB
FAILURE!!
KB does not entail: Rain


[true, false]
```

### Implementation outline
* Input parser 
* Postfix conversion
* Expression Tree construction
* Literal, clause and expression utilities.
* Resolution algorithm.

### Thank You!
If you found this useful, please ⭐️ this repo and share it with others!

### References
[Resolution Theorem Proving](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-825-techniques-in-artificial-intelligence-sma-5504-fall-2002/lecture-notes/Lecture7FinalPart1.pdf)

