package BlackjackPackage;

public class Chips {

	private long credit = 0;

	public Chips(){
		credit = 500;
	}

	public int getCredit() {
		return (int) credit;
	}

	public void setCredit(int credit) {
		this.credit = this.credit - credit;
	}
	
	public void setCreditWin(int credit) {
		this.credit = this.credit + credit;
	}

}
