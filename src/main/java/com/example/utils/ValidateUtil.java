package com.example.utils;

public class ValidateUtil {
	/**
	 * 正则表达式验证密码
	 * @param input
	 * @return
	 */
	 public static boolean rexCheckPassword(String input) {
	 // 6-12 位，字母、数字、字符
	 //String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,12}$";
	 String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,12}$";
	 return input.matches(regStr);
	 }
	  public static boolean rexCheckEmail(String input) {
		 String regular ="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		 return input.matches(regular);
	  }
	 public static void main(String[] args){
	 System.out.println("rexCheckPassword is： "+ rexCheckPassword("14`~!@#$%^&*(\\)+=|{}"));
	 System.out.println("email = "+ rexCheckEmail("aewefweffehjseffeafa@3.com"));
	 }
}
