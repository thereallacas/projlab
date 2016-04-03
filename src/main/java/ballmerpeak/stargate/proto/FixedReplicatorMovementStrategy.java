/**
 * 
 */
package ballmerpeak.stargate.proto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import ballmerpeak.stargate.Direction;
import ballmerpeak.stargate.ReplicatorMovementStrategy;

/**
 * @author ballmerpeak
 *
 */
public class FixedReplicatorMovementStrategy implements ReplicatorMovementStrategy {

	List<Direction> directions;
	int index;

	public FixedReplicatorMovementStrategy(String filename) throws Exception {
		directions = new ArrayList<>();
		index = 0;
		try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
			String line = br.readLine();
			String[] words = line.split(" ");
			for (String word : words) {
				switch (word) {
				case "up":
					directions.add(Direction.UP);
					break;
				case "down":
					directions.add(Direction.DOWN);
					break;
				case "left":
					directions.add(Direction.LEFT);
					break;
				case "right":
					directions.add(Direction.RIGHT);
					break;
				default:
					throw new Exception("invalid direction read from file " + filename);
				}
			}
		}
	}

	@Override
	public Direction getDirection() {
		return directions.get(index < directions.size() - 1 ? index++ : index);
	}

}
