package tests;

import java.util.ArrayList;

import model.algorithms.Action;
import model.algorithms.RandomSearcher;
import model.domain.MazeState;
import model.domain.SearchDomain;
import model.domain.State;

import org.junit.Assert;
import org.junit.Test;

/**
 * Make a test to the Random.
 * 
 * @author Dudi and Nadin
 *
 */
public class TestRandom
{
	@Test
	public void test()
	{
		RandomSearcher rs = new RandomSearcher();
		rs.setDomain(new TestDomain());
		ArrayList<Action> search = rs.search();
		for (int i = 0; i < search.size(); i++)
		{
			Assert.assertEquals(((MazeState)search.get(i).from).x,i);
			Assert.assertEquals(((MazeState)search.get(i).from).y,i);
			Assert.assertEquals(((MazeState)search.get(i).to).x,i + 1);
			Assert.assertEquals(((MazeState)search.get(i).to).y,i + 1);
		}
	}

	public class TestDomain implements SearchDomain
	{

		@Override
		public State getStart()
		{
			return new MazeState(0,0);
		}

		@Override
		public ArrayList<State> getAllPossibleStates(State s)
		{
			ArrayList<State> l = new ArrayList<State>();
			MazeState s1 = (MazeState) s;
			l.add(new MazeState(s1.x + 1, s1.y + 1));
			return l;
		}

		@Override
		public boolean isEnd(State s)
		{
			MazeState s1 = (MazeState) s;
			return s1.x == 6 && s1.y == 6;
		}

		@Override
		public void print()
		{
		}

		@Override
		public String getUniqDescription()
		{
			return null;
		}

		@Override
		public String getDomainName()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getAlgorithmName()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAlgoName(String name)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
