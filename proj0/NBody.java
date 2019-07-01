/** Project 0: NBody Simulation
 *
 *  src = "https://sp18.datastructur.es/materials/proj/proj0/proj0"
 *
 *  @author Puyuan Peng 06/21/2019
 */
public class NBody {
	/** Query a double corresponding to the radius of the universe in that file.
        Args:
            dir(String): Directory path to txt file.
        Returns:
            R(double): Radius of the universe in that file
    */
	public static double readRadius(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	/** Query an array of Planets corresponding to the planets in the file.
        Args:
            dir(String): Directory path to txt file.
        Returns:
            planet[](Array): array which contains all of the planets.
    */
	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] manyPlanets = new Planet[N];
		for (int n = 0; n < N; n++) {
    		double xxPos = in.readDouble();
    		double yyPos = in.readDouble();
    		double xxVel = in.readDouble();
    		double yyVel = in.readDouble();
    		double mass = in.readDouble();
    		String imgFileName = in.readString();
            manyPlanets[n] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

		}
		return manyPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];
        In in = new In(filename);
        int N = in.readInt();
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);   
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet p: planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T ; t += dT) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dT, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet p: planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(5);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
	
}