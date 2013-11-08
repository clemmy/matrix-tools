
public class Matrix {

	double[][] matrix;

	public Matrix(double[][] matrix)
	{
		//TODO: check if columns are equal in every row before creating?
		this.matrix=matrix;
	}

	public void matrixMultiply(Matrix b) {

		double[][] matrixProduct = new double[this.getRows()][b.getColumns()];

		for (int rowOfA = 0; rowOfA < this.getRows(); rowOfA++) 
		{
			for (int columnOfB = 0; columnOfB < b.getColumns(); columnOfB++) 
			{
				for (int i = 0; i < b.getRows(); i++)
				{
					matrixProduct[rowOfA][columnOfB] += this.matrix[rowOfA][i] * b.matrix[i][columnOfB];
				}
			}
		}
		this.matrix=matrixProduct;
	}
	
	public int getRows()
	{
		return this.matrix.length;
	}

	public int getColumns()
	{
		return this.matrix[0].length;
	}
}
