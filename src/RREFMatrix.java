import Jama.Matrix;

public class RREFMatrix extends Matrix {
    private RREFMatrix(double[][] doubles) {
        super(doubles);
    }

    public static RREFMatrix from(Matrix A) {
        double[][] array = A.getArray();
        int numRows = A.getRowDimension();
        int numCols = A.getColumnDimension();
        int lead = 0;

        for (int r = 0; r < numRows; r++) {
            if (numCols <= lead) break;
            int i = r;

            while (Math.abs(array[i][lead]) < 1e-10) {
                i++;
                if (numRows == i) {
                    i = r;
                    lead++;
                    if (numCols == lead) break;
                }
            }
            if (numCols <= lead) break;

            // Swap rows
            double[] temp = array[r];
            array[r] = array[i];
            array[i] = temp;

            // Scale pivot row
            double pivot = array[r][lead];
            if (Math.abs(pivot) > 1e-10) {
                for (int j = 0; j < numCols; j++) {
                    array[r][j] /= pivot;
                }
            }

            // Eliminate other rows
            for (int k = 0; k < numRows; k++) {
                if (k != r) {
                    double factor = array[k][lead];
                    for (int j = 0; j < numCols; j++) {
                        array[k][j] -= factor * array[r][j];
                    }
                }
            }
            lead++;
        }
        return new RREFMatrix(array);
    }

    public static Matrix removeNegativeZeros(Matrix A) {
        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                if (Double.compare(A.getArray()[i][j], -0.0) == 0) {
                    A.getArray()[i][j] = 0.0;
                }
            }
        }
        return A;
    }

    public Matrix removeNegativeZeros() {
        return removeNegativeZeros(this);
    }
}