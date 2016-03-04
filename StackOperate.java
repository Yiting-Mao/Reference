package stack_PB11210041;

import java.util.Scanner;

class stack {
	private int  current; //Õ»¶¥
	private int length;  //Õ»³¤
	private int[] StackArray; //Õ»
	public int GetCurrent(){return current;}

	public stack(int a){
		if(a==0){
			StackArray= new int[16];
		current=0;
		length=16;
		}
		else{
		StackArray= new int[a];
		current=0;
		length=a;
		}
	}
	public void InNum(int a){ //ÊäÈëÔªËØ
		if(current==length){
			StackArray=ArrayExp();
			length=length*2;
		}
		StackArray[current]=a;
		current++;		
	}
	public int OutNum(){ //µ¯³öÔªËØ
	
		int a=StackArray[current-1];
		current--;
		return a;
		
	}
	
	public boolean WhetherEmpty(){ //ÅĞ¶ÏÕ»ÊÇ·ñÎª¿Õ
		if(current==0)return true;
		else return false;
	}
	private int[] ArrayExp(){ //À©³äÕ»
		int[] copy=new int[length*2];
		System.arraycopy(StackArray,0,copy,0,length);
		return copy;
	}
}

public class StackOperate{
	public static void main(String args[]){
		Scanner scan= new Scanner(System.in);
		int a;
		System.out.println("you can only input integers in this application");
		System.out.println("enter a number to define stack's volumn, enter 0 to skip");
		a=scan.nextInt();
	
		stack ss=new stack(a);
		do{
		System.out.println(" enter 1 to see whether the stack is empty\n enter 2 to put one integer into the stack\n enter 3 to get the top integer\n enter 4 to see how many numbers in the stack");
		a=scan.nextInt();
		if(a==1){
			boolean b=ss.WhetherEmpty();
			if(b==true)System.out.println("The stack is empty");
			else System.out.println("The stack is not empty");
		}
		else if(a==2){
			System.out.println("input integer");
			int b=scan.nextInt();
			ss.InNum(b);
		}
		else if(a==3){
			boolean b=ss.WhetherEmpty();
			if(b==true)System.out.println("The stack is empty");
			else{
				System.out.print(ss.OutNum());
				System.out.println("");
			}
			
		}
		else if(a==4){
			int b=ss.GetCurrent();
			System.out.println("There are "+b+" numbers in the stack");
		}
		else System.out.println("Wrong input");
		System.out.println("enter 1 to continue, enter other numbers to stop");
		a=scan.nextInt();
		}while(a==1);
		scan.close();
	}
}
