-- COSC 304 Fall 2019 
-- Lab9 Nov 12&14, 2019
-- ABCLab9 

{- Replace ABC above and below with your 3 letters, capped.
Then rename the file, again changing ABC with your 3 letters, capped. -}

module ABCLab9 where 
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

listlength [] = 0
listlength (firstval:restoflist) = 1 + listlength restoflist

startstring str = '#':str ++ ['#']
startpos str = (listlength str) + 1

str1 = "abcd"

--3
chstr (first:rest,ch,0) = ch:rest
chstr (first:rest,ch,pos) = first:(chstr (rest,ch,pos-1))

--4
newmove tmspec (str,state,ch,pos) = let (newstate,overwritech,dir) = tmspec (state,ch) in
                        if (dir == 'r') then (chstr (str,overwritech,pos),newstate,val (str,pos+1),pos+1) else
                        if (dir == 'l') then (chstr (str,overwritech,pos),newstate,val (str,pos-1),pos-1) else
                            (chstr (str,overwritech,pos),newstate,overwritech,pos)
--5
newrun tmspec (str,state,ch,pos) = if (state == 100) 
                        then (str,state,ch,pos) 
                        else newrun tmspec (newmove tmspec (str,state,ch,pos))

--6
startrun tmspec str = newrun tmspec (startstring str, 0, '#', startpos str)


{- Expressions test1, ... test6 ARE DEFINED IN THIS FILE, NOT IN THE TEST FILE -}





