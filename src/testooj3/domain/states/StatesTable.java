package testooj3.domain.states;

public class StatesTable 
{
  protected String[][] mData;
  protected int mRows;
  protected int mColumns;
  
  public StatesTable(int rows, int columns)
  {
    mData=new String[rows][columns];
    mRows=rows;
    mColumns=columns;
  }
  
  public String toHTMLTable() 
  {
	  StringBuilder result=new StringBuilder("<table border=1>");
    for (int row=0; row<mRows; row++) 
    {
      result.append("<tr>");
      for (int col=0; col<mColumns; col++) 
      {
    	  result.append("<td>");
    	  result.append((mData[row][col]!=null ? mData[row][col] : ""));
    	  result.append("</td>");
      }
      result.append("</tr>");
    }
    result.append("</table>");
    return result.toString();
  }
  
  public void set(int row, int column, String value) 
  {
    mData[row][column]=value;
  }
  
  public void set(String rowLabel, String columnLabel, String value) 
  {
    int row=0, col=0;
    for (int i=0; i<mRows; i++) {
      if (mData[i][0]!=null && mData[i][0].equals(rowLabel))
        row=i;
    }
    for (int i=0; i<mColumns; i++) 
    {
      if (mData[0][i]!=null && mData[0][i].equals(columnLabel))
        col=i;
    }
    mData[row][col]=value;
  }

  public int getRow(String name) {
		for (int i=0; i<mRows; i++)
			if (mData[i][0]!=null && mData[i][0].equals(name))
				return i;
		return 0;
	}
  
  public int getColumn(String name) {
		for (int i=0; i<mColumns; i++)
			if (mData[0][i]!=null && mData[0][i].equals(name))
				return i;
		return 0;
	}
}