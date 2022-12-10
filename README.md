# Primates


This is the implementation of a Sanctuary of Animals. (Check this at my [Github Portfolio](https://github.com/AlexanderHMagno/Primates))

The Sanctuary consists of two different types of housing:
- Isolation is used to keep monkeys when they first arrive at the sanctuary and anytime they are receiving medical attention. Isolation consists of a series of cages each of which can house a single animal.
- Enclosures are much larger and can host a single troop (i.e., a group) of monkeys. Each troop consists of a single species that is found in the New World (some of which are pictured below): drill, guereza, howler, mangabey, saki, spider, squirrel, and tamarin.


## How to use this implementation (VIEW)

The Jar file is located at the res folder, [here](https://github.com/AlexanderHMagno/Primates//tree/master/res/sanctuary.jar)

The view will be divided by 4 panels, where you can interact with each of them thru the tab panel located at top of the view.

From there you can select: 

### Dashboard
- Displays a quick summary of the Sanctuary
![Primate](/res/readme/dashboard.png)

### Add an Animal
- Allows you to add new animals to the Sanctuary
![Primate](/res/readme/addAnimal.png)

### Isolation
- Displays the current animals in isolation, you can check their health and send them to an enclosure only if they are healthy
![Primate](/res/readme/isolation.png)

### Enclosure
- You can view all animals per species, feed them and move them back to Isolation. Additional you can view a list per name of all animals in this area.
![Primate](/res/readme/enclosure.png)


**NOTE:**  Remember that at any point you can switch between each panel, and the search menu at the bottom can be used to search for any animal in the sanctuary


## How to use this implementation (MODEL)

- Create an instance of Sanctuary. 
- When you try to add a new member 

```JAVA
        //A sanctuary is composed of two types of housing.
        Sanctuary jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary");
        
        //You can also provide your own housing using a different constructor
```

- Then you can use several methods inside this instance to move animals around

| method  | Action | Example |
| ------------- | ------------- | ------------- |
| moveForceToEnclosure  | Cure and move all monkeys in isolation to enclosure | ```jungleFriends.moveForceToEnclosure();``` |
| addNewAnimal  | Add a new Animal to the sanctuary | ```jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);``` |
| provideMedicalAttention  | Cure a monkey | ``` jungleFriends.provideMedicalAttention("Alex");``` |
| moveAnimalToEnclosure  | move a monkey to the enclosure from isolation | ```jungleFriends.moveAnimalToEnclosure("Alex");``` |
| moveAnimalToIsolation  | move a monkey to the Isolation from enclosure | ```jungleFriends.moveAnimalToIsolation(Species.Quereza,"Alex");``` |
| getAnimalsInHabitat  | Get information of the curren inhabitants | ```jungleFriends.getAnimalsInHabitat();``` |
| getAnimalsNamesInHabitat  | Get a list with all names of inhabitants in alphabetical order | ```jungleFriends.getAnimalsNamesInHabitat()``` |


## What animals can be created at this sanctuary
You could create different types of animals, but the sanctuary will only take care of one class. Right now, we can take care of Monkeys, but we a few 
changes we can take of any animal 

- ![Primate](/res/Old-World-New-monkeys-1.jpg)


## Bibliography 

- “Java Swing Tutorial - javatpoint,” www.javatpoint.com. [Online]. Available: https://www.javatpoint.com/java-swing. [Accessed: 10-Dec-2022]. 
- https://www.junglefriends.org/
- “Getting all names in an enum as a string[],” Stack Overflow, 01-Jan-1960. [Online]. Available: https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string. [Accessed: 10-Dec-2022]. 
