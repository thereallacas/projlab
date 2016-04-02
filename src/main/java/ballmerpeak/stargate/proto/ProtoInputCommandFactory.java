/**
 * 
 */
package ballmerpeak.stargate.proto;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandFactory;
import ballmerpeak.stargate.commands.PlayerSelectionStrategy;

/**
 * @author ballmerpeak
 *
 */
public class ProtoInputCommandFactory extends InputCommandFactory {

	private String string;

	public void setInputString(String string) {
		this.string = string;
	}

	@Override
	public InputCommand nextCommand() {
		String words[] = string.split(" ");

		if (words.length == 0)
			return unknown;

		if (words[0].startsWith("q"))
			return quit;

		if (words[0].startsWith("j")) {
			pss = jaffaChooser;
		} else if (words[0].startsWith("p")) {
			pss = kebabChooser;
		}

		switch (words[1]) {
		case "pickup":
			return pickup;
		case "up":
		case "u":
			return moveUp;
		case "down":
		case "d":
			return moveDown;
		case "left":
		case "l":
			return moveLeft;
		case "right":
		case "r":
			return moveRight;
		case "shoot":
			if (words.length != 3)
				return unknown;
			switch (words[2]) {
			case "yellow":
			case "y":
				return shootYellow;
			case "blue":
			case "b":
				return shootBlue;
			case "green":
			case "g":
				return shootBlue;
			case "red":
			case "r":
				return shootYellow;
			default:
				return unknown;
			}
		default:
			return unknown;
		}
	}

}
