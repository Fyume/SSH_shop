package zhku.jsj141.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class test {
	Logger log = Logger.getLogger(test.class);
	public test(){
		System.out.println("log log");
	}
	public static void main(String[] args) {
		PropertyConfigurator.configure("/SSH_test/src/log4j2.properties");
		test t = new test();
	}
}
