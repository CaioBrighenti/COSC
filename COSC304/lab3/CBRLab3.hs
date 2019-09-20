-- COSC 304 Fall 2019 
-- Lab 3 Sept 17, 2019 
-- CBRLab3 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module CBRLab3 where 

-- add
add (x,y) = x + y
times (x,y) = x * y

-- monext
monext f bin iden [] = iden
monext f bin iden (first:rest) = bin(f first, monext f bin iden rest)
f num1 = 3*num1 + 2
g num1 = num1 + 1

-- faspec1
faspec1 'a' 0 = 1
faspec1 'a' 1 = 2
faspec1 'a' 2 = 0
faspec1 'b' 0 = 0
faspec1 'b' 1 = 2
faspec1 'b' 2 = 2
faspec1 'c' 0 = 2
faspec1 'c' 1 = 0
faspec1 'c' 2 = 1

-- comp
comp (f,g) val = monext g : [] (monext f : )

{- Expressions test1, ... test7 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}
test1 = monext f add 0 [3,4,7]
test2 = monext g times 1 [2,4,1]
