# N2T-Assembly
Assembler implementation based on Nand2Tetris book.

## Dev-Notes

Current plan is to be using forth and/or C.

## Machine Code Instructions

16-bit instruction sets:

+ Addressing Instructions

> 0XXX XXXX XXXX XXXX

+ Computing Instructions

> 111A CCCC CCDD DJJJ

a = 0 | c1 c2 c3 c4 c5 c6 | a = 1
 ---  |        ---        |  ---
   0  |  1  0  1  0  1  0 | 
   1  |  1  1  1  1  1  1 | 
  -1  |  1  1  1  0  1  0 | 
   D  |  0  0  1  1  0  0 | 
   A  |  1  1  0  0  0  0 |    M 
  !D  |  0  0  1  1  0  1 | 
  !A  |  1  1  0  0  0  1 |   !M 
  -D  |  0  0  1  1  1  1 | 
  -A  |  1  1  0  0  1  1 |   -M 
 D+1  |  0  1  1  1  1  1 | 
 A+1  |  1  1  0  1  1  1 |  M+1 
 D-1  |  0  0  1  1  1  0 | 
 A-1  |  1  1  0  0  1  0 |  M-1 
 D+A  |  0  0  0  0  1  0 |  D+M 
 D-A  |  0  1  0  0  1  0 |  D-M 
 A-D  |  0  0  0  1  1  1 |  M-D 
 D&A  |  0  0  0  0  0  0 |  D&M 
D\|A  |  0  1  0  1  0  1 | D\|M 

dest | d1 d2 d3
 --- |    ---
null |  0  0  0
  M  |  0  0  1
  D  |  0  1  0
 MD  |  0  1  1
  A  |  1  0  0
 AM  |  1  0  1
 AD  |  1  1  0
AMD  |  1  1  1 

jump | j1 j2 j3
 --- |    ---
null |  0  0  0
 JGT |  0  0  1
 JEQ |  0  1  0
 JGE |  0  1  1
 JLT |  1  0  0
 JNE |  1  0  1
 JLE |  1  1  0
 JMP |  1  1  1

## Assembler Instructions

> Addressing Instructions: @value
> + where the value is a non-negative decimal number

> Computing Instructions: dest=comp;jump
> + either the dest or jump fields may be empty

## TODO-List

- [ ] List out all the instructions in assembly and machine code to estimate workload

- [ ] Set up development environment