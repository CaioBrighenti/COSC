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
--relcomp (rel1,rel2) val = flatten (map rel2 (rel1 val))

-- 5 - list helpers
inlist val [] = False
inlist val (first:rest) = if first == val
    then True
    else inlist val rest

noreplist [] = []
noreplist (first:rest) = if inlist first rest
    then noreplist rest
    else first : noreplist rest

--minlist [] = []
minlist [a] = a
minlist (first:second:rest) = if first > second  
    then minlist (second:rest)
    else minlist (first:rest)

sortlist [] = []
sortlist [a] = [a]
sortlist (first:rest) = if first <= (minlist rest)
    then first:(sortlist rest)
    else sortlist (rest++[first])

fixlist list = sortlist (noreplist list)
    
relcomp (rel1,rel2) val = fixlist (flatten (map rel2 (rel1 val)))
--6
reliden val = [val]
nfa ndspec = monext ndspec relcomp reliden
nfa1 = nfa ndspec1

--7
startndm ndspec string = nfa ndspec string 0

--8
-- find intersection of two lists
inter [] [] = []
inter [] list2 = []
inter list1 [] = []
inter (first:rest) list2 = if inlist first list2
    then fixlist (first : (inter rest list2))
    else fixlist (inter rest list2)
-- test if list is empty
isEmpty list = (list == [])

ndfa (ndspec,finalstates) string = not (isEmpty (inter finalstates (startndm ndspec string)))

--lab 6
addtoList val [] = [val]
addtoList val list = fixlist (list ++ [val])

--ndspec2
ndspec2 'a' 0 = [1,2]
ndspec2 'a' 1 = []
ndspec2 'a' 2 = [3]
ndspec2 'a' 3 = []
ndspec2 'b' 0 = [3]
ndspec2 'b' 1 = [2]
ndspec2 'b' 2 = []
ndspec2 'b' 3 = [0]

--nn
nndspec3 "a" 0 = [1]
nndspec3 "a" 1 = []
nndspec3 "a" 2 = [3]
nndspec3 "a" 3 = []
nndspec3 "b" 0 = []
nndspec3 "b" 1 = [2]
nndspec3 "b" 2 = []
nndspec3 "b" 3 = [3]
nndspec3 "" 2 = [0]

nnfa nndspec = monext nndspec relcomp reliden
nstartndm nndspec string = nnfa nndspec string 0
nndfa (nndspec,finalstates) string = not (isEmpty (inter finalstates (nstartndm nndspec string)))


{- Expressions test1, ... test12 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}
test1 = nfa1 "abbababbaba" 1
test2 = startndm ndspec1 "abbababbaba"
test3 = ndfa (ndspec1,[0,2]) "abbababbabaaaa"
test4 = ndfa (ndspec1,[0]) "ab"
test5 = startndm ndspec2 "bb"
test6 = startndm ndspec2 "abb"
test7 = ndfa (ndspec2,[2]) "a"
test8 = ndfa (ndspec2,[2]) "abab"
test9 = nstartndm nndspec3 ["a","b","a"]
test10 = nstartndm nndspec3 ["a","b","","a"]
test11 = nndfa (nndspec3,[2]) ["a","b","a","b"]
test12 = nndfa (nndspec3,[2]) ["a","b","","a","b"]
