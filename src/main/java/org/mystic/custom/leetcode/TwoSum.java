import java.util.HashMap;
import java.util.Map;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        final int n = nums.length;
        final Map<Integer, Integer> ind = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (ind.containsKey(target - nums[i])) {
                return new int[]{i, ind.get(target - nums[i])};
            }
            ind.put(nums[i], i);
        }
        return null;
    }
}
