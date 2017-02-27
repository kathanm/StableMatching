# StableMatching

This is an implementation of the Gale-Shapley stable matching algorithm in Java. 

To maintain flexibility and be used for multiple purposes, there is an abstract Matchable class which is the mold for what is required for any class that wishes to be able to be matched. It allows the user to set their own custom restrictions on what and what not the class should be matched to. 

The match class itself is the structure in which the two lists are maintained and updated as the matching algoirthm works. 

Included are sample Boy and Girl classes to serve as examples of Matchables. 
