import java.util.Scanner;

public class SigninAndLogin { 

	public static void main(String[] args) 
	{
		//Variables
		Scanner scan = new Scanner(System.in);
		
	    String name, idSer, Comm, password, fName, lName, inName, 
	    inPassword, registerName, registerPassword;
	    int com, age, id;
	    
	    System.out.println("					WELCOME TO NIGHTLIFE BAR					");
	    	
	    while(true);
	    {
	    	System.out.println("Press 1 to register/Press 2 to login /Press 3 to exit");
	    	//Input Number Command
	    	System.out.println("Command: ");
	    	int Command = scan.nextInt();
	    	System.out.println(com);
	     
		if (com == 3 );
		{
			return;
		}
		if (com == 1 );
		{
			System.out.println("				REGISTER			");
			//Input First Name
			System.out.println("First Name: ");
			String FIRSTNAME = scan.nextLine();
			System.out.println(fName);
			
			//Input Last Name
			System.out.println("Last Name: ");
			String LASTNAME = scan.nextLine();
			System.out.println(lName);
			
					//Full Name
					System.out.println(fName + " " + lName);
					
			//Input Age
			System.out.println("Age: ");
			int Age = scan.nextInt();
			System.out.println(age);
				
				if (age >= 1 && age <= 17) {
					System.out.println("Minor");
				return;
				}
				else if (age >= 18) {
					System.out.println("Legal");
				}
		   //Input ID
		   System.out.println("Press 1 (Office Id)/Press 2 (Driver's License)/Press 3 (Comelec Voter's ID)/Press 4 (Passport)");
		   System.out.println("Type of I.D.: ");
		   int Id = scan.nextInt();
		   System.out.println(id);
		   
		   		if (id == 1) {
				System.out.println("Office Id");
		   		}
		   		else if (id == 2) {
				System.out.println("Driver's License");
		   		}
		   		else if (id == 3) {
				System.out.println("Comelec Voter's ID");
			   	}
		   		else if (id == 4) {
				System.out.println("Passport");
			   	}
		   System.out.println("I.D Serial Number; ");
		   String IDSER = scan.nextLine();
		   System.out.println(idSer);
		   
		   System.out.println("New Username: ");
           String NewUserName = scan.nextLine();
           System.out.println(registerName);
           
           System.out.println("New Password: ");
           String NewPassword = scan.nextLine();
           System.out.println(registerPassword);
		   }
		
		if(com == 2 ) {
		System.out.println("				LOGIN				");
		
		while(true);
		
		System.out.println("Enter Username: ");
		String USERNAME = scan.nextLine();
		System.out.println(inName);
		
		System.out.println("Enter Password: ");
		String PASSWORD = scan.nextLine();
		System.out.println(inPassword);
		 
		if (inName==name && inPassword==password)
         {
         	System.out.println("					");
             System.out.println("Login Successful");

                  System.out.println("Welcome," + inName);
     
          		System.out.println("					");
          		System.out.println("________________________________Bar Info___________________________________");
    	
    	srand((unsigned) time(0));
    		
    		int tableNumber;
    		for (int index = 0; index < 1; index++) 
      		{
        			tableNumber = (rand() % 10) + 1;
       				System.out.println("Tables Available: ");
    				System.out.println(tableNumber);
      			}
      		int guestNumber;
      		for (int index = 0; index < 1; index++) 
      		{
        			guestNumber = (rand() % 200) + 1;
    				System.out.println("Guest: ");
    				System.out.println(guestNumber);
    			}
                        break;
                    }
                    System.out.println("incorrect username or password");
                }
            }
            System.out.println("				");
        return ;
    }
}