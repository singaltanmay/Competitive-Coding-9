// Time Complexity : O(n*l)
// Space Complexity : O(n*l)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
class Solution {

    private String[] variants(String s){
        String[] result = new String[s.length()];
        for(int i = 0; i < s.length(); i++){
            result[i] = (i-1 > -1 ? s.substring(0, i) : "") + "*" + (i+1 < s.length() ? s.substring(i+1): "");
        }
        return result;
    }

    private void addVariantChildrenToQueue(String variant, Queue<String> q, Map<String, List<String>> map, Set<String> seen){
        if(!map.containsKey(variant)) return;
        List<String> strings = map.get(variant);
        while(strings.size() > 0){
            String s = strings.get(0);
            if(!seen.contains(s)){
                q.offer(s);
                seen.add(s);
            }
            strings.remove(0);
        }
        map.remove(variant);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String, List<String>> map = new HashMap<>();

        // init map
        for(String s: wordList){
            for(String variant: variants(s)){
                if(!map.containsKey(variant)) map.put(variant, new LinkedList<>());
                map.get(variant).add(s);
            }
        }

        // Start BFS
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        seen.add(beginWord);

        int depth = 1;

        while(!q.isEmpty()){
            // level
            int size = q.size();
            while(size-- > 0){
                String s = q.poll();
                if(s.equals(endWord)) return depth;
                String[] variantStrings = variants(s);
                for(String variant: variantStrings) addVariantChildrenToQueue(variant, q, map, seen);
            }
            depth++;
        }

        return 0;
    }
}
