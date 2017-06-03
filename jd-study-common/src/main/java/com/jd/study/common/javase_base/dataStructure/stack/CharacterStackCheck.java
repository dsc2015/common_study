package com.jd.study.common.javase_base.dataStructure.stack;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * @description 利用栈是特性判断代码中的括号是否匹配
 * @author dushuangcheng
 * @create 2017/3/14
 */
public class CharacterStackCheck {
    private String inputStr;

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    /**
     * 检验操作
     */
    public boolean isFixWell(String input){
        //使用栈
        StackX stackX = new StackX(input.length(), new char[input.length()]);
        //开始校验字符串
        for(int i=0;i<input.length();i++){
            char ch=input.charAt(i);
            //判断字符形式
            switch (ch){
                case '{':
                case '[':
                case '(':
                    //放入栈中
                    stackX.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    //这样的需要跟栈中的
                    if(!stackX.isEmpty()){
                        char chS=stackX.pop();
                        //比较
                        if((ch=='}'&&(chS!='{'))||(ch==']'&&(chS!='['))||(ch==')'&&(chS!='('))){
                            System.out.println("error!"+ch+"at"+i);
                            return false;
                        }
                    }
                    else{
                        System.out.println("error!"+ch+"at"+i);
                        break;
                    }

                    default:
                        break;
            }
        }
        //循环完毕若栈不为空，那么肯定就是错误的
        if(!stackX.isEmpty()){
            System.out.println(" the character is not match ,the character is"+stackX.pop());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CharacterStackCheck charCheck = new CharacterStackCheck();
        charCheck.setInputStr("a{b[c]c");
        boolean fixWell = charCheck.isFixWell(charCheck.getInputStr());
        System.out.println("------------------------->"+fixWell);
    }
}
