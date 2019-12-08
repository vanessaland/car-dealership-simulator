/**
 * Vanessa Landayan
 * Car Dealership Assignment
 */
import java.util.ListIterator;
import java.util.LinkedList;

public class SalesTeam
{
    static LinkedList<String> team;

    /**
     * Constructor initializes the LinkedList and adds the names of team members
     */
    public SalesTeam()
    {
        team = new LinkedList<String>();
        team.add("Kingly");
        team.add("Danny");
        team.add("Ken");
        team.add("Vanessa");
        team.add("Jimmy");
        team.add("Anthony");
        team.add("Andy");

    }

    /**
     * Counts the number of team members
     * @return amount of members
     */
    public int countTeam()
    {
    	ListIterator<String> iter = team.listIterator();
        int counter = 0;
        while(iter.hasNext())
        {
            counter++;
            iter.next();
        }
        return counter;
    }

    /**
     * Gets a random team member
     * @return random team member
     */
    public String getRandom()
    {
    	ListIterator<String> iter = team.listIterator();
        int index =  (int)(Math.random() * team.size());
        iter = team.listIterator(index);
        String name = iter.next();
        return name;
    }

    /**
     * Displays the names of all the members
     */
    public void displayTeam()
    {
    	ListIterator<String> iter = team.listIterator();
    	System.out.println("----------[SALES TEAM]----------\n");
        while(iter.hasNext())
        {
            String name = iter.next();
            System.out.println(name);
        }
    }

    /**
     * Gets a certain team member based on their index
     * @param index
     * @return name of the member
     */
    public String getASalesPerson(int index)
    {
    	ListIterator<String> iter = team.listIterator();
    	String name = null;
        for (int i = 0; i <= index; i++)
        {
            name = iter.next();
        }
        return name;
    }
}