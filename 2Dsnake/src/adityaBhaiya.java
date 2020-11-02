import java.util.Scanner;
public class adityaBhaiya {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		
		
			int x=sc.nextInt();
			String a=Integer.toBinaryString(x);
			
			StringBuilder input1 = new StringBuilder();
			input1=input1.reverse();
			
			int decimal=Integer.parseInt(input1,2);  
			System.out.println(decimal); 
			
			
			
		
		
		

	}

}
