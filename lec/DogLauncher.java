public class DogLauncher {
	public static void main(String[] args) {
		Dog d1;
		d1 = new Dog(20);
		Dog d2 = new Dog(45);
		System.out.println("Below is the result of trying the static maxDog method:");
		Dog theMax = Dog.maxDog(d1, d2);
		theMax.makeNoise();
		System.out.println("\n");

		Dog small = new Dog(1);
		Dog[] dogHouse = new Dog[4];
		dogHouse[0] = small;
		dogHouse[1] = new Dog(20);
		Dog themaxV2 = dogHouse[0].maxDogV2(dogHouse[1]);
		System.out.println("Below is the result of trying the non-static maxDog method:");
		themaxV2.makeNoise(); 
		System.out.println("\n");

		System.out.println("Below I access the static variable via class Dog straightly")
		System.out.println(Dog.binomen);
	}
}