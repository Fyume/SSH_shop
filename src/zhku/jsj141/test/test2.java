package zhku.jsj141.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
class ThreadA extends Thread{
	public ThreadA(String name){
		super(name);
	}
	public void run(){
		 synchronized (this) {  
	            try {                 
	            	System.out.println("Thread.sleepb");
	                Thread.sleep(3000); //  使当前线阻塞 1 s，确保主程序的 t1.wait(); 执行之后再执行 notify() 
	                System.out.println("Thread.sleepa");
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }             
	            System.out.println(Thread.currentThread().getName()+" call notify()");  
	            // 唤醒当前的wait线程  
	            this.notify();  
	        }  
	    }  
}
public class test2 {
	
	
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		  synchronized(t1) {  
	            try {  
	                t1.start();  
	                long date1 = new Date().getTime();
	                System.out.println("date1:"+date1);
	                t1.wait();  //  不是使t1线程等待，而是当前执行wait的线程等待  
	                long date2 = new Date().getTime();
	                System.out.println("date2:"+date2);
	                System.out.println(new SimpleDateFormat("HH:mm:ss").format(date2-date1));
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
}
