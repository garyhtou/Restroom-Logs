package mainProgram;

/**
 * Restroom Log is a Java program designed to create ease for teachers.
 * Restroom Logs allow student to sign in and out when using the restroom.
 * This allows teachers to set this up and forget about it.
 * <br><br>
 * This program contains many features such as:
 * <ul>
 * <li>Barcode Scanning (alternative is typing in barcode)</li>
 * <li>Custom student database</li>
 * <li>Emailable PDF of student entry and exits</li>
 * <li>List of students who are currently signed out</li>
 * <li>Customizable information section</li>
 * </ul>
 * Restroom Logs was designed to be ran on a dedicated Raspberry Pi.
 * <br>
 * <br>
 * <strong>Requirements:</strong>
 * <ul>
 * <li><a href = "https://www.raspberrypi.org/products/">Raspberry Pi</a>
 *     <ul>
 *     <li>B+ or newer</li>
 *     <li>USB Port</li>
 *     <li>HDMI Port for a monitor</li>
 *     <li>Internet connection (Either through Wifi for Ethernet)</li>
 *     <li>Micro SD Card with atleast 8GB to run Raspbian OS
 *     <li><a href="https://www.raspberrypi.org/downloads/raspbian/">Raspbian OS</a> installed on the RPI</li>
 *     <li><a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">Oracle Java</a> 8+ installed on Raspbian</li>
 *     </ul>
 * </li>
 * <li>Display for the RPI</li>
 * <li>Cooling solution for the RPI</li>
 *     <ul>
 *     </li>Since this will be running 24/7, you will need a cooling solution to keep the RPI cool</li>
 *     </ul>
 * <strong>Optional:</strong>
 *     <ul>
 *     <li>Barcode Scanner</li>
 *         <ul>
 *         <li>Make sure it send the Enter Key once it scans the barcode (Most scanner does this)</li>
 *         </ul>
 *     <li>YOUR DIGNITY</li>
 *     </ul>
 * </ul>
 * <strong>Note:</strong><br>
 * When running this program, make sure to use the splashscreen by setting up the following argument.<br>
 * <blockquote>-splash:assets/logos/RestroomLogsSplashscreen.png</blockquote>
 * To learn how to run the program with this argument, please visit <a href="rl.coding2kids.com/BLAH">rl.coding2kdis.com/BLAH</a><br>
 * <br>
 * <h3>Please view the Read Me file for more information<h3><br>
 * 
 * <div></div>
 *  Copyright (C) 2018  Gary Tou and Michael Schwamborn
 *  <div></div>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * <div></div>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * <div></div>
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * @version BETA 0.1.0
 * @author Gary Tou
 * @author Michael Schwamborn
 */
public class MAIN {
	//DO THREADS IN HERE
	public static void main(String[] args) {
		startUp();
	}
	
	/**
	 * Call this method to start up the program
	 */
	public static void startUp() {
		//-splash:assets/logos/RestroomLogsSplashscreen.png
		config.checkRanBefore();
		//FrontEnd.Splash.init();
		FrontEnd.splashScreen.create();
	}
}
