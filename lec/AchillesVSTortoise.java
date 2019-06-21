public class AchillesVSTortoise {
	public static void main(String[] args) {
		String a = "Achilles";
		String t = "Tortoise";
		double aPos = 0;
		double tPos = 100;
		double aSpeed = 20;
		double tSpeed = 10;
		double totalTime = 0;
		while (aPos < tPos) {
			System.out.println("At time " + totalTime);
			System.out.println("    " + a + " is at " + aPos);
			System.out.println("    " + t + " is at " + tPos);

			double timeToReach = (tPos - aPos) / aSpeed;
			totalTime = totalTime + timeToReach;
			aPos = aPos + aSpeed * timeToReach;
			tPos = tPos + tSpeed * timeToReach;
		}
	}
}