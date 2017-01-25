# Adventurers-Archive
An Android application that allows the user to easily manage character information for D&amp;D 5th Edition.

### Team Members
- Darin Beaudreau (Project Leader)
- Ethan Goldman
- Anthony Rinaldi

## Progress Report 3

### Tasks
Fortunately we have implemented about 80% of the app at this point. The last thing to do before presentation day is the CharacterSpellbookActivity, which is well underway. Most of what is left to do is querying the database for the information we want and then working with it, as the database is mostly functional. Everyone else has completed their tasks.

- Darin Beaudreau
    - Completed the Character Sheet activity, which is now fully functional. It was mostly fine-tuning how everything interacts with the CharacterInfo class and making the sheet itself look pretty.
    - Created an XML parser for the Spell information contained in phb_spells.xml.
    - Began implementation of the Spells database, which is created from the XML data if the database does not already exist, and then queried.
- Ethan Goldman
    - Completed the Equipment activity. Specifically, he made the equipment items clickable so you can see more information about the equipment. He also implemented the ability to delete equipment from your list.
    - Added total weight to the Equipment activity so you can see how much gear you're carrying.
- Anthony Rinaldi
    - Completely implemented the Skills activity, including creating the layout with all components and adding the logic behind the buttons, which pulls the information from the CharacterInfo object so a Toast can be created showing the results of a "skill check", which is the result of a random number 1-20 added to the bonus the character gets to that skill.
    - Made a few quick patches to the appearance of Skill activity text and components.
