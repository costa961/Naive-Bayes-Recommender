# Naive Bayes Recommender

A **content-based** movie recommender written in Java. It uses a **Naive Bayes** classifier to suggest movies to a user of the MovieLens 100K dataset, based on movie features (director, writers, stars, genres).

## How it works

1. **Data loading**
   - `ua.base`: MovieLens ratings in the format `user  item  rating  timestamp`.
   - `content.xml`: descriptions of the 1682 movies (title, director, writers, stars, genres, ...).
2. **User profile (training set)**: two categories are built for the selected user:
   - `like`: features of movies rated **4 or 5**;
   - `not_like`: features of movies rated **1 or 2**.

   The extracted features are `director`, `writer`, `star` and `genre`.
3. **Test set**: every movie the user has **not rated** (movies with missing information are skipped).
4. **Classification**: each unrated movie is classified with the Naive Bayes argmax, producing a triple `<item ID, category, probability>`.
5. **Output**: movies classified as `like` are sorted by descending probability and the top 10 are printed, along with the training sets of both categories.

## Project structure

```
Recommender_CB/
├── content.xml          # movie metadata
├── ua.base              # MovieLens ratings (training split "a")
└── src/
    ├── Bayes/           # Naive Bayes classifier
    │   ├── Classifier.java, BayesClassifier.java, Classification.java, IFeatureProbability.java
    │   └── Item_Category_Probability.java   # <item, category, probability> triple
    └── Dataset/
        ├── Functions.java         # parsing, training/test set construction, recommendation
        ├── Movie.java             # single movie information
        ├── User_Item_Rating.java  # single dataset rating
        └── Test.java              # example entry point
```

## Requirements

- Java 8 or later (no external dependencies).

## Running

`content.xml` and `ua.base` are read from the current directory, so the program must be run from the `Recommender_CB` folder.

```bash
cd Recommender_CB
javac -d bin src/Bayes/*.java src/Dataset/*.java
java -cp bin Dataset.Test        # default user: 13
java -cp bin Dataset.Test 42     # recommendations for user 42
```

Alternatively, import the `Recommender_CB` folder into Eclipse as an existing project and run `Dataset.Test`.

### Sample output

```
Training set Like:
[<director>, <writer>, <star>, ..., <genre>, ...]
Training set Not Like:
[...]
All' utente potrebbero piacere :
[ [ Id: ... ] , [ Category: like ] , [ Probability: ... ] ]
...
```

## Credits

- Naive Bayes classifier: [ptnplanet/Java-Naive-Bayes-Classifier](https://github.com/ptnplanet/Java-Naive-Bayes-Classifier) (Philipp Nolte).
- Dataset: [MovieLens 100K](https://grouplens.org/datasets/movielens/100k/) by GroupLens.