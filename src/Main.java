import Jama.Matrix;

static Matrix getMatrixFromInput() {
    IO.println("Enter matrix data, press Ctrl+D when done.");
    IO.println("Add a newline after each row, including the last one");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        return Matrix.read(reader);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
//    int rows = Integer.parseInt(IO.readln("How many rows does the matrix have?"));
//    List<double[]> data = new ArrayList<>();
//    for (int i = 0; i < rows; i++) {
//        data.add(Arrays.stream(IO.readln("Enter matrix row, with numbers separated by spaces:").split(" ")).mapToDouble(Double::parseDouble).toArray());
//    }
//    return new Matrix(data.toArray(new double[rows][]));
}

public static Matrix rref(Matrix m) {
    int numRows = m.getRowDimension();
    int numCols = m.getColumnDimension();
    if (numRows > numCols)
        throw new IllegalArgumentException("The number of rows may now exceed the number of columns");
    int lead = 0;
    double[][] array = m.getArrayCopy();

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
    return new Matrix(array);
}

void main() {
    Matrix m = getMatrixFromInput();
    IO.println(Arrays.deepToString(m.getArray()));
}