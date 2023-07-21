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

## Assembler Instructions

> Addressing Instructions: @value
> + where the value is a non-negative decimal number

> Computing Instructions: dest=comp;jump
> + either the dest or jump fields may be empty

## TODO-List

- [ ] List out all the instructions in assembly and machine code to estimate workload

- [ ] Set up development environment