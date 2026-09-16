import Jama.Matrix;

static Matrix getMatrixFromInput() {
    IO.println("Enter matrix data, press Ctrl+D when done.");
    IO.println("Add a newline after each row, including the last one");
    try {
        //do stuff
    } catch (Throwable e) {
        IO.println("Cannot process input\n" + e);
    }
    return Matrix.identity(3, 4);
}

void main() {
    for (int i = 0; i < 4; i++) {
        Matrix m = getMatrixFromInput();
        m.print(3, 1);
        RREFMatrix.from(m).removeNegativeZeros().print(3, 1);
    }
}