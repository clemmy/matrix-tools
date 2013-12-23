import java.math.BigDecimal;


public class Matrix {

        double[][] matrix;
        int rows = 0;
        int columns = 0;
        
        // creates an empty matrix of dimensions (row, cols)
        public Matrix(int rows, int cols) 
        {
        		double[][] matrix = new double[rows][cols];
        		this.matrix = matrix;
        		this.rows = rows;
        		this.columns = cols; 
        		
        }
        
        // creates a matrix using a two dimensional array
        public Matrix(double[][] matrix)
        {
                //TODO: check if columns are equal in every row before creating?
                this.matrix=matrix;
                this.rows = this.matrix.length;
                this.columns = this.matrix[0].length;
        }
        
        // multiplies two matrices and returns their product
        public Matrix multiply(Matrix b) {
        		
        		if (this.columns != b.rows) {
        			System.out.println("ERROR IN MULTIPLY!");
        			return null;
        		}
        		
        		Matrix product = new Matrix(this.rows,b.columns);

                for (int rowOfA = 0; rowOfA < this.rows; rowOfA++) 
                {
                        for (int columnOfB = 0; columnOfB < b.columns; columnOfB++) 
                        {
                                for (int i = 0; i < b.rows; i++)
                                {
                                        product.matrix[rowOfA][columnOfB] += this.matrix[rowOfA][i] * b.matrix[i][columnOfB];
                                }
                        }
                }
                
                return product;
        }
        
        // print a matrix with "precision" decimal places
        public void print(int precision) {
        	for (int i = 0; i < this.rows; i++) {
        		for (int j = 0; j < this.columns; j++) {
        			System.out.print(round(this.matrix[i][j],precision) + " ");
        		}
        		System.out.println("");
        	}
        }
        
        // print the matrix normally
        public void print() {
        	for (int i = 0; i < this.rows; i++) {
        		for (int j = 0; j < this.columns; j++) {
        			System.out.print(this.matrix[i][j] + " ");
        		}
        		System.out.println("");
        	}
        }
        
        // edit a single element of a matrix
        public void edit(int row, int col, double value) {
        	
        	this.matrix[row-1][col-1] = value;
        	
        }

        // add two matrices
        public Matrix add(Matrix b) {
        	
        	if (this.rows != b.rows || this.columns != b.columns) {
        		System.out.println("ERROR IN ADDITION!");
        		return null;
        	}
        	
        	Matrix sum = new Matrix(this.rows,this.columns);
        	
        	for (int i = 0; i < sum.rows; i++) {
        		for (int j = 0; j < sum.columns; j++)
        			sum.matrix[i][j] = this.matrix[i][j] + b.matrix[i][j];
        	}
        	
        	return sum;
        }

        // subtracts two matrices 
        public Matrix subtract(Matrix b) {
        	
        	if (this.rows != b.rows || this.columns != b.columns) {
        		System.out.println("ERROR IN SUBTRACTION!");
        		return null;
        	}
        	
        	Matrix difference = new Matrix(this.rows,this.columns);
        	
        	for (int i = 0; i < difference.rows; i++) {
        		for (int j = 0; j < difference.columns; j++)
        			difference.matrix[i][j] = this.matrix[i][j] - b.matrix[i][j];
        	}
        	
        	return difference;
        }

        // divides each element by a scalar
        public Matrix divide(double t) {
        	
        	Matrix newMatrix = new Matrix(this.rows,this.columns);
        	
        	for (int i = 0; i < newMatrix.rows; i++)
        		for (int j = 0; j < newMatrix.columns; j++)
        			newMatrix.matrix[i][j] = this.matrix[i][j] / t;
        	
        	return newMatrix;
        }
        
        // just a subroutine I copied from stackoverflow that rounds the doubles for me :)
        public static double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
            return bd.doubleValue();
        }

        // returns the (i, j)th entry in the matrix
        public double get(int i, int j) {
        	return this.matrix[i-1][j-1];
        }

        // returns the determinant of a matrix (fill this in)
        public double determinant() {
        	
        	if (this.rows != this.columns){
        		System.out.println("MATRIX NOT SQUARE! YOU CAN'T CALCULATE DETERMINANT.");
        		return (Double) null;
        	}
        	double determinant = 0;
        	
        	for (int i = 1; i <= this.columns; i++) {
        		if (this.columns != 2)
        			determinant += this.matrix[1-1][i-1] * Math.pow((-1),1+i)* this.kill(1, i).determinant(); 
        		else
        			return this.matrix[0][0]*this.matrix[1][1] - this.matrix[0][1]*this.matrix[1][0];
        	}
        	
        	return determinant;
        }

        // a subroutine used for finding a matrix with the first ith row and the jth column omitted
        public Matrix kill(int i, int j) {
        	
        	if (i < 1 || i > this.rows || j < 1 || j > this.columns){
        		System.out.println("OUT OF BOUNDS! YOU CAN'T KILL THAT ROW/COLUMN BECAUSE IT DOES NOT EXIST");
        		return null;
        	}
        	
        	Matrix newMatrix = new Matrix(this.rows-1, this.columns-1);
        	int x = 0; int y = 0;
        	
        	for (int row = 0; row < this.rows; row++) {
        		for (int column = 0; column < this.columns; column++) {
        			
        			if (row == i - 1 || column == j - 1);
        			else
        				newMatrix.matrix[x][y++] = this.matrix[row][column];
        			
        		}
        		// reset the column position
        		y = 0;
        		if (row == i - 1);
        		else
        			x++;
        	}
        	
        	return newMatrix;
        	
        }
        
        // a method to determine the cofactor matrix
        public Matrix cofactor() {
        	
        	Matrix cof = new Matrix(this.rows, this.columns);
        	
        	for (int i = 0; i < cof.rows; i++)
        		for (int j = 0; j < cof.columns; j++) {
        			cof.matrix[i][j] = Math.pow((-1),(j+1)+(i+1)) * this.kill(i+1,j+1).determinant();
        		}
        	
        	return cof;
        	
        }
        
        // transposes the matrix
        public Matrix transpose(){
        	
        	Matrix transpose = new Matrix(this.rows,this.columns);
        	
        	for (int i = 0; i < transpose.rows; i++)
        		for (int j = 0; j < transpose.rows; j++)
        			transpose.matrix[i][j] = this.matrix[j][i]; 
        	
        	return transpose;
        	
        }
        
        // returns the inverse of the provided matrix
        
        public Matrix sInvert() {
        	
        	if (this.rows != this.columns){
        		System.out.println("MATRIX NOT SQUARE! CALLING THE WRONG FUNCTION.");
        		return null;
        	}
        	
        	if (this.determinant() == 0){
        		System.out.println("DETERMINANT IS ZERO SO NO INVERSE EXISTS!");
        		return null;
        	}
        	
        	return this.cofactor().transpose().divide(this.determinant());
        	
        }
}
