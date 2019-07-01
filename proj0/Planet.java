public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
		          double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p2) {
		return Math.sqrt((this.xxPos - p2.xxPos) * (this.xxPos - p2.xxPos) + (this.yyPos - p2.yyPos) * (this.yyPos - p2.yyPos));
	}

	public double calcForceExertedBy(Planet p2) {
		double r = this.calcDistance(p2);
		return (G * this.mass * p2.mass) / (r * r);
	}

	public double calcForceExertedByX(Planet p2) {
		return this.calcForceExertedBy(p2) * (p2.xxPos - this.xxPos) / this.calcDistance(p2);
	}

	public double calcForceExertedByY(Planet p2) {
		return this.calcForceExertedBy(p2) * (p2.yyPos - this.yyPos) / this.calcDistance(p2);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netForce = 0;
		for (Planet p: allPlanets) {
			if (this.equals(p)) {
				continue;
			}			
			netForce = netForce + this.calcForceExertedByX(p);
		}
		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForce = 0;
		for (Planet p: allPlanets) {
			if (this.equals(p)) {
				continue;
			}
			netForce = netForce + this.calcForceExertedByY(p);
		}
		return netForce;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
