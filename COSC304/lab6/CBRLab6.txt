-- COSC 304 Fall 2019 
-- Lab 6 October 29, 2019
-- CBRLab6 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module CBRLab6 where 


{- Expressions test1, ... test6 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}

-- 1 - preliminary functions
-- monext
monext f bin iden [] = iden
monext f bin iden (first:rest) = bin(f first, monext f bin iden rest)
-- comp
comp(f, g) val = g(f val)
iden val = val
-- inlist
inlist val [] = False
inlist val (first:rest) = if first == val
    then True
    else inlist val rest


-- 2 - machine spec
spec1 'a' (0,stack) = (0,'a':stack)
spec1 'b' (0,stack) = (0,'b':stack)
spec1 'c' (0,stack) = (1,stack)
spec1 'a' (1,'a':stack) = (1,stack) 
spec1 'b' (1,'b':stack) = (1,stack)

test1 = spec1 'b' (spec1 'a' (0,""))

-- 3 - full specification
final1 = [1]
pdaspec1 = (spec1,final1)

-- 4 - pdacreate
first (x,y) = x
second (x,y) = y
pdacreate pdaspec = (monext (first pdaspec) comp iden, second pdaspec)
pda1 = pdacreate pdaspec1
test2 = first pda1 "abbbac" (0,"")

-- 5 - 
pdacomp pda string = first pda string (0,"")
test3 = pdacomp pda1 "abbacab"

-- 6 - 
pdatest pda string = (inlist (first (pdacomp pda string)) (second pda)) && ((second (pdacomp pda string)) == "")
test4 = pdatest pda1 "abbcbba"
-- Using the string inputs abbacbbab and abbacbbab causes the machine to crash as neither are in the language
-- of this pda, which accepts only palindromes around

-- 7 -
spec2 'x' (0,stack) = (1,'c':stack)
spec2 'a' (1,'c':stack) = (1,'a':'c':stack)
spec2 'a' (1,'a':stack) = (1,'a':'a':stack)
spec2 'a' (1,'b':stack) = (1,stack)
spec2 'b' (1,'c':stack) = (1,'b':'c':stack)
spec2 'b' (1,'b':stack) = (1,'b':'b':stack)
spec2 'b' (1,'a':stack) = (1,stack)
spec2 'z' (1,'c':stack) = (2,stack)
spec2 'z' (1,stack) = (2,stack)
pdaspec2 = (spec2,[2])
pda2 = pdacreate pdaspec2
test5 = pdacomp pda2 "xaabbabaz"
test6 = pdatest pda2 "xaabbabaz"