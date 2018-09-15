package io.doanjohn.amazon.coding.amazon_codetest_alexa_steakhouses;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClosestSteakhousesTest {

	/**
	 * Input: 3 coordinates <br />
	 * Expected: 1 coordinate
	 */
	@Test
	public void testClosestOneSteakhouse() {
		List<List<Integer>> input = new ArrayList();
		List<List<Integer>> expected = new ArrayList();
		List<List<Integer>> actual = null;

		// arrange
		input.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(-3);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(2);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(3);
				this.add(4);
			}
		});

		expected.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(2);
			}
		});

		// act
		actual = new ClosestSteakhouses().getClosestSteakhouses(input.size(), input, 1);

		// assert equals [ 1, 2 ]
		assertEquals(expected, actual);
	}

	/**
	 * Input: 5 coordinates <br />
	 * Output: 3 coordinates
	 */
	@Test
	public void testClosestMultipleSteakhouses() {
		List<List<Integer>> input = new ArrayList();
		List<List<Integer>> expected = new ArrayList();
		List<List<Integer>> actual = null;

		// arrange
		input.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(-2);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(4);
				this.add(6);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(2);
				this.add(2);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(3);
				this.add(4);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(1);
			}
		});

		expected.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(1);
			}
		});
		expected.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(-2);
			}
		});
		expected.add(new ArrayList<Integer>() {
			{
				this.add(2);
				this.add(2);
			}
		});

		// act - 3 available, 1 output
		actual = new ClosestSteakhouses().getClosestSteakhouses(input.size(), input, 3);

		// assert equals [ 1, 2 ], [ 1, -3], [ 2, 2 ]
		assertEquals(expected, actual);
	}
	
	/**
	 * Input: 3 coordinates <br />
	 * Output: RuntimeException.  More was requested than available.
	 */
	@Test(expected = RuntimeException.class)
	public void testRequestMoreThanAvailable() {
		List<List<Integer>> input = new ArrayList();
		List<List<Integer>> expected = new ArrayList();
		List<List<Integer>> actual = null;

		// arrange
		input.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(-2);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(4);
				this.add(6);
			}
		});
		input.add(new ArrayList<Integer>() {
			{
				this.add(2);
				this.add(2);
			}
		});

		expected.add(new ArrayList<Integer>() {
			{
				this.add(1);
				this.add(-2);
			}
		});

		// act - 3 available, 100 output
		actual = new ClosestSteakhouses().getClosestSteakhouses(input.size(), input, 100);

		// assert
		// Note: we will not reach here for a RuntimeException should have been caught
		assertEquals(expected, actual);
	}
	
}
