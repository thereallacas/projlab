/**
 * 
 */
package ballmerpeak.stargate.utils;

import java.util.Random;

import ballmerpeak.stargate.tiles.Floor;
import ballmerpeak.stargate.tiles.ZPMGeneratingStrategy;

/**
 * @author ballmerpeak
 *
 */
public class RandomZPM implements ZPMGeneratingStrategy {

	private Random random = new Random();

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.tiles.ZPMGeneratingStrategy#getFloorForNewZPM()
	 */
	@Override
	public Floor getFloorForNewZPM() {
		Floor floor = null;
		do {
			floor = Floor.floors.get(random.nextInt(Floor.floors.size()));
			assert (Floor.floors.contains(floor));
		} while (floor.hasEntity() || floor.hasZPM());
		return floor;
	}

}
