import Jama.Matrix;

static Matrix getMatrixFromInput() {
//    IO.println("Enter matrix data, press Ctrl+D when done.");
//    IO.println("Add a newline after each row, including the last one");
    try {
        int rows = Integer.parseInt(IO.readln("How many rows does the matrix have?"));
        List<double[]> data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            data.add(Arrays.stream(IO.readln("Enter matrix row, with numbers separated by spaces:").split(" ")).mapToDouble(Double::parseDouble).toArray());
        }
        return new Matrix(data.toArray(new double[rows][]));
    } catch (Throwable e) {
        IO.println("Cannot process input: " + e.getMessage());
    }
    return Matrix.identity(3, 4);
}

void main() {
    for (; ; ) {
//        Matrix m = getMatrixFromInput();
//        m.print(3, 0);
        RREFMatrix.from(getMatrixFromInput()).removeNegativeZeros().print(3, 2);
    }
}