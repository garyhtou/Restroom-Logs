# Restroom-Logs
School Restroom Logs
Quick and easy way to log who goes to the bathroom using barcodes on the back of Students' I.D. Cards

SETUP ---------------------
Download Raspbian RASPBIAN STRETCH WITH DESKTOP https://www.raspberrypi.org/downloads/raspbian/
unzip
flash to micro sd card using win32DiskImager https://sourceforge.net/projects/win32diskimager/
wait
wait
wait!!
Plug PI into internet (wifi/ethernet)
plug display into pi
plug mouse/keyboard into pi
plug in micro sd card
plug pi into power
set up
open raspi-config
adjust settings

BOOT SPLASHSCREEN --------
install putty for ssh
insert mircro sd into a computer to make these changes easier: vvvvvv
Disable the Raspberry Pi ‘color test’ by adding the line disable_splash=1 to /boot/config.txt.
Disable the Raspberry Pi logo in the corner of the screen by adding logo.nologo to /boot/cmdline.txt.
Disable the various bits of output from the kernel and friends by adding consoleblank=0 loglevel=1 quiet to /boot/cmdline.txt.
Disable the login prompt by running systemctl disable getty@tty1 as root. (sudo su for root, su pi for pi)
THEN:
http://www.raspberry-projects.com/pi/pi-operating-systems/raspbian/custom-boot-up-screen.
image of boot splash screen is at /assets/logos/Restroom Logs Boot Splash Screen.png

SSH MOTD ------------------
delete content of /etc/motd
added your motd to /home/pi/.profile

		#CUSTOM SSH MOTD
		let upSeconds="$(/usr/bin/cut -d. -f1 /proc/uptime)"
		let secs=$((${upSeconds}%60))
		let mins=$((${upSeconds}/60%60))
		let hours=$((${upSeconds}/3600%24))
		let days=$((${upSeconds}/86400))
		UPTIME=`printf "%d days, %02dh%02dm%02ds" "$days" "$hours" "$mins" "$secs"`

		echo "$(tput setaf 6)
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		████████████████▒     ░████████████████    ███████████████▓     ░██████████████
		███████████████░       ░███████████████    ██████████████▒        █████████████
		███████████████░       ░███████████████    ██████████████▓        █████████████
		████████████████░     ░████████████████    ████████████████     ░██████████████
		███████████████████████████████████████    ████████████████████████████████████
		███████████░               ░███████████    █████████                   ▒███████
		██████████                   ██████████    ████████                     ░██████
		█████████░   ░░         ░░   ░█████████    ███████░   ░░           ░░░   ██████
		█████████   ██░         ░██   ▓████████    ███████░  ░██           ░██   ██████
		████████░  ░██           ██░   ████████    ███████░  ░██           ░██   ██████
		███████▒   ██░            ██   ░███████    ███████░  ░██           ░██   ██████
		███████   ░██             ▒█▓   ███████    ███████░  ░██           ░██   ██████
		██████░   ██░              ██░  ░██████    ███████░  ░██           ░██   ██████
		██████░  ██▓               ░██  ░██████    ███████░  ░██           ░██   ██████
		███████████                 ███████████    ███████▓  ░██           ░██  ░██████
		██████████░                 ░██████████    █████████████    ░██    ░███████████
		██████████                   ██████████    █████████████    ░██    ░███████████
		█████████░░░░░░    ░░   ░░░░░░█████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ░███████████
		███████████████   ░██   ░██████████████    █████████████    ░██    ▒███████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		███████████████████████████████████████████████████████████████████████████████
		$(tput bold) $(tput setaf 0)
						 $(tput setab 6) Developed by Gary Tou and Michael Schwamborn $(tput sgr0)
		"
		sleep 0.5
		echo "$(tput setaf 2)
		   .~~.   .~~.    `date +"%A, %e %B %Y, %r"`
		  '. \ ' ' / .'   `uname -srmo`$(tput setaf 1)
		   .~ .~~~..~.    
		  : .~.'~'.~. :   Uptime...............: ${UPTIME}
		 ~ (   ) (   ) ~  Memory...............: `cat /proc/meminfo | grep MemFree | awk {'print $2'}`kB (Free) / `cat /proc/meminfo | grep MemTotal | awk {'print $2'}`kB (Total)
		( : '~'.~.'~' : ) 
		 ~ .~ (   ) ~. ~  IP Addresses.........: `hostname -I` and `wget -q -O - http://icanhazip.com/ | tail`
		  (  : '~' :  )   
		   '~ .~~~. ~'    Weather in Seattle...: `curl -s "http://rss.accuweather.com/rss/liveweather_rss.asp?metric=0&locCode=NAM|US|WA|SEATTLE" | sed -n '/Currently:/ s/.*: \(.*\): \([0-9]*\)\([CF]\).*/\2°\3, \1/p'`
			   '~'
			   
		$(tput setaf 2)SSH Users:
		$(tput setaf 1)`who`
		$(tput sgr0)
		$(tput bel)"
