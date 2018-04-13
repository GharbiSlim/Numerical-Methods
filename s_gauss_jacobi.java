import Classes.*;
import java.util.*;

public class s_gauss_jacobi
{
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		int n;
		System.out.print("Enter Number of variables in system : ");
		n = reader.nextInt();

		s_matrix coef_matrix = new s_matrix(n,n);
		s_matrix right_matrix = new s_matrix(n,1);

		System.out.println("Give Coefficient Matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print("\nCoefficient A["+(i+1)+"]["+(j+1)+"] : ");
				Float f = reader.nextFloat();
				coef_matrix.set(i,j,f);
			}
		}
		coef_matrix.print();
		System.out.println("Give Right Side Matrix");
		for(int i=0;i<n;i++)
		{
			System.out.print("\nValue B["+(i+1)+"] : ");
			Float f = reader.nextFloat();
			right_matrix.set(i,0,f);
		}
		right_matrix.print();


		ArrayList <s_expression> var_exprs = new ArrayList <s_expression> (n);
		s_variable_table var_list = new s_variable_table();	
		// setting expression for each variable
		for(int i=0;i<n;i++)
		{
			String exp = "(" + Float.toString(right_matrix.get(i,0)) ;
			for(int j=(i+1)%n;j!=i;j=(j+1)%n)
			{
				exp += " + (" + Float.toString(coef_matrix.get(i,j)) + ") * (-1) * x"+j;
			}
			exp += ") / "+ Float.toString(coef_matrix.get(i,i));
			s_expression expression = new s_expression(exp);
			var_exprs.add(expression);
			var_list.add_variable("x"+i,"0.0");
		}

		// do iterations
		int i = 0;
		while(true)
		{
			System.out.println("\n--------------------------------------------------------------------");
			System.out.print("                               Iteration "+(i+1));
			System.out.println("\n--------------------------------------------------------------------");
			s_variable_table new_table = new s_variable_table();

			for(int j=0;j<n;j++)
			{
				// construct table for this expression
				s_variable_table local_table = new s_variable_table();
				local_table = var_list;
				local_table.delete_variable("x"+j);
				new_table.add_variable("x"+j,Float.toString(var_exprs.get(j).evaluate(local_table)));
			}
			if(var_list.compare(new_table))
			{
				System.out.println("\n--------------------------------------------------------------------");
				System.out.print("                               Answer "+(i+1));
				System.out.println("\n--------------------------------------------------------------------");
				var_list.print();
				break;
			}
			var_list = new_table;
			var_list.print();
			i++;
			if(i > 50)
			{
				System.out.println("\n############################################################################");
				System.out.println("\nStopping Forcibly [ Iterations exceeds count of "+i+" ]");
				System.out.println("\n############################################################################");
				break;
			}
		}
	}
}