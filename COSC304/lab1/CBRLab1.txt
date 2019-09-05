-- COSC 304 Fall 2019 
-- Lab1 Sept 3, 2019
-- CBRLab1 

{- Replace ABC above and below with your 3 email letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

-- 2) I put 7, [1,2,3], and 4/3 into the Haskell interpreter as examples of an integer, list, and real number.
-- In all three cases, the Haskell interpreter printed out exactly what I typed in.
-- 3) After using the :type operator, the Haskell interpret also printed out the data type of each of the examples
-- following the format :: TYPE a => a. For the integer, Haskell returned typed "num", for the set it returned "num"
-- but with a mapping of a => [a] instead, and for the real number it returned "fractional".
-- 4) This time Haskell outputted the ordered pair as desired, and made clear the data type was an ordered pair
-- consisting of a Fractional and a Num, mapping to a (a,b) pair.
-- 5) 
module CBRLab1 where 

-- 5
double x = 2*x
triple x = 3*x
-- 6
add1 (x,y) = x + y
add2 x y = x + y
-- 7
fact 0 = 1
fact x = x * (fact (x-1))
-- 8
boolval1 = 3 == 4
-- 9
first (x,y) = x
second (x,y) = y
--10 The first and second functions are both polymorphic, as they do not depend on the type and simply return
-- whatever data type was passed in. In this case, first and second returned the function daat type mapping. For
-- instance, add1 takes the form Num a => (a, a) -> a, as it takes two nums and outputs a single one.
-- 11 The type structure of listlength is [a] -> where p is a number, meaning it takes a list and returns a single integer.
listlength [] = 0
listlength (firstval:restoflist) = 1 + listlength restoflist

{- Expressions test1, ... test6 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}
test1 = double 3.4
test2 = add1(4,5)
test3 = add2 3.4 7.8
test4 = fact 6
test5 = listlength [listlength [2,3,4,5,6]]
test6 = second(add1, fact) (test5 + 4)



