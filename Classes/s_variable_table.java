package Classes;
import java.util.*;

public class s_variable_table
{
  public List<String> var_names ;
  public List<String> var_values ;
  public int count = 0;

  public s_variable_table()
  {
    var_names = new ArrayList<String>();
    var_values = new ArrayList<String>();
  }

  public List<String> get_variable_names()
  {
    return this.var_names;
  }

  public void add_variable(String variable,String value)
  {
    for(int i=0;i<count;i++)
    {
      if(var_names.get(i).equals(variable))
      {
        var_values.set(i,value);
        return;
      }
    }
    var_names.add(variable);
    var_values.add(value);
    count++;
  }

  public void delete_variable(String variable)
  {
    Iterator<String> iter1,iter2;
    iter1 = var_names.listIterator();
    iter2 = var_values.listIterator();

    while(iter1.hasNext())
    {
      if(iter1.next() == variable)
      {
        iter1.remove();
        iter2.remove();
        break;
      }
      iter2.next();
    }
  }

  public boolean exist(String variable)
  {
    if(var_names.contains(variable))
      return true;
    return false;
  }

  public String valueOf(String variable)
  {
    for(int i=0;i<count;i++)
    {
      if(var_names.get(i).equals(variable))
        return var_values.get(i);
    }
    return null;
  }

  public void print()
  {
    System.out.println("-----------------------------");
    for(int i=0;i<count;i++)
    {
      System.out.println(var_names.get(i)+" = "+var_values.get(i));
    }
    System.out.println("-----------------------------");
  }

  public boolean compare(s_variable_table t2)
  {
    if(this.count != t2.count)
      return false;
    for(int i=0;i<this.count;i++)
    {
      if(!this.var_values.get(i).equals(t2.var_values.get(i)))
        return false;
    }
    return true;
  }

}
