package test;

import util.StringUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println(StringUtil.getRandomString(16));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
