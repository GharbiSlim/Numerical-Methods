import Classes.*;
import java.util.*;
public class expression
{
  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);
    s_expression exp;
    System.out.print("\nEnter expression : ");
    String temp = reader.nextLine();
    exp = new s_expression(temp);

    s_variable_table table = exp.get_variables_table();
    List<String> variable_names = table.get_variable_names();

    while(true)
    {
      System.out.println("\nEnter variable values");
      for(int i=0;i<variable_names.size();i++)
      {
        System.out.print("\nEnter value of "+variable_names.get(i)+" : ");
        temp = reader.next();
        table.add_variable(variable_names.get(i),temp);
      }
      System.out.println("\nValue = "+Float.toString(exp.evaluate(table)));
    }

  }
}
