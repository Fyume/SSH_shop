package zhku.jsj141.test;

public class testClass {
	private int test1=0;
	private String test2="";
	private long test3=1;
	private boolean test4=false;
	private testClass2 tc2;
	public int getTest1() {
		return test1;
	}
	public void setTest1(int test1) {
		this.test1 = test1;
	}
	public String getTest2() {
		return test2;
	}
	public void setTest2(String test2) {
		this.test2 = test2;
	}
	public long getTest3() {
		return test3;
	}
	public void setTest3(long test3) {
		this.test3 = test3;
	}
	public boolean isTest4() {
		return test4;
	}
	public void setTest4(boolean test4) {
		this.test4 = test4;
	}
	public testClass2 getTc2() {
		return tc2;
	}
	public void setTc2(testClass2 tc2) {
		this.tc2 = tc2;
	}
	@Override
	public String toString() {
		return "testClass [test1=" + test1 + ", test2=" + test2 + ", test3="
				+ test3 + ", test4=" + test4 + "]";
	}
	
}
