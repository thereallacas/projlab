/**
 * 
 */
package ballmerpeak.stargate.proto;

import ballmerpeak.stargate.commands.InputCommand;
import ballmerpeak.stargate.commands.InputCommandFactory;

/**
 * @author ballmerpeak
 *
 */
public class ProtoInputCommandFactory extends InputCommandFactory {

	private String string;
	
	public boolean oneil = true;

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
			oneil = false;
			pss = jaffaChooser;
			return unknown;
		} else if (words[0].startsWith("o")) {
			oneil = true;
			pss = kebabChooser;
			return unknown;
		}

		switch (words[0]) {
		case "pickup":
		case "p":
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
			if (words.length != 2)
				return unknown;
			switch (words[1]) {
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
