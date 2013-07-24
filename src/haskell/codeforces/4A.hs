import System.IO
import Control.Monad

readAInt :: IO Int
readAInt = readLn

main = do
	w <- getLine
	--putStrLn $ solve ( read w :: Int)
	putStrLn w

solve w 
	| w mod 2 == 0 	= "YES"
	| otherwise 	= "NO"

