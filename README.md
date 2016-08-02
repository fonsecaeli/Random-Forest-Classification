# Random-Forest-Classification

NWAPW Project Proposal 
Random Forest Classification

FUNCTIONAL OVERVIEW

The project will be to implement a classifier which employs decision trees.  The program will either be a web application or run from the command line interface.  Data will be accepted in the form of text file prescribing to some (yet to be determined) formatting.  Outputs will be saved as text files (also in a yet to be determined format).  The expectation for the random forest is for it to be fairly robust allowing it to be trained and theoretically be used for many different classification problems.    

DESIGN OVERVIEW

The user will provide an input file that contains a data set that is comprised different objects with a set of values along with an identifier of the objects class.  My program will create a Random Forest (a set of decision trees) based of the sample data set to perform classification for other objects.

Once the Random Forest has been created it will be saved in memory.  Users will then be able to feed in unclassified data sets.  These data sets will be fed through the decision trees.  A combination or mean of the classifications each tree in the Random Forest produces will be used to determine the classification of a given object.  The results of the classification process will be saved in a text file.  Objects to classify may also be passed in through the command line; in this case the resulting classification will be displayed on the command line.  Other option is to make this a web application which users can upload data too be classified.       

PRIORITIZATION OF FEATURES

Features in a ranked list from highest priority to lowest:

Program can create at least make one functioning decision tree from a data set
User enters data set by naming a file contains the data
Classification objects is displayed on the command line/or website
Implements a fully functional Random Forest 
Program can output the classification of a data set to a text file/or website
Handling both continuous and discrete attributes

DESIGN DETAILS
	
The classifier will consist of two major parts: the implementation of the Random Forest and a system to parse input data from a text file to be used to create the decision trees.  
Each tree in the Random Forest will be grown according to the following algorithm:
Select a random subspace of the input set  this will be the data used to generate the decision tree
If there are N input variables for each object in  specify a number M < N so that at each node, M parameters out of N are selected randomly and the best split on these variables are used to split the data at his node.  M should be determined using the out-of-bag error rate (oob) so that the error of the Random Forest is minimized.  M should also be held constant for all trees in the forest
Grow each tree to its largest extent, do not prune

Keeping the above algorithm in mind each individual decision tree will be grown using the ID3 algorithm.

Brief Summary of the ID3 algorithm:
Calculate the entropy of every variable contained in each object of set 
Split  into subsets using the variable for which entropy is minimum (or, equivalently, information gain is maximum)
Create a branch node containing information on how  was split at that point
Recurse on subsets using remaining variables.
More detail on how entropy is used to determine how to split data:
The information gain is based on the decrease in entropy after a dataset is split on an attribute. Constructing a decision tree is all about finding attribute that returns the highest information gain (i.e., the most homogeneous branches).  Information gain can be defined as the difference between the entropy before splitting the data on an attribute and the entropy of that attribute.  Entropy of a given attribute, A, is given by the following equation.  Where m is the total number of classifications the given data set includes.
 E(A)= i=1m-pilog2 pi  
We also need to determine the entropy after splitting the data base of one attribute.  This is given by the following equation, where P(C) is the probability of a given case of X.   
E(T,X)= C∈XP(C)E(C)
The information gain of a given split S is defined by the following:
G(S)= E(A)-E(T,X)
ID3 uses a greedy algorithm to find the best possible split, calculating the information gain for every possible split and then finding the minimum.  This shouldn’t be an issue if the data sets being used define their attributes discreetly.  Time efficiency arises if we are trying to deal with continuous attributes because there are too many ways the split the data at each node.  To deal with this if we are dealing with continuous attributes, then I will define a threshold for the attributes so that continues attributes can be split into discrete sections.  I am interested in exploring other algorithms to build the Random Forest ID3 should just be the starting point.
