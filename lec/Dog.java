public class Dog {
	public int weightInPounds;
	public static String binomen = "Canis familiaris";
	public Dog(int w) {
		/** constuctor, similar to the __init__ method */
		weightInPounds = w;
	}
	public void makeNoise() {
		if (weightInPounds < 10) {
			System.out.println("Yip!");
		} else if (weightInPounds < 30) {
			System.out.println("Bark!");
		} else {
			System.out.println("Wooof!");
		}
	}
	public static Dog maxDog(Dog d1, Dog d2) {
		if (d1.weightInPounds > d2.weightInPounds) {
			return d1;
		}
		return d2;
	}
	public Dog maxDogV2(Dog d) {
		if (this.weightInPounds > d.weightInPounds) {
			return this;
		}
		return d;
	}
}