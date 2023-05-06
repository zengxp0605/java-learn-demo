package com.stan.leetcode.simple;

/**
 * 125. 验证回文串 <https://leetcode.cn/problems/valid-palindrome/>
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * <p>
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 */
public class T0125_valid_palindrome {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s;
        boolean res;

//        示例 1：
//        输入: s = "A man, a plan, a canal: Panama"
//        输出：true
//        解释："amanaplanacanalpanama" 是回文串。
        s = "A man, a plan, a canal: Panama";
        res = solution.isPalindrome(s);
        assert res == true;

//        示例 2：
//        输入：s = "race a car"
//        输出：false
//        解释："raceacar" 不是回文串。
        s = "race a car";
        res = solution.isPalindrome(s);
        assert res == false;

//        示例 3：
//        输入：s = " "
//        输出：true
//        解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
//        由于空字符串正着反着读都一样，所以是回文串。
        s = "   ";
        res = solution.isPalindrome(s);
        assert res == true;


        byte[] bytes = "0P".getBytes();
        byte b1 = bytes[0];
        byte b2 = bytes[1];
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b2 - b1);
    }

    /**
     * 官方的双指针写法
     */
    static class Solution1 {
        public boolean isPalindrome(String s) {
            // 去除字母数字之外的字符
            s = s.replaceAll("[^a-zA-Z0-9]+", "");
            int n = s.length();

            // 双指针
            int left = 0, right = n - 1;
            while (left < right) {
                if (Character.toLowerCase(s.charAt(left))
                        != Character.toLowerCase(s.charAt(right))
                ) {
                    return false;
                }
                ++left;
                --right;
            }
            return true;
        }
    }

    /**
     * 官方解法
     */
    static class Solution {
        public boolean isPalindrome(String s) {
            StringBuffer sgood = new StringBuffer();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sgood.append(Character.toLowerCase(ch));
                }
            }
            int n = sgood.length();
            int left = 0, right = n - 1;
            while (left < right) {
                if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
            return true;
        }
    }
}
