/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fidelity.banking.mobile.functions;

/**
 *
 * @author administrator
 */
public class MathUtil {
    private MathUtil(){
        
    }
    
    public static int compareDoubles(String left, String right){
        int result = -1;
        
        if(left != null){
            if(right != null){
                int leftWhole = getWholeSide(left);
                int leftDecimal = getDecimalSide(left);
                int rightWhole = getWholeSide(right);
                int rightDecimal = getDecimalSide(right);
                
                if(leftWhole > rightWhole){
                    result = 1;
                }else if(rightWhole > leftWhole){
                    result = -1;
                }else{
                    if (leftDecimal > rightDecimal){
                        result = 1;
                    }else if (rightDecimal > leftDecimal){
                        result = -1;
                    }else{
                        result = 0;
                    }
                }
            }else{
                result = 1;
            }
        }
        
        return result;
    }
    
    public static int getWholeSide(String decNumber){
        int result = 0;
        StringBuffer addNum = new StringBuffer();
        char []nums = decNumber.toCharArray();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == '.'){
                break;
            }
            addNum.append(nums[i]);
        }
        if(addNum.length() > 0){
            result = Integer.valueOf(addNum.toString()).intValue();
        }
        
        return result;
    }
    
    /**
     * <p>This method evaluated a double or float and returns the decimal or fraction side of it.<br/>
     * It presumes that the string is a floating point number </p>
     * @param decNumber The double or float value to get the decimal or fraction side of
     * @return The decimal side of the double or float value parsed
     */
    public static int getDecimalSide(String decNumber){
        int result = 0;
        StringBuffer addNum = new StringBuffer();
        char []nums = decNumber.toCharArray();
        for(int i = nums.length - 1; i >= 0; i--){
            if(nums[i] == '.'){
                break;
            }
            addNum.append(nums[i]);
        }
        if(addNum.length() > 0){
            addNum.reverse();
            result = Integer.valueOf(addNum.toString()).intValue();
        }
        
        return result;
    }
    
    public static void main(String [] args){
        System.out.println("getDecimalSide - value = 2334.34421: " + getDecimalSide("2334.34421"));
        System.out.println("getWholeSide - value = 2334.34421: " + getWholeSide("2334.34421"));
        System.out.println("compareDoubles - values = 2334.34421, 233.9944: " + compareDoubles("2334.34421", "233.9944"));
    }
            
}
