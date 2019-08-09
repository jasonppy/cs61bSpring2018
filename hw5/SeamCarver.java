import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
//        if (x >= width() || y >= height() || x < 0 || y < 0)
//            throw new java.lang.IndexOutOfBoundsException();

        double xGradient, yGradient;

        if (x == 0) {
            xGradient = getGradient(width() - 1, y, x + 1, y);
        } else if (x == width() - 1) {
            xGradient = getGradient(x - 1, y, 0, y);
        } else {
            xGradient = getGradient(x - 1, y, x + 1, y);
        }
        if (y == 0) {
            yGradient = getGradient(x, height() - 1, x, y + 1);
        } else if (y == height() - 1) {
            yGradient = getGradient(x, y - 1, x, 0);
        } else {
            yGradient = getGradient(x, y - 1, x, y + 1);
        }

        return xGradient + yGradient;
    }
    private double getGradient(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);
        return (color1.getRed() - color2.getRed()) * (color1.getRed() - color2.getRed())
                + (color1.getGreen() - color2.getGreen()) * (color1.getGreen() - color2.getGreen())
                + (color1.getBlue() - color2.getBlue()) * (color1.getBlue() - color2.getBlue());
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture pictureT = new Picture(height(), width());
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                pictureT.set(j, i, picture.get(i, j));
            }
        }

        SeamCarver sc = new SeamCarver(pictureT);
        int[] hseam = sc.findVerticalSeam();
        return hseam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] M = new double[width()][height()];
        double[][] e = new double[width()][height()];
        for (int j = 0; j < height(); j++) {
            for (int i = 0; i < width(); i++) {
                e[i][j] = energy(i, j);
                if (j == 0) { M[i][j] = e[i][j]; }
            }
        }
        for (int j = 1; j < height(); j++) {
            for (int i = 0; i < width(); i++) {
                if (i == 0) {
                    M[i][j] = e[i][j] + Math.min(M[i][j - 1],M[i + 1][j - 1]);
                }
                else if (i == width() - 1) {
                    M[i][j] = e[i][j] + Math.min(M[i - 1][j - 1], M[i][j - 1]);
                }
                else {
                    M[i][j] = e[i][j] + Math.min(Math.min(M[i - 1][j - 1], M[i][j - 1]),M[i + 1][j - 1]);
                }
            }
        }

        int[] seam = new int[height()];
        seam[height() - 1] = findMin(0, width() - 1, height() - 1, M);

        for (int j = height() - 2; j >=0; j--) {
            int x = seam[j + 1];
            if (x == 0) {
                seam[j] = findMin(x, x + 1, j, M);
            } else if (x == width() - 1) {
                seam[j] = findMin(x - 1, x, j, M);
            } else {
                seam[j] = findMin(x - 1, x + 1, j, M);
            }
        }
        return seam;
    }

    private int findMin(int xMin, int xMax, int y, double[][] M) {
        double min = M[xMin][y];
        int index = xMin;
        for (int i = xMin + 1; i <= xMax ; i++) {
            if (M[i][y] < min) {
                min = M[i][y];
                index = i;
            }
        }
        return index;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(picture, findHorizontalSeam());
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(picture, findVerticalSeam());
    }
}