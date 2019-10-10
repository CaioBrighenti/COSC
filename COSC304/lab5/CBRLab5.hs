-- COSC 304 Fall 2019 
-- Lab 5 October 1 and 8, 2019
-- CBRLab5 

{- Replace CBR above and below with your 3 letters, capped.
Then rename the file, again changing CBR with your 3 letters, capped. -}

module CBRLab5 where 

-- 1 - monext
monext f bin iden [] = iden
monext f bin iden (first:rest) = bin(f first, monext f bin iden rest)

-- 2 - setup spec
ndspec1 'a' 0 = [1,2]
ndspec1 'a' 1 = [2,3]
ndspec1 'a' 2 = [0,3]
ndspec1 'a' 3 = [0,1]
ndspec1 'b' 0 = [0]
ndspec1 'b' 1 = [1]
ndspec1 'b' 2 = [2]
ndspec1 'b' 3 = [3]

-- 3 - map test
double x = 2*x

-- 4 - relcomp
flatten [] = []
flatten (first:rest) = first ++ (flatten rest)
relcomp (rel1,rel2) val = flatten (map rel2 (rel1 val)):val

-- 5 - list helpers
inlist val [] = False
inlist val (first:rest) = if first == val
    then True
    else inlist val rest

noreplist [] = []
noreplist (first:rest) = if inlist first rest
    then noreplist rest
    else first : noreplist rest

minlist [] = []
minlist [a] = a
minlist (first:second:rest) = if first > second  
    then minlist (second:rest)
    else minlist (first:rest)


{- Expressions test1, ... test12 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}





