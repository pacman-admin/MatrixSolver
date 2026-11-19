/*
 * Copyright (c) 2026 Langdon Staab <langdon@langdonstaab.ca>
 *
 * Permission to use, copy, modify, and distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

import Jama.Matrix;

/**
 * A matrix in reduced row echelon form, built via {@link #from(Matrix)}.
 * Note: the RREF guarantee holds only until the matrix is mutated
 * (e.g. via set(...) or getArray()).
 */
public class RREFMatrix extends Matrix {
    private RREFMatrix(double[][] doubles) {
        super(doubles);
    }

    //Made by Claude.ai
    public static RREFMatrix from(Matrix A) {
        // Relative tolerance: scales with the data instead of a fixed 1e-10.
        int m = A.getRowDimension(), n = A.getColumnDimension();
        double tol = Math.max(m, n) * Math.ulp(A.normInf());
        return from(A, tol);
    }

    //Made by Claude.ai
    public static RREFMatrix from(Matrix A, double tol) {
        final int rows = A.getRowDimension();
        final int cols = A.getColumnDimension();
        final double[][] a = A.getArrayCopy();   // input is never mutated
        int r = 0;

        for (int c = 0; c < cols && r < rows; c++) {
            // Partial pivoting: largest magnitude in this column.
            int best = r;
            double max = Math.abs(a[r][c]);
            for (int i = r + 1; i < rows; i++) {
                double v = Math.abs(a[i][c]);
                if (v > max) {
                    max = v;
                    best = i;
                }
            }
            if (max <= tol) {
                for (int i = r; i < rows; i++) a[i][c] = 0.0;   // clean noise
                continue;
            }

            double[] pr = a[best];
            a[best] = a[r];
            a[r] = pr;

            // Scale the pivot row. Entries left of c are already zero, so start at c.
            double inv = 1.0 / pr[c];
            for (int j = c + 1; j < cols; j++) pr[j] *= inv;
            pr[c] = 1.0;

            // Eliminate the column from every other row.
            for (int k = 0; k < rows; k++) {
                if (k == r) continue;
                double[] row = a[k];
                double f = row[c];
                if (f == 0.0) continue;
                for (int j = c + 1; j < cols; j++) row[j] -= f * pr[j];
                row[c] = 0.0;
            }
            r++;
        }
        return new RREFMatrix(a);
    }

    public RREFMatrix clearTinyValues() {
        return clearTinyValues(Double.MIN_NORMAL);
    }

    public RREFMatrix clearTinyValues(final double tol) {
        double[][] a = getArrayCopy();
        for (double[] row : a)
            for (int j = 0; j < getColumnDimension(); j++)
                row[j] = Math.abs(row[j]) <= tol ? 0.0 : row[j] + 0.0;
        return new RREFMatrix(a);
    }
}