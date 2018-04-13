import Classes.*;
import java.util.*;
public class runge_kutta
{
  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);
    s_expression function;
    String temp;
    System.out.print("\nEnter f : ");
    function = new s_expression(reader.nextLine());
    s_variable_table table = function.get_variables_table();
    List<String> variable_names = table.get_variable_names();
    System.out.print("\nEnter step size h : ");
    Float h = reader.nextFloat();
    System.out.print("\ny(x0)=y0\nEnter value of x0 : ");
    Float x0 = reader.nextFloat();
    System.out.print("\nEnter value of y0 : ");
    Float y0 = reader.nextFloat();
    System.out.print("\nEnter value of x : ");
    Float x = reader.nextFloat();
    int iteration = 1;

    System.out.println("\n******************************************************************************\n");

    while(x0 <= x)
    {
      System.out.println("\n------------------------------------------------------------------");
      System.out.println("                           Iteration : "+iteration);
      System.out.println("------------------------------------------------------------------");
      table.add_variable("x",Float.toString(x0));
      table.add_variable("y",Float.toString(y0));
      Float k1 = h * function.evaluate(table);
      System.out.println("\n\t\tK1 = "+k1);
      table.add_variable("x",Float.toString(x0+(h/2)));
      table.add_variable("y",Float.toString(y0+(k1/2)));
      Float k2 = h* function.evaluate(table);
      System.out.println("\n\t\tnK2 = "+k2);
      table.add_variable("x",Float.toString(x0+(h/2)));
      table.add_variable("y",Float.toString(y0+(k2/2)));
      Float k3 = h* function.evaluate(table);
      System.out.println("\n\t\tK3 = "+k3);
      table.add_variable("x",Float.toString(x0+h));
      table.add_variable("y",Float.toString(y0+k3));
      Float k4 = h*function.evaluate(table);
      System.out.println("\n\t\tK4 = "+k4);

      Float ans = y0 + (k1 + 2*k2 + 2*k3 + k4)/6;
      System.out.println("\n\t\ty("+x0+") = "+ans);
      x0 += h;
      y0 = ans;
      //System.out.println("\nNew y = "+y0+"    new x0 = "+x0);
      //System.out.println("\n------------------------------------------------------------------");
      iteration++;

      //reader.nextLine();
    }
    System.out.println("\n******************************************************************************\n");

  }
}
