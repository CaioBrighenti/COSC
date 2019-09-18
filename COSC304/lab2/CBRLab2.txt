-- COSC 304 Fall 2019 
-- Lab2 Sept 10, 2019
-- CBRLab2 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module CBRLab2 where 

-- 1
listlength [] = 0
listlength (firstval:restoflist) = 1 + listlength restoflist
listsum [] = 0
listsum (firstval:restoflist) = firstval + listsum restoflist

-- 2
data Nat = Z | S Nat deriving (Read, Show, Eq)
-- addition
add nat1 Z = nat1
add nat1 (S nat2) = add (S nat1) nat2 
-- multiplication
mult nat1 Z = Z
mult nat1 (S nat2) =  add nat1 (mult nat1 nat2)
-- minus
minus nat1 Z = nat1
minus Z nat2 = Z
minus (S nat1) (S nat2) = minus nat1 nat2

-- 3
nattoInt Z = 0
nattoInt (S nat) = 1 + nattoInt nat
buildNat 0 = Z
buildNat int = S (buildNat (int - 1))
two = buildNat 2
four = buildNat 4

--4
-- less than
lt Z Z = False
lt Z nat2 = True
lt nat1 Z = False
lt (S nat1) (S nat2) = lt nat1 nat2 
-- less than or equal to
lte Z nat2 = True
lte nat1 Z = False
lte (S nat1) (S nat2) = lte nat1 nat2

--5 
three = buildNat 3
five = buildNat 5

--6
factNat Z = (S Z)
factNat nat = mult nat (factNat (minus nat (S Z))) 

--7
addA [] = []
addA (first:rest) = (first ++ "a"):(addA rest)
astar = "" : (addA astar) 

{- Expressions test1, ... test10 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}
test1 = buildNat 4
test2 = nattoInt(S(S(S(S(S(S Z))))))
test3 = add three five
test4 = mult three five
test5 = minus five three
test6 = lte three five
test7 = factNat(S(S(S Z)))
test8 = nattoInt(factNat(buildNat 5))
test9 = addA ["cat","dog"]
test10 = take 6 astar


