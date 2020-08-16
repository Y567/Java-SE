package com.gyy.找出最小数字;

import java.util.Scanner;
import java.util.Stack;

public class 找出最小数字 {


    /**
     * 思路：利用栈来进行移除K个数字，当入栈数字比栈顶元素小时，将栈顶元素弹出
     */

    public static String getMin(String s,int k){
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i < chars.length;i++){
            int c = Integer.valueOf(String.valueOf(chars[i]));
            if(k == 0){
                //说明已经删除完k个数字了,直接将剩下的添加进去就好
                stack.push(c);
                continue;
            }
            if(stack.empty()){
                //如果栈为空，直接添加
                stack.push(c);
            }else if(stack.peek() < c){
                //栈中有数据，那么就拿出来比较,小于就继续入栈
                stack.push(c);
            }else{
                stack.pop();
                k--;
                i--;
            }
        }
        //取出栈中的数据
        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            String k = in.nextLine();
            System.out.println(getMin(s,Integer.valueOf(k)));
        }
    }
}
