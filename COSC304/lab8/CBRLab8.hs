-- COSC 304 Fall 2019 
-- Lab 8 November 5&7, 2019
-- CBRLab8 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module CBRLab8 where 

-- 1
spec1 (0,'#') = (0,'#','l')
spec1 (0,'a') = (1,'a','l')
spec1 (0,'b') = (1,'a','l')
spec1 (0,'c') = (1,'a','l')
spec1 (0,'d') = (1,'a','l')
spec1 (1,'#') = (2,'#','r')
spec1 (1,'a') = (1,'b','l')
spec1 (1,'b') = (1,'a','l')
spec1 (1,'c') = (1,'a','l')
spec1 (1,'d') = (1,'a','l')
spec1 (2,'#') = (100,'#','d')
spec1 (2,'a') = (2,'a','r')
spec1 (2,'b') = (2,'b','r')
spec1 (2,'c') = (2,'c','r')
spec1 (2,'d') = (2,'d','r')

-- 2
val (first:rest,0) = first
val (first:rest,pos) = val (rest,pos-1) 
test1 = val ("abcd",2)

listlength [] = 0
listlength (firstval:restoflist) = 1 + listlength restoflist

startstring str = '#':str ++ ['#']
startpos str = (listlength str) + 1

str1 = "abcd"
test2 = startstring str1
test3 = val (startstring str1, startpos str1)

--3
chstr (first:rest,ch,0) = ch:rest
chstr (first:rest,ch,pos) = first:(chstr (rest,ch,pos-1))
test4 = chstr (str1,'z',2)

--4
move (str,state,ch,pos) = let (newstate,overwritech,dir) = spec1 (state,ch) in
                        if (dir == 'r') then (chstr (str,overwritech,pos),newstate,val (str,pos+1),pos+1) else
                        if (dir == 'l') then (chstr (str,overwritech,pos),newstate,val (str,pos-1),pos-1) else
                            (chstr (str,overwritech,pos),newstate,overwritech,pos)
test5 = move (str1,0,'d',3)
test6 = move (str1,1,'c',2)
test7 = move (move (str1,0,'d',3))
 
{- Expressions test1, ... test10 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}





