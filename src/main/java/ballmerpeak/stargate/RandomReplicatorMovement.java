/**
 * 
 */
package ballmerpeak.stargate;

/**
 * @author ballmerpeak
 *
 */
public class RandomReplicatorMovement implements ReplicatorMovementStrategy {

	/* (non-Javadoc)
	 * @see ballmerpeak.stargate.ReplicatorMovementStrategy#getDirection()
	 */
	@Override
	public Direction getDirection() {
		return Direction.randomDirection();
	}

}
