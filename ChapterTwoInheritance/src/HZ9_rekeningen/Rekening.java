package HZ9_rekeningen;

public abstract class Rekening {
	private int balans;
	public int getBalans() {return balans;}
	
	/**
	 * @post | getBalans() == 0
	 */
	public Rekening() {
		this.balans = 0;
	}
	
	/**
	 * @pre | bedrag >= 0
	 * @mutates | this
	 * @post | getBalans() == old(getBalans()) - result
	 * @post | 0 <= result && result <= bedrag 
	 */
	public int neemAf(int bedrag) {
		balans -= bedrag;
		return bedrag;
	}
	
	/**
	 * @pre | bedrag >= 0
	 * @mutates | this
	 * @post | getBalans() == old(getBalans()) + bedrag
	 */
	public void stort(int bedrag) {
		balans += bedrag;
	}
	
	/**
	 * @pre | rekening2 != null
	 * @inspects | this, rekening2
	 * @post | !result || getBalans() == rekening2.getBalans()
	 */
	public abstract boolean equals(Rekening rekening2);
}

/**
 * @invariant | getBalans() >= -getKredietlimiet()
 */
class Zichtrekening extends Rekening{
	private int balans;
	/**
	 * @invar | getBalans() >= -kredietlimiet
	 * @invar | kredietlimiet >= 0
	 */
	private int kredietlimiet;
	
	/**
	 * @post | getBalans() == balans
	 * @post | getKredietlimiet() == kredietlimiet 
	 */
	public Zichtrekening(int balans, int kredietlimiet) {
		this.kredietlimiet = kredietlimiet;
	}
	
	public int getKredietlimiet() {return kredietlimiet;}
	
	/**
	 * @pre | bedrag >= 0
	 * @mutates | this
	 * @post | (bedrag >= getBalans() && bedrag >= getKredietlimiet()) ? getBalans() == old(getBalans()) - bedrag : (bedrag >= getBalans() ? getBalans() == 0 : getBalans() == old(getBalans()) - getKredietlimiet())
	 * @post | (bedrag >= getBalans() && bedrag >= getKredietlimiet()) ? result == bedrag: (bedrag >= getBalans() ? result == old(getBalans()) : result == getKredietlimiet())
	 */
	public int neemAf(int bedrag) {
		if (bedrag > balans) {
			return super.neemAf(balans);
		}
		else if (bedrag > kredietlimiet) {
			return super.neemAf(kredietlimiet);
		}
		else {
			return super.neemAf(bedrag);
		}
	}
	
	/**
	 * @pre | rekening2 != null
	 * @inspects | rekening2
	 * @post | rekening2.getClass() == getClass() ? result == (getBalans() == rekening2.getBalans() && getKredietlimiet() == ((Zichtrekening)rekening2).getKredietlimiet()) : false
	 */
	public boolean equals(Rekening rekening2) {
		if (rekening2 instanceof Zichtrekening zrek)
			return balans == zrek.getBalans() && kredietlimiet == zrek.getKredietlimiet();
		return false;
	}
	
	/**
	 * @post | result != null
	 */
	public String toString() {
		return ("Uw balans bedraagt: " + balans + "\nUw kredietlimiet bedraagt: " + kredietlimiet );
	}
}

class Spaarrekening extends Rekening {
	int balans;
	
	/**
	 * @post | getBalans() == balans
	 */
	public Spaarrekening() {}
	
	/**
	 * @pre | bedrag >= 0
	 * @post | result == 0
	 * @post | getBalans() == old(getBalans()) 
	 */
	public int neemAf(int bedrag) {
		return 0;
	}
	
	/**
	 * @pre | rekening2 != null
	 * @inspects | rekening2
	 * @post | rekening2.getClass() == getClass() ? result == (getBalans() == rekening2.getBalans()) : result == false
	 */
	public boolean equals(Rekening rekening2) {
		if (rekening2 instanceof Spaarrekening srek)
			return balans == srek.getBalans();
		return false;
	}
	
	/**
	 * @post | result != null
	 */
	public String toString() {
		return ("Uw balans bedraagt: " + balans);
	}
}