package linkedbrainz.testcases.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLResultSet
{

	public ResultSet resultSet = null;
	public Statement statement = null;

	public SQLResultSet(ResultSet resultSet, Statement statement)
	{
		this.resultSet = resultSet;
		this.statement = statement;
	}

	public ResultSet getResultSet()
	{
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet)
	{
		this.resultSet = resultSet;
	}

	public Statement getStatement()
	{
		return statement;
	}

	public void setStatement(Statement statement)
	{
		this.statement = statement;
	}

	public void close()
	{
		if (statement != null)
		{
			try
			{
				statement.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
