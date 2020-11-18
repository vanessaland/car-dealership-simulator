/**
 * Vanessa Landayan
 * Car Dealership Assignment
 */
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.DecimalFormat;

public class AccountingSystem
{	
    private static Map<Integer, Transaction> transactionMap;
    private static Map<Integer, Integer> monthSalesMap;
    private static Map<String, Integer> personSalesMap;
    private static Transaction newTransaction;
    private static double totalSales;
    private static int totalSold;
    private static int totalReturned;
    
    /**
     * Constructor initializes the maps for transactions with ID, amount of sales per month, amount of sales per salesperson;
     and initializes variables
     */
    public AccountingSystem()
    {    
        transactionMap = new TreeMap<Integer, Transaction>();
        monthSalesMap = new TreeMap<Integer, Integer>();
	personSalesMap = new TreeMap<String, Integer>();
        
        monthSalesMap.put(0, 0);
        monthSalesMap.put(1, 0);
        monthSalesMap.put(2, 0);
        monthSalesMap.put(3, 0);
        monthSalesMap.put(4, 0);
        monthSalesMap.put(5, 0);
        monthSalesMap.put(6, 0);
        monthSalesMap.put(7, 0);
        monthSalesMap.put(8, 0);
        monthSalesMap.put(9, 0);
        monthSalesMap.put(10, 0);
        monthSalesMap.put(11, 0);
       
        personSalesMap.put("Kingly", 0);
        personSalesMap.put("Danny", 0);
        personSalesMap.put("Ken", 0);
        personSalesMap.put("Vanessa", 0);
        personSalesMap.put("Jimmy", 0);
        personSalesMap.put("Anthony", 0);
        personSalesMap.put("Alex", 0);

        totalSales = 0;
        totalSold = 0;
        totalReturned = 0;
    }

    /**
     * Adds a new transaction to the map, updates variables, and updates maps
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @return a display of the transaction
     */
    public String add(GregorianCalendar date, Car car, String salesPerson,
    Transaction.TransactionType type, double salePrice)
    {    
	int id = 1 + (int)(Math.random() * (99-1)+1);
        newTransaction = new Transaction(id, date, car, salesPerson, type, salePrice);
        transactionMap.put(id, newTransaction);
        
        int month = date.get(Calendar.MONTH);
        int amount = monthSalesMap.get(month) + 1;
        monthSalesMap.put(month, amount);

        int spAmount = personSalesMap.get(salesPerson) + 1;
        personSalesMap.put(salesPerson, spAmount);

        if (type == Transaction.TransactionType.BUY)
	{
        	totalSold++;
        }
        
        if (type == Transaction.TransactionType.RETURN)
	{
        	totalReturned++;
        }

        totalSales += salePrice;
        
        return newTransaction.display();
    }

    /**
     * Gets a certain transaction
     * @param id
     * @return transaction with corresponding ID
     */
    public static Transaction getTransaction(int id)
    {
        if (transactionMap.get(id) != null)
	{
            return transactionMap.get(id);
        } else {
            return null;
        }
    }

    /**
     * Displays all the transactions by looping through and displaying the map of transactionIDs and transaction objects
     */
    public void getAllTransactions()
    {    
    	Set<Integer> keySet = transactionMap.keySet();	
        System.out.println("-------------------------------");
        
        for (Integer key : keySet)
	{
        	Transaction value = transactionMap.get(key);
            String formatdate = value.getDate().toZonedDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu"));
            System.out.println("ID: " + key +" "+ formatdate + " " + value.getType()
			+ " SalesPerson: " +  value.getSalesPerson() + " Car: " 
			+ getCar(key).display());
        	
        }
    }
    
    /**
     * Gets a certain car
     * @param id
     * @return the car
     */
    public Car getCar(int id)
    {
        if (transactionMap.get(id) != null)
	{
            Transaction transaction = transactionMap.get(id);
            Car car = transaction.getCar();
            return car;
            
        } else {
            return null;
        }
    }

    /**
     * Displays the salesperson(s) with the highest amount of sales
     */
    public void topSalesPerson()
    {
    	String topSalesPerson = "";
    	int topAmount = 0;
    	Set<String> keySet = personSalesMap.keySet();
        for (String key : keySet)
	{
        	int amount = personSalesMap.get(key);
        	if (amount > topAmount)
		{
        		topSalesPerson = key;
        		topAmount = amount;
        	}
        	if (amount == topAmount && amount != 0 && !key.equals(topSalesPerson))
		{
        		topSalesPerson += " and " + key;
        	}
        }
        
        System.out.println("Top SP: " + topSalesPerson + " Amount: " + topAmount);
    }
    
    /**
     * Displays the total sales, total sold, average sales, total returned, month with the most 
     * sales and the amount of cars sold in the best month 
     */
    public void stats()
    {    
    	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    	int topMonth = 0;
    	int topMonthValue = 0;
    	Set<Integer> keySet = monthSalesMap.keySet();
    	
        for (int key : keySet)
	{
		int value = monthSalesMap.get(key);
		if (value >= topMonthValue)
		{
			topMonthValue = value;
			topMonth = key;
		}
        }
    	
    	double avg = totalSales/12;
    	
        DecimalFormat df = new DecimalFormat("#.##");
        
        String str = "Total Sales: $" + df.format(totalSales) 
        			+ " Total Sold: " + totalSold
        			+ " Avg Sales: $" + df.format(avg)
        			+ " Total Returned: " + totalReturned
        			+ " Best Month -> " + months[topMonth]
        			+ ": cars sold -> " + topMonthValue;
        System.out.print(str);
    }
    
    /**
     * Displays the transactions within the indicated month
     * @param month
     */
    public void salesM(int month)
    {    
    	Set<Integer> keySet = transactionMap.keySet();	
        System.out.println("-------------------------------");

        for (Integer key : keySet)
	{
        	Transaction value = transactionMap.get(key);
        	if (value.getDate().get(Calendar.MONTH) == month)
		{
                String formatdate = value.getDate().toZonedDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu"));
                System.out.println("ID: " + key +" "+ formatdate + " " + value.getType()
    			+ " SalesPerson: " +  value.getSalesPerson() + " Car: " 
    			+ getCar(key).display());
        	}
        }
    }

}
