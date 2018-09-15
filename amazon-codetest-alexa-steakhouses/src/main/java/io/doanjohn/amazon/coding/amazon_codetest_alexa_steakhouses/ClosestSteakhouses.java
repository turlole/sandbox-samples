package io.doanjohn.amazon.coding.amazon_codetest_alexa_steakhouses;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Class</b>: Closest Steakhouses <br />
 * <b>Purpose</b>: return a list closest steakhouses. A given number of
 * requested entries will be provided as part of function input. <br />
 * <b>Pseudocode</b>: <br />
 * 
 * <pre>
 * <code>
 *     Input 1: Size of coordinates list.
 *     Input 2: Array list of coordinates Coordinates.
 *     Input 3: Requested size of output to return.  
 *     Output: list of results Result.
 *     
 *     FOR (Coordinate coordinate : Coordinates)
 *         Get x coordinate.
 *         Get y coordinate.
 *         Calculate distance => sqrt(x^2 + y^2).
 *         IF Result size == 0 THEN
 *             smallest distance = current coordinate distance.
 *             largest distance = current coordinate distance.
 *             add coordinate to output list.
 *         ELSE IF ( Result size < requested size OR distance <= smallest distance OR distance <= largest distance) THEN
 *             FOR (Coordinate resultCoordinate : Coordinates in Result list)
 *                 Get x coordinate of resultCoordinate.
 *                 Get y coordinate of resultCoordinate.
 *                 Calculate resultCoordinate distance => sqrt(x^2 + y^2).
 *                 
 *                 IF (current coordinate distance <= resultCoordinate distance) THEN
 *                     IF (resultCoordinate is FIRST in list) THEN
 *                         smallest distance = current coordinate distance
 *                         Set insert pointer counterResultListPointer to insert latset coordinate.
 *                         BREAK out of FOR LOOP 
 *                     END IF 
 *                 END IF
 *                 
 *                 IF (resultCoordinate is LAST in list AND current coordinate distance <= resultCoordinate distance) THEN
 *                     max distance = current coordinate distance
 *                 END IF 
 *             END FOR
 *             
 *             IF (counterResultListPointer <= requested size of output) THEN
 *                 add coordinate to output list at location counterResultListPointer.
 *             ELSE
 *                 add coordinate to output list at end of list.
 *             END IF  
 *             
 *             IF (requestedNumber IS LESS THAN output result size) THEN
 *                 Remove last coordinate entry in output result list.
 *             END IF   
 *         END IF  
 *     END FOR
 * </code>
 * </pre>
 * 
 * <br />
 * <br />
 * 
 * @author john.doan
 *
 */
public class ClosestSteakhouses {

	/**
	 * @param numberSteakhouses
	 *            total number of steakhouses
	 * @param coordinates
	 *            full list of coordinates of steakhouses
	 * @param requestedNumber
	 *            requested nmber of steakhouses
	 * @return a list of sorted closest steakhouses
	 */
	public List<List<Integer>> getClosestSteakhouses(int numberSteakhouses, List<List<Integer>> coordinates,
			int requestedNumber) {

		// validate input
		if (requestedNumber > numberSteakhouses) {
			throw new RuntimeException(String.format(
					"Error: Number of requested number of steakhouses '%d' is greater than total number of available steakhouses '%d'",
					requestedNumber, numberSteakhouses));
		}

		List<List<Integer>> result = new ArrayList<List<Integer>>(requestedNumber);
		double shortestDistance = -1;
		double maxDistance = shortestDistance;

		// Loop through list of coordinates
		for (List<Integer> coordinate : coordinates) {

			// 1. Calculate distance of given coordinate
			int xCoordinate = coordinate.get(0);
			int yCoordinate = coordinate.get(1);
			double distance = Math.sqrt((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate));

			// 2. Test what is currently in the result output list.
			if (result.size() == 0) {
				// 2.1 If there are no entries, automatically add coordinates into list

				// set the shortest and maximum distance ..
				shortestDistance = distance;
				// .. and max distance = shortdistance by default
				maxDistance = shortestDistance;

				// add coordinate in result
				result.add(coordinate);
			} else if (result.size() < requestedNumber || distance <= shortestDistance || distance <= maxDistance) {
				// 2.2 If the result list is not full OR the distance is <= the shortest
				// distance OR
				// <= the max distance, attempt
				// to insert the coordinates at the best location by looping through result list

				// Pointer when looping through result output list.
				int counterResultListPointer = 0;

				// 2.2.1 Attempt to loop through result list
				while (counterResultListPointer < result.size()) {
					List<Integer> resultCoordinate = result.get(counterResultListPointer);
					// TODO we will make the fair assumption resultCoordinate will always have good
					// data, and will be 2 items in the list.
					int xResultCoordinate = resultCoordinate.get(0);
					int yResultCoordinate = resultCoordinate.get(1);
					double resultCoordinateDistance = Math
							.sqrt((xResultCoordinate * xResultCoordinate) + (yResultCoordinate * yResultCoordinate));

					// 2.2.1.1 If the distance of the existing coordinate is less than or equal to
					// the pointed coordinate in the result output list, then break out of for ..
					// loop
					if (distance <= resultCoordinateDistance) {
						// 2.2.1.2 If the pointed coordinate in the result output list is == 0, set
						// shortest
						// distance to the existing coordinate distance.
						if (counterResultListPointer == 0) {
							shortestDistance = distance;
						}

						// break out of while loop here. we found our insertion point
						break;
					}

					// 2.2.2 Reset the max distance here, if index == requestedNumber -1
					if (counterResultListPointer == requestedNumber - 1 && distance <= resultCoordinateDistance) {
						maxDistance = distance;
					}

					// 2.2.3 Increment counter
					counterResultListPointer++;
				}

				// 2.3 Attempt to insert here. If the pointed coordinate in the result output
				// list is less than or equal to the requested number, then insert at the
				// pointer coordinate index ...
				if (counterResultListPointer <= requestedNumber - 1) {
					result.add(counterResultListPointer, coordinate);
				} else {
					// .. else add at end
					result.add(coordinate);
				}

				// 2.4 Attempt to remove last entry if overflow (requestedNumber < output result
				// list)
				if (requestedNumber < result.size()) {
					result.remove(result.size() - 1);
				}
			}
		}

		// fin
		return result;
	}
}
