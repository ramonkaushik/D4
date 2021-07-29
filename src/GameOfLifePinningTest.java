import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameOfLifePinningTest {
	/*
	 * READ ME: You may need to write pinning tests for methods from multiple
	 * classes, if you decide to refactor methods from multiple classes.
	 * 
	 * In general, a pinning test doesn't necessarily have to be a unit test; it can
	 * be an end-to-end test that spans multiple classes that you slap on quickly
	 * for the purposes of refactoring. The end-to-end pinning test is gradually
	 * refined into more high quality unit tests. Sometimes this is necessary
	 * because writing unit tests itself requires refactoring to make the code more
	 * testable (e.g. dependency injection), and you need a temporary end-to-end
	 * pinning test to protect the code base meanwhile.
	 * 
	 * For this deliverable, there is no reason you cannot write unit tests for
	 * pinning tests as the dependency injection(s) has already been done for you.
	 * You are required to localize each pinning unit test within the tested class
	 * as we did for Deliverable 2 (meaning it should not exercise any code from
	 * external classes). You will have to use Mockito mock objects to achieve this.
	 * 
	 * Also, you may have to use behavior verification instead of state verification
	 * to test some methods because the state change happens within a mocked
	 * external object. Remember that you can use behavior verification only on
	 * mocked objects (technically, you can use Mockito.verify on real objects too
	 * using something called a Spy, but you wouldn't need to go to that length for
	 * this deliverable).
	 */

	/* TODO: Declare all variables required for the test fixture. */
	Cell[][] cellMatrix;
	MainPanel mp;

	@Before
	public void setUp() {
		/*
		 * TODO: initialize the text fixture. For the initial pattern, use the "blinker"
		 * pattern shown in:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns
		 * The actual pattern GIF is at:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_blinker.gif
		 * Start from the vertical bar on a 5X5 matrix as shown in the GIF.
		 */
		cellMatrix = new Cell[5][5];
		mp = new MainPanel(5);
		//mocking all of the cells in the cell matrix 
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				cellMatrix[i][j] = Mockito.mock(Cell.class);
				//making them all false and will change to vertical pattern later
				Mockito.when(cellMatrix[i][j].getAlive()).thenReturn(false);
			}
		}
		//making the vertical pattern
		Mockito.when(cellMatrix[1][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[2][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[3][2].getAlive()).thenReturn(true);
		mp.setCells(cellMatrix);
	}

	/* TODO: Write the three pinning unit tests for the three optimized methods */
	
	/**
	 * Pinning test for MainPanel iterateCell(x,y);
	 * Preconditions: Mainpanel created with 5x5 of cells. Vertical blinker bar is configured
	 * Execution steps: Call mp.iterateCell(0,0)
	 * Postconditions: Method returns false
	 */
	@Test
	public void pinningTestIterateCell()
	{
		//mocking all of the cells in the cell matrix 
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				cellMatrix[i][j] = Mockito.mock(Cell.class);
				//making them all false and will change to vertical pattern later
				Mockito.when(cellMatrix[i][j].getAlive()).thenReturn(false);
			}
		}
		//making the vertical pattern
		Mockito.when(cellMatrix[1][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[2][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[3][2].getAlive()).thenReturn(true);
		mp.setCells(cellMatrix);
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if ((i == 2 && j == 2) || (i == 2 && j == 3) || (i == 2 && j == 1)) {
					assertTrue("Cell " + i + " " + j + " was not true as expected" , mp.iterateCell(i,j));
				}
				else {
					assertFalse("Cell " + i + " " + j + " was not false as expected" , mp.iterateCell(i,j));
				}
			}
		}
	}

	/**
	 * Pinning test for Cell toString()
	 * Preconditions: MainPanel has 25 mocked cells and is in the blinker pattern
	 * Execution steps: Call cell.toString() for each cell
	 * Postconditions: return value is "X" for true and "." for false
	 */
	@Test
	public void pinningTestCellToString() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if ((i == 1 && j == 2) || (i == 2 && j == 2) || (i == 3 && j == 2)) {
					assertEquals("Cell " + i + " " + j + " was not X as expected" , cellMatrix[i][j].toString() , "X");
				}
				else {
					assertEquals("Cell " + i + " " + j + " was not . as expected" , cellMatrix[i][j].toString() , ".");
				}
			}
		}
	}

	/**
	 * Pinning test for calculating te next iteration
	 * Preconditions: main panel has 25 mocked cells and is in blinnker pattern
	 * Execution steps: Call mp.calculateNextIteration()
	 * Postconnditions: Verify set alive is called for each new cell
	 */
	@Test
	public void pinningTestCalculateNextIteration() {
		//mocking all of the cells in the cell matrix 
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				cellMatrix[i][j] = Mockito.mock(Cell.class);
				//making them all false and will change to vertical pattern later
				Mockito.when(cellMatrix[i][j].getAlive()).thenReturn(false);
			}
		}
		//making the vertical pattern
		Mockito.when(cellMatrix[1][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[2][2].getAlive()).thenReturn(true);
		Mockito.when(cellMatrix[3][2].getAlive()).thenReturn(true);
		mp.setCells(cellMatrix);
		mp.calculateNextIteration();
		Cell[][] tempCellMatrix = mp.getCells();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if ((i == 2 && j == 2) || (i == 2 && j == 3) || (i == 2 && j == 1)) {
					verify(tempCellMatrix[i][j]).setAlive(true);
				}
				else {
					verify(tempCellMatrix[i][j]).setAlive(false);
				}
			}
		}
	}




}
