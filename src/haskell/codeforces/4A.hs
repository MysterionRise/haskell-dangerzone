import System.IO
import Control.Monad

readAInt :: IO Int
readAInt = readLn

main = do
    input <- getLine
    putStrLn $ solve $ read input

solve n = if (n > 2 && mod n 2 == 0) then "YES" else "NO"