package auJava;

import java.util.Scanner;

public class TestStack {
	int size;
	int[] arr;
	int top;

	TestStack(int size, int top) {
		this.size = size;
		arr = new int[size];
		
		this.top = top;
	}

	public void push(int x) {
		top++;
		arr[top] = x;
	}

	public void pop() {
		arr[top] = 0;
		top--;
	}

	public int currentSize() {
		return top;
	}

	public boolean isEmpty() {
		return top != size ? true : false;
	}

	public boolean isFull() {
		return top == size ? true : false;
	}

	public int getTop() {
		return arr[top];
	}


	public static void main(String[] args) {

		int size;
		System.out.println("enter size of stack");
		Scanner in = new Scanner(System.in);
		size = in.nextInt();
		TestStack s1 = new TestStack(size, -1);

		int choice;
		do {
			System.out.println("press\n " + "1-> push\n" + "2->pop\n" + "3->top\n " + "4->current Size\n"
					+ "5->is Empty?\n" + "6->is Full?\n" + "7->printState\n" + "0->exit Menu");

			choice = in.nextInt();
			if (choice == 1) {
				if(s1.size == 0)
				{
					System.out.println("Cannot push stack size is zero");
					break;
				}
				if (s1.isEmpty()) {
					System.out.println("Enter the number you want to push");
					int number = in.nextInt();
					s1.push(number);
				}

			}
			if (choice == 2) {
				//System.out.println("Enter the number you want to pop");
				s1.pop();
			}
			if (choice == 3) {
				System.out.println(s1.getTop());
			}
			if (choice == 4) {
				System.out.println(s1.currentSize());
			}
			if (choice == 5) {
				System.out.println(s1.isEmpty());
			}
			if (choice == 6) {
				System.out.println(s1.isFull());
			}
			if (choice == 7) {
				for (int i = 0; i < s1.top+1; i++) {
					System.out.print(s1.arr[i] + " ");
					System.out.println();
				}
			}

		} while (choice != 0);

	}

}
