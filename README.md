BurnBlader's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ How can energy be harnessed and used in the Minecraft world?
- __Time:__ Time 2 (7/12/2014 09:00 to 7/12/2014 19:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ None

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/BurnBlader-t2`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

How to use
----------

This plugin uses energy from Minecraft's many elements. With this energy you can do various things. To release this Energy, you hold shift on your selected object. The features are:

- Diamond Ore: Resistance 6 - Cost: 20 Energy
- Dirt: Teleports you to y: 16 - Cost: 40 Energy
- Glass: Launches you into the air and you land on a glass platform - Cost: 10 Energy
- Grass: Creates a small explosion where you're standing - Cost: 5 Energy
- Stone: Launches you into the air, and when you land you damage entities around you - Cost: 50 Energy
- Water: You move quickly in the direction you look - Cost: 20 Energy
- Netherrack: You create a trail of fire behind you - Cost: 20 Energy
- Air: You fly for a few seconds - Cost: 20 Energy
- Wool: Teleports you to your bed location - Cost: 10 Energy

How to receive Energy
---------------------

To get Energy you can hit mobs using a Energy Sapper. You can craft this with this recipe:

DDD

DSD

DSD

D = Diamond
S = Stick

You can also receive energy using an Explosion Block. To use this, place a Redstone Lamp. When the Redstone Lamp is exploded, it fills with Energy. To steal this Energy, right click the now Glowstone, and you will receive the Energy.
