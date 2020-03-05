-- COSC 304 Fall 2019 
-- Lab11 Dec 3&5, 2019
-- CBRLab11 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module CBRLab11 where 

--1
helperab [] = []
helperab (first:rest) = (('a':first)++"b"):(helperab rest)
listab = "" : (helperab listab) 

--2
helperc [] = []
helperc (first:rest) = (first++"c"):(helperc rest)
listc = "" : (helperc listc)

--3
mergelists [] [] = []
mergelists [] (first2:rest2) = first2:rest2
mergelists (first1:rest1) [] = first1:rest1
mergelists (first1:rest1) (first2:rest2) = (first1++first2):(mergelists rest1 rest2)

--4
listabc = mergelists listab listc 

--5
helperabc [] = []
helperabc (first:rest) = (('a':first)++"bc"):(helperabc rest)

--6
mixedabc = "" : (helperabc mixedabc)

--7
swap [] = []
swap (first:rest) = if first == 'c'
then (swap rest) ++ "c"
else first:(swap rest)


swaplist [] = []
swaplist (first:rest) = (swap first):(swaplist rest)

--8
listabc1 = swaplist mixedabc


{- Expressions test1, ... test9 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}
test1 = helperab ["cat","dog"]
test2 = take 5 listab
test3 = take 5 listc
test4 = mergelists ["ab","cd","ef","g"] ["cat","dog","bird"]
test5 = take 5 listabc
test6 = helperabc ["cat","dog"]
test7 = take 5 mixedabc
test8 = swap "aaaabcbcbcbc"
test9 = take 5 listabc1




