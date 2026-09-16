import Jama.Matrix;

static Matrix getMatrixFromInput() {
    IO.println("Enter matrix data, press Ctrl+D when done.");
    IO.println("Add a newline after each row, including the last one");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        return Matrix.read(reader);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

void main() {
    Matrix m = getMatrixFromInput();
    m.print(3, 1);
    RREFMatrix.from(m).removeNegativeZeros().print(3, 1);
}