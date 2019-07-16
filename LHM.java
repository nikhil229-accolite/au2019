package auJava;

import java.util.Scanner;

public class LHM {
	

		private Node[] hash_table;
		private int size;
		private Node header;
		private Node last;
		
		public LHM(int size) {
			this.size = size;
			hash_table = new Node[size];
		}
		
		static class Node{
			int key, value;
			Node after, before, next;
			
			public Node(int key, int value, Node next)
			{
				this.key =  key;
				this.value= value;
				this.next = next;
			}
		}
		
		
		public void display()
		{
			Node temp =  header;

			
			for(int i =0;i<size;i++)
			{
				//System.out.println();
				System.out.print(i+"->");
				temp = hash_table[i];
				if(temp==null)
					System.out.println();
				while(temp!=null)
				{
					System.out.println(temp.value);
					temp =  temp.next;
				}
				
			}
		}
		public void put(int key, int value)
		{
			if(key <0)
				return;
			
			int hash = hash(key);
			
			Node newNode = new Node(key,value,null);
			if(header==null)//adding first
			{
				header = newNode;
				last =  newNode;
				newNode.after = null;
				newNode.before = null;
				
			}
			
			else{//adding in middle
				last.after = newNode;
				newNode.before = last;
				newNode.after = null;
				last = newNode;
			}
			
			if(hash_table[hash]==null)
			{
				hash_table[hash]= newNode;// if the bucket is empty then hashed
				newNode.next =  null;
			}
			else{// else traversing the chain the add it in the end s
				Node temp =  hash_table[hash];
				while(temp.next!=null)
				{
					temp =  temp.next;
				}
				temp.next= newNode;
				
			}
		}
		
		private int hash(int key){
			return key%size;
		}
		

		public int get(int key)
		{
			int hash =  hash(key);
			int value = -1;
			Node temp = hash_table[hash];
			while(temp!=null)
			{
				if(temp.key==key)
				{
					value = temp.value;
					break;
				}
				temp =  temp.next;
			}
			return value;
		}
		
		public int delete(int key)
		{
			int hash =  hash(key);
			int value = -1;
			Node temp = hash_table[hash];
			Node prev = null;
			while(temp!=null)
			{
				if(temp.key==key)
				{
					if(temp==header)
					{
						header = temp.after;
					}
					if(temp.before!=null){
						temp.before.after =  temp.after;
					}
					if(temp.after!=null){
						temp.after.before =  temp.before;
					}
					if(prev==null)
					{
						hash_table[hash]=temp.next;
					}
					else{
						prev.next = temp.next;
					}
					value =  temp.value; 
					break;
				}
				prev =  temp;
				temp = temp.next;
			}
			return value;
		}

		

		
		public static void main(String args[]){
			Scanner in = new Scanner(System.in);
			System.out.println("Enter the size of hash table ");
			int size = in.nextInt();
			
			
			LHM ob = new LHM(size);

			int choice;
			
			do {
			System.out.println("Enter your choice ");
			System.out.println(
			"1.put\n"+"2.display\n"+"3.delete\n"+"4.get\n"+"0.Exit"		
					);
			
			choice = in.nextInt();	
			
				
			switch(choice)
			{
				case 1:
				{
					System.out.println("Enter key");
					int key = in.nextInt();
					System.out.println("Enter value");
					int value = in.nextInt();
					ob.put(key, value);
					break;
					
					
				}
				case 2:
					ob.display();
					break;
					
				case 3:
				{
					System.out.println("Enter key to delete  ");
					int key = in.nextInt();
					int value = ob.delete(key);
					if(value==-1)
						System.out.println("Key not found");
					else
						System.out.println("The  value for the deleted key is = "+value);
					break;
				}
				
				case 4:
				{
					System.out.println("Enter key to retrive");
					int key = in.nextInt();
					int value = ob.get(key);
					if(value==-1)
						System.out.println("Key not found");
					else
						System.out.println("The value for the key is = "+value);
					break;
				}
				
				default:
					System.out.println("Please enter the  valid choice");
			}
			}while(choice != 0);
			

			
			}
			
		}

		
	


