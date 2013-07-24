
pairFromList x y = if null x || length x == 1 then y else pairFromList (init (tail x)) ((head x, last x) : y)
makePairs x = pairFromList x []

quicksort [] = []
quicksort (x:xs) = 
	let smaller = quicksort $ filter (<=x) xs
	    bigger = quicksort $ filter (>x) xs
	in smaller ++ [x] ++ bigger


