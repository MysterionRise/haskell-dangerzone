arr = $stdin.readline.split(" ")
n1 = arr[0].to_i
n2 = arr[1].to_i
puts n2 + 1 > n1 ? 'Second' : 'First'