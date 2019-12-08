/**
 * Vanessa Landayan
 * Car Dealership Assignment
 */
public class Car extends Vehicle implements Comparable<Car>
{
  public static enum Model
  {
	  SEDAN, SUV, SPORTS, MINIVAN;
  }
  
  Model   car_model; 
  int     maxRange; 
  double  safetyRating;
  boolean AWD;
  double  cost;
  boolean electric = false;

  /**
   * Constructor initializes the variables
   * @param manuf
   * @param color
   * @param model
   * @param power
   * @param safety
   * @param range
   * @param awd
   * @param price
   */
  public Car(String manuf, String color, Car.Model model, Vehicle.PowerSource power, 
		     double safety, int range, boolean awd, double price)
  {
	  super(manuf, color, 4, power);
	  car_model = model;
	  cost = price;
	  AWD = awd;
	  safetyRating = safety;
	  maxRange = range;
	  if (power == PowerSource.ELECTRIC_MOTOR)
	  {
		  electric = true;
	  }
  }
  
  /**
   * Display the information of the car
   */
  public String display()
  {
	 return super.display() + "\t" + car_model + "\t" +"$"+ cost + "\t" + safetyRating + "\t" + maxRange;

  }
  
  public String toString()
  {
	return "";
  }
  
  /**
   * Checks if cars are equal to each other
   */
  public boolean equals(Object other)
  {
	  Car otherCar = (Car) other;
	  return super.equals(other) && this.car_model == otherCar.car_model && this.AWD == otherCar.AWD; 
  }
  
  /**
   * Compares cars based on price
   */
  public int compareTo(Car other)
  {
    if      (this.cost > other.cost) return  1;
	else if (this.cost < other.cost) return -1;
	else                               return  0;
  }

  /**
   * The following methods get various variables (see names of the methods)
   * @return various variables of the car
   */
  public double getPrice()
  {
	  return cost;
  }
  
  public Car.Model getModel()
  {
	  return car_model;
  }
  
  public double getSR()
  {
	  return safetyRating;
  }
  
  public String getAWD()
  {
	  if (AWD==true)
	  {
		  return "AWD";
	  }
	  return "2WD";
  }
  
  public int getRange()
  {
	  return maxRange;
  }
  
  public boolean getElectric()
  {
	  return electric;
  }
}
