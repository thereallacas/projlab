# Map design

## File format

- first line: number of rows
- second line: number of columns

- edge of map has to be walled

### legend:

- '#' wall
- ' ' floor
- '@' player start position
- '0' pit
- '$' zpm
- '%' crate
- '>' special wall facing right
- '<' special wall facing left
- '^' special wall facing up
- '/' special wall facing down

example in src/test/resources/maps/map1.txt

## to try the new map

- place it next to map1.txt
- edit the map loading code in src/main/java/ballmerpeak/stargate/gui/GameWindow.java main method with the new filename
