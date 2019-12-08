/**
 * Vanessa Landayan
 * Car Dealership Assignment
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class CarDealershipSimulator 
{
  public static void main(String[] args) throws FileNotFoundException
  {
	  CarDealership dealership = new CarDealership();  
	  ArrayList<Car> newCars = new ArrayList<Car>();
	  
	  String filename = "cars.txt";
	  File file = new File(filename);	  
	  newCars = readFiles(file);
	  
	  SalesTeam salesTeam = new SalesTeam();
	  AccountingSystem accountingSystem = new AccountingSystem();
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
  
	  /**
	   * Scans user input and follows the commands
	   * If input does not match an expected command, it will either show a message or just do nothing and continue
	   */
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) 
          {
			System.out.print("\n>");
            continue;
		  }

		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();

		  if (command == null || command.equals("")) 
		  {
             System.out.print("\n>");
	         continue;
          }
		  else if (command.equals("L") || command.equals("l"))
		  {
			  dealership.displayInventory();
		  }
		  else if (command.equals("Q") || command.equals("QUIT")|| command.equals("quit")|| command.equals("q"))
		  {	  
			  scanner.close();
			  commandLine.close();
			  System.exit(0);
		  }
		  else if (command.equals("ADD") || command.equals("add"))
		  {	  
			  dealership.addCars(newCars);
		  }
		  else if (command.equals("BUY") || command.equals("buy"))
		  {
					if (commandLine.hasNextInt())
					{
						int i = commandLine.nextInt();

						try
						{
							System.out.println(dealership.buyCar(i));

						}
						catch (NullPointerException e)
						{
							System.out.println("Invalid VIN. Please try again.");
							
						}
					}
					else
					{
						System.out.println("Which car would you like to buy? (include VIN)");
					}
			}

		  else if (command.equals("RET")||command.equals("ret"))
		  {
				if (commandLine.hasNextInt())
				{
					int i = commandLine.nextInt();

					try
					{
						System.out.println(dealership.returnCar(i));

					}
					catch (NullPointerException e)
					{
						System.out.println("Invalid Transaction ID. Please try again.");
						
					}
				}
				else
				{
					System.out.println("Which car would you like to return? (include transaction ID)");
				}
		  }
		  else if (command.equals("SPR")||command.equals("spr"))
		  {
			  dealership.sortByPrice();
		  }
		  else if (command.equals("SSR")||command.equals("ssr"))
		  {
			  dealership.sortBySafetyRating();
		  }
		  else if (command.equals("SMR")||command.equals("smr"))
		  {
			  dealership.sortByMaxRange();
		  }
		  else if (command.equals("FPR")||command.equals("fpr"))
		  {
			  double minPrice = 0; 
			  double maxPrice = 99999999;

			  if (!commandLine.hasNextDouble())
			  {				  
				  System.out.println("Include minimum price and maximum price.");
				  continue;
			  }
			  minPrice = commandLine.nextDouble();
			  if (!commandLine.hasNextDouble())
			  {				  
				  System.out.println("Include minimum price and maximum price.");
				  continue;
			  }	  
		      maxPrice = commandLine.nextDouble();
		      if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice)
		      {				  
				  System.out.println("Invalid minimum price and maximum price.");
				  continue;
			  }	
			  dealership.filterByPrice(minPrice,maxPrice);
		  }
		  else if (command.equals("FEL")||command.equals("fel"))
		  {
			  dealership.filterByElectric();
		  }
		  else if (command.equals("FAW")||command.equals("faw"))
		  {
			  dealership.filterByAWD();
		  }
		  else if (command.equals("FCL")||command.equals("fcl"))
		  {
			  dealership.filtersClear();
		  }
		  else if (command.equals("SALES")||command.equals("sales"))
		  {
			  if (!commandLine.hasNext())
			  {				  
				  accountingSystem.getAllTransactions();
				  continue;
			  }
			  else if (commandLine.hasNext())
			  {
				  String next = commandLine.next();
			  
				  if (next.equals("TEAM")||next.equals("team"))
				  {				  
					  salesTeam.displayTeam();
					  continue;
				  }	  
				  else if (next.equals("TOPSP")||next.equals("topsp"))
				  {				  
					  accountingSystem.topSalesPerson();
					  continue;
				  }
				  else if (next.equals("STATS")||next.equals("stats"))
				  {				  
					  accountingSystem.stats();
					  continue;
				  }
				  else
				  {
					    try
					    {
					        int month = Integer.parseInt(next);
					        if (month >= 0 && month < 12)
					        {
					        	accountingSystem.salesM(month);
					        }
					    } catch (NumberFormatException ex)
					    {
					    	System.out.println("Invalid input. Try again.");
					        continue;
					    }
				  }
			  }
			  else 
			  {
				  continue;
			  }
			}
		  System.out.print("\n>");
		}
  }
  
  /**
   * Reads from the written text file, assigns values to variables, and creates car objects accordingly
   * Puts the car objects inside an ArrayList
   * Displays message if there is no text file
   * @param cars
   * @return an ArrayList of car objects
   */
  public static ArrayList<Car> readFiles(File cars)
  {
	    try{
	    ArrayList<Car> newCars = new ArrayList<Car>();
	    Scanner scanner = new Scanner(cars);
		while (scanner.hasNextLine())
		{
		    String line = scanner.nextLine();
		    Scanner linescanner = new Scanner(line);

		    String manuf = null;				//1
		    String color = null;				//2
		    Car.Model model = null;				//3
		    Vehicle.PowerSource power = null;	//4
		    double safety = 0;					//5
		    int range = 0;						//6
		    boolean awd = true;					//7
		    double price = 0;					//8
		    int counter = 1;
			while (linescanner.hasNext())
			{
			switch (counter)
			{
			case 1:	manuf = linescanner.next(); break;
			case 2:	color = linescanner.next(); break;
			case 3:	String mod = linescanner.next();
					if (mod.equals("SEDAN"))
					{
						model = Car.Model.SEDAN;
					}else if(mod.equals("SPORTS"))
					{
						model = Car.Model.SPORTS;
					}else if(mod.equals("MINIVAN"))
					{
						model = Car.Model.MINIVAN;
					}
					break;
			case 4:	String pow = linescanner.next();
					if (pow.equals("GAS_ENGINE"))
					{
						power = Vehicle.PowerSource.GAS_ENGINE;
					}else if (pow.equals("GAS_ENGINE"))
					{
						power = Vehicle.PowerSource.ELECTRIC_MOTOR;
					}
					break;
			case 5:	safety = Double.parseDouble(linescanner.next()); break;
			case 6:	range = Integer.parseInt(linescanner.next()); break;
			case 7:	if (linescanner.next() == "AWD") {awd = true;} break;
			case 8:	price = Double.parseDouble(linescanner.next()); break;
			}
			counter++;
			}
			Car car = new Car(manuf, color, model, power, safety, range, awd, price);
			newCars.add(car);
			linescanner.close();			
		}
		scanner.close();
		return newCars;
	}
	    catch (FileNotFoundException exception){
	    System.out.println("No car file found.");
	    return null;
		}
  }
}
