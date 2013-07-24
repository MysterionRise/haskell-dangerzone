main =  do  
        [n, m, a] <- (map read . words) `fmap` getLine
	print (ceiling(n / a) * ceiling (m / a) )
