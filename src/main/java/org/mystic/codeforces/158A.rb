input = $stdin.readlines
arr = input[0].split(" ")
n = arr[0].to_i
k = arr[1].to_i
a = input[1].split(" ")
i = 1
ans = 0
prev = -1
while i <= n
    ai = a[i - 1].to_i
    if i == k && ai > 0
        prev = ai
        ans +=1
    end
    if i > k && ai == prev && ai > 0
        ans +=1
    end
    if i < k && ai > 0
        ans += 1
    end
    i += 1
end
puts ans