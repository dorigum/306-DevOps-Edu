package ex0212_과제;

public class Audio extends Elec implements ElecFunction {
	private int volume;
	
	public Audio() { }

	public Audio(int volume) {
		this.volume = volume;
	}
	
	public Audio(String code, int cost, int volume) {
		super(code, cost);
		this.volume = volume;
	}
	
	// ----------------------------------------------------
	@Override
	public void start() {
		System.out.println(getCode() + "의 " + 
				  getClass().getSimpleName() + "를 " + volume + "으로 듣는다.");
	}
	
	@Override
	public void stop() { }
	
	@Override
	public void display() { }
	
	// ----------------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Audio [");
		builder.append(super.toString());
		builder.append(", volume = ");
		builder.append(volume);
		builder.append("]");
		
		return builder.toString();
	} 
}