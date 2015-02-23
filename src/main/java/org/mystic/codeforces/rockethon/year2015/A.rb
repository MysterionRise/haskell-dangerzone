arr = $stdin.readline.split(" ")
n1=  Float("#{arr[0]}")
n2 = Float("#{arr[1]}")
k1 = Float("#{arr[2]}")
k2 = Float("#{arr[3]}")
if (n2 + 1 > n1 )
    puts "Second"
else
    puts "First"
end