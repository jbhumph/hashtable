# Spell Check
Spell Check program using a unique Hashmap agorithm and Levenshtein Distance algorithm for CS240 class

## Author
John Humphrey

## Contents
- [Author](#author)
- [Contents](#contents)
- [Overview](#overview)
- [Instructions](#instructions)
- [About](#about)
- [Attributions](#attributions)
- [Screenshots](#screenshots)

## Overview
Basic spell check program. Enter a word and the program will tell you if it's spelled correctly. If it is incorrect, the program will offer you several suggestions. This will continue until you quit the program.


## Instructions
This program can be run straight from your terminal or your IDE.


## About
This program implements a Hash map as it's primary data structure. It hashes the values of an english dictionary with 10,000 words. The program first compares against the hash map. If not in the map, the program then proceeds to recommend several other words.

This was achieved using the Levenshtein Distance algorithm. It has a time complexity of O(m*n) where m is the length of str1 and n is the length of str2 in the comparison. 

The program then selects from the 5 lowest returned values (the least distance from the input word) and presents them as suggestions.


## Attributions

Docu-Scan ASCII art is Swamp Land from [Patorjk](https://patorjk.com/software/taag/#p=display&f=Swamp%20Land&t=Docu-Scan)

10000 word example dictionary is from [MIT Wordlist](https://www.mit.edu/~ecprice/wordlist.10000)


## Screenshots
