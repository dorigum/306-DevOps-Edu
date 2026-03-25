package ex0212_과제;

public class Tv extends Elec implements ElecFunction {
	private int chnnel;

	public Tv() { }

	public Tv(int channel) {
		this.chnnel = channel;
	}

	public Tv(String code, int cost, int chnnel) {
		super(code, cost);
		this.chnnel = chnnel;
	}

	// -----------------------------------------------
	@Override
	public void start() {
		System.out.println(getCode()+"의 " + 
				  getClass().getSimpleName() +" 를 " + chnnel +"본다.");
	}

	@Override
	public void stop() {
	}

	@Override
	public void display() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tv [");
		builder.append(super.toString());
		builder.append(", channel = ");
		builder.append(chnnel);
		builder.append("]");

		return builder.toString();
	}
}