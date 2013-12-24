import java.math.BigDecimal;


public class Matrix {

        double[][] matrix;
        int rows = 0;
        int columns = 0;
        int nonZeroRows = this.rows; // Assumption that the last column is non-zero
        
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
        			System.out.print(Double.toString(round(this.matrix[i][j],precision) + 0.0) + " ");
        		}
        		System.out.println("");
        	}
        }
        
        // print the matrix normally
        public void print() {
        	for (int i = 0; i < this.rows; i++) {
        		for (int j = 0; j < this.columns; j++) {
        			System.out.print(Double.toString(this.matrix[i][j] + 0.0) + " ");
        		}
        		System.out.println("");
        	}
        }
        
        // checks to see if a matrix is Empty
        public boolean isEmpty() {
        	int counter = 0;
        	
        	for (int i = 1; i <= this.columns; i++)
        		if (this.isColumnEmpty(i)){
        			counter++;
        		}
        	if (counter == this.columns)
        		return true;
        	else
        		return false;
        }
        
        // edit a single element of a matrix
        public void edit(int row, int col, double value) {
        	
        	this.matrix[row-1][col-1] = value;
        	
        }

        // copies the elements of matrix b into the current matrix
        public void copy(Matrix b){
        	
        	if (b.rows != this.rows || b.columns != this.columns){
        		System.out.println("THE MATRIX YOU'RE TRYING TO COPY IS NOT THE SAME SIZE AS THE CURRENT MATRIX");
        		return;
        	}	
        	
        	for (int i = 0; i < this.rows; i++)
        		for (int j = 0; j < this.columns; j++)
        			this.matrix[i][j] = b.matrix[i][j];

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
        
        // returns the inverse of the provided square matrix
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

        // returns the trace of the provided matrix
        public double trace(){
        	
        	if (this.columns != this.rows){
        		System.out.println("NOT A SQUARE MATRIX. TRACE CALCULATION FAILED!");
        		return (Double)null;
        	}
        	
        	double trace = 0;
        	
        	for (int i = 0; i < this.columns; i++)
        		trace += this.matrix[i][i];
        	
        	return trace;
        }
        
        // return the b power of a matrix
        public Matrix power(int num) {
        	
        	Matrix powerM = new Matrix(this.rows,this.columns);
        	
        	for (int i = 0; i < this.rows; i++)
        		powerM.matrix[i][i] = 1;
        	
        	for (int i = 0; i < num; i++)
        		powerM = powerM.multiply(this);
        	
        	return powerM;
        }
        
        // swapping two rows
        public Matrix rowSwap(int row1, int row2) {
        	
        	Matrix swapMatrix = new Matrix(this.rows,this.columns);
        	swapMatrix.copy(this);
        	
        	double temp = 0; // temporary variable to ease swapping of values 
        	
        	for (int j = 0; j < swapMatrix.columns; j++) {
        		temp = swapMatrix.matrix[row1 - 1][j];
        		swapMatrix.matrix[row1 - 1][j] = swapMatrix.matrix[row2 - 1][j];
        		swapMatrix.matrix[row2 - 1][j] = temp;
        	}
        		
        	return swapMatrix;
        	
        }
        
        // swapping two columns
        public Matrix columnSwap(int col1, int col2) {
        	
        	Matrix swapMatrix = new Matrix(this.rows,this.columns);
        	swapMatrix.copy(this);
        	
        	double temp = 0; // temporary variable to ease swapping of values 
        	
        	for (int i = 0; i < swapMatrix.rows; i++) {
        		temp = swapMatrix.matrix[i][col1 - 1];
        		swapMatrix.matrix[i][col1 - 1] = swapMatrix.matrix[i][col2 - 1];
        		swapMatrix.matrix[i][col2 - 1] = temp;
        	}
        		
        	return swapMatrix;
        	
        }

        // adds a multiple of one row to another; called like this addRow(row you want to add to, row that's being added, scaling factor of second row)
        public Matrix rowAdd(int row1, int row2, double scalingFactor) {
        	Matrix addMatrix = new Matrix(this.rows,this.columns);
        	addMatrix.copy(this);
        	
        	for (int j = 0; j < addMatrix.columns; j++)
        		addMatrix.matrix[row1-1][j] += scalingFactor*addMatrix.matrix[row2-1][j];
        	
        	return addMatrix;
        }
 
        // adds a multiple of one column to another; called like this columnAdd(column you want to add to, column that's being added, scaling factor of second column)
        public Matrix columnAdd(int col1, int col2, double scalingFactor) {
        	Matrix addMatrix = new Matrix(this.rows,this.columns);
        	addMatrix.copy(this);
        	
        	for (int i = 0; i < addMatrix.rows; i++)
        		addMatrix.matrix[i][col1-1] += scalingFactor*addMatrix.matrix[i][col2-1];
        	
        	return addMatrix;
        }

        // scales row1 by factor scalingFactor
        public Matrix rowScale(int row1, double scalingFactor) {
        	Matrix scaleMatrix = new Matrix(this.rows,this.columns);
        	scaleMatrix.copy(this);
        	
        	for (int j = 0; j < scaleMatrix.columns; j++)
        		scaleMatrix.matrix[row1-1][j] *= scalingFactor;
        	
        	return scaleMatrix;
        }

        // scales col1 by factor scalingFactor
        public Matrix columnScale(int col1, double scalingFactor) {
        	Matrix addMatrix = new Matrix(this.rows,this.columns);
        	addMatrix.copy(this);
        	
        	for (int i = 0; i < addMatrix.rows; i++)
        		addMatrix.matrix[i][col1-1] *= scalingFactor;
        	
        	return addMatrix;
        }

        // checks if row1 is zero (recursive function); not sure if good idea or not to use recursion
        public boolean isRowEmpty(int row1) {
        	return this.isRowEmpty(row1-1, 0);
        }
        
        public boolean isRowEmpty(int row1, int index) {
        	if (index == this.columns - 1 && this.matrix[row1][index] == 0)
        		return true;
        	else if (index == this.columns - 1 && this.matrix[row1][index] != 0)
        		return false;
        	else if (this.matrix[row1][index] == 0)
        		return isRowEmpty(row1, index + 1);
        	else 
        		return false;
        	
        }
        
        // check if col1 is zero (recursive function); not sure if good idea or not to use recursion
        public boolean isColumnEmpty(int col1) {
        	return this.isColumnEmpty(col1-1, 0);
        }
        
        public boolean isColumnEmpty(int col1, int index) {
        	if (index == this.rows - 1 && this.matrix[index][col1] == 0)
        		return true;
        	else if (index == this.rows - 1 && this.matrix[index][col1] != 0)
        		return false;
        	else if (this.matrix[index][col1] == 0)
        		return isColumnEmpty(col1, index + 1);
        	else 
        		return false;
        	
        }

        // organizes the matrix in descending order in terms of the number of entries in each row
        public Matrix reOrganize(){
        	Matrix organizedMatrix = new Matrix(this.rows, this.columns);
        	organizedMatrix.copy(this);
        	
        	// Gets the location of each element in each row
        	int[] index = new int[organizedMatrix.rows];
        	
        	for (int i = 0; i < organizedMatrix.rows; i++)
        		for (int j = 0; j < organizedMatrix.columns; j++)
        			if (this.matrix[i][j] == 0)
        				index[i]++;
        			else
        				break;

        	int temp = 0;
        	
        	for (int i = 0; i < organizedMatrix.rows; i++) {
        		for (int j = i + 1; j < organizedMatrix.rows; j++) {
        			if (index[i] > index[j]) // row i+1 has less elements than row j+1
        			{
        				organizedMatrix = organizedMatrix.rowSwap(i+1,j+1);
        				temp = index[i];
        				index[i] = index[j];
        				index[j] = temp;
        				
        			}
        
        		}
        	}
        	
        	return organizedMatrix;
        	
        }
        
        // returns an integer containing the location data of the first element in row1
        public int rowStartingPosition(int row1){
        	
        	int index = 0;
        	
        	for (int i = 0; i < this.columns; i++)
        		if (this.matrix[row1 - 1][i] == 0)
        			index++;
        		else 
        			break;
        	
        	return index + 1;
        	
        }
        
        // returns the first column in the matrix that contains data
        public int firstNonEmptyColumn(){
        	Matrix newMatrix = new Matrix(this.rows, this.columns);
        	newMatrix.copy(this.reOrganize());
        	
        	for (int i = 1; i <= newMatrix.columns; i++) {
        		if (!(newMatrix.isColumnEmpty(i) == true))
        			return i;
        	}
        	
        	System.out.println("YOUR MATRIX IS EMPTY! RETURNING 0");
        	return 0;
        }
        
        // returns the ref of the matrix object
        public Matrix ref() {
        	Matrix ref = new Matrix(this.rows, this.columns);
        	ref.copy(this.reOrganize());
        	
        	if (ref.isEmpty())
        		return ref;
        	
//        	System.out.println(this.firstNonEmptyColumn());
        	
        	// BULK CODE:
        	for (int j = this.firstNonEmptyColumn(), i = 1;j <= this.columns; j++, i++) {
        		for (int l = i + 1; l <= this.rows; l++) {
//        			ref.print(1);
        			
        			if (ref.matrix[i-1][j-1] != 0)
        				ref = ref.rowAdd(l,i,-(ref.matrix[l-1][j-1]/ref.matrix[i-1][j-1]));
//        			System.out.println();

        		}
        		ref = ref.reOrganize();
        	}
//        	System.out.println();
        	return ref;
        }

        // returns the rref of the matrix object
        public Matrix rref() {
        	
        	Matrix rref = new Matrix(this.rows, this.columns);
        	rref.copy(this.ref());
        	
        	if (rref.isEmpty())
        		return rref;
        	
        	// BULK CODE:
        	for (int j = rref.firstNonEmptyColumn(), i = 1;j <= this.columns; j++, i++) {
        		for (int l = i - 1; l >= 1; l--) {
//        			rref.print(1);
        			
        			if (rref.matrix[i-1][j-1] != 0)
        				rref = rref.rowAdd(l,i,-(rref.matrix[l-1][j-1]/rref.matrix[i-1][j-1]));
//        			System.out.println();

        		}
        	}
        	
        	for (int i = 1, j = rref.firstNonEmptyColumn(); i <= rref.rows; i++, j++)
        		if (rref.matrix[i-1][j-1] != 0)
        			rref = rref.rowScale(i,1/(rref.matrix[i-1][j-1]));
        	
        	return rref;
        }
}
