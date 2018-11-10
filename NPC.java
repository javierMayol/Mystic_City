 /*
 *  Neel Patel 	NetID : npate315	UIN : 674004711
 * Keval Patel
 * Javier Mayol NetID : cmayol2		UIN : 671352495
 */
import java.util.*;

public class NPC extends Character
{
	private static String random_artifact;
	private static boolean request_artifact;
	//private static TreeMap<String, NPC>dummies = new TreeMap<String, NPC>();

	public NPC(Scanner scan) 
	{
		super(scan);
		decider = new AI();
		random_artifact = new String();
		request_artifact = false;
		//dummies.put(Integer.toString(this.ID), this);
	} 

	public NPC(Place place, int id, int age, String name_character, String desc)
	{
		super(place, id, age, name_character, desc);
		decider = new AI();
		random_artifact = new String();
		request_artifact = false;
		//dummies.put(Integer.toString(this.ID), this);
	}

<<<<<<< HEAD
	public void makeMove()
	{
		Move move = decider.getMove(this, current);
		while(move == null)
			move = decider.getMove(this, current);
		move.execute();
	}
=======
  public void makeMove()
  {
    Move move = decider.getMove(this, current);
    for(int i = 0; i < 3; i++)
    {
      while(move == null)
        move = decider.getMove(this, current);
      move.execute();
    }
  }
>>>>>>> bf1f5c68d2f87f09434f46680a81e17056b9009d

	public static Character sendDummy()
	{
		Random rand = new Random();
		List<String>keys = new ArrayList<String>(characters.keySet());
		String randK = keys.get(rand.nextInt(keys.size()));
		while(!(getCharacterByID(Integer.parseInt(randK)) instanceof NPC))
			randK = keys.get(rand.nextInt(keys.size()));
		//    System.out.println(dummies.get(randK).name()+" coming at you.");
		return getCharacterByID(Integer.parseInt(randK));
	}

	public void dummyMove()
	{
		for(int i = 0; i < 5; i++)
		{
			for(Character j : characters.values())
			{
				if(j instanceof NPC)
					j.makeMove();
			}
		}
	}

	public LinkedList<Artifact> emptyInventory()
	{
		LinkedList<Artifact> loot = new LinkedList<Artifact>(inventory.values());
		this.inventory.clear();
		this.points = 0;
		this.artifacts_weight = 0;
		return loot;    
	}

	public static String randomArtifact(String thing)
	{
		return thing;
	}
}
