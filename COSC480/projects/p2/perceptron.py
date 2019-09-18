import random
import numpy as np

def error_function(X, y, w):
    """Compute the error of perceptron, defined by weights w, on examples (X, y).

    Inputs:
    X        double array of training examples
                X[i] is ith example,
                X[i][j] is jth feature value of ith example
    y        array of labels
                y[i] is label of ith example
    w        array of weights, w[j] is jth weight

    Returns:
    error    fraction of misclassified examples
    """
    error = 0.0
    ##################################################
    # TODO: write code here to compute error correctly
    ##################################################
    raise NotImplementedError()


def pla(X, y, t_max):
    """Implements the Perceptron Learning Algorithm (PLA) on examples (X, y).

    Inputs:
    X        double array of training examples
                X[i] is ith example,
                X[i][j] is jth feature value of ith example
    y        array of labels
                y[i] is label of ith example
    t_max    maximum number of iterations

    Returns:
    (w, Ws)  w is the final weight vector w
             Ws is a list of the weight vector at every iteration                  
    """
    n = len(X)
    assert n > 0, "No training examples provided"
    d = len(X[0])
    assert d > 0, "First training example has no features"

    ##################################################
    # TODO: finish the implementation of PLA
    ##################################################
    raise NotImplementedError()


def pocket(X, y, t_max):
    """Implements the Pocket Algorithm on examples (X, y).

    Inputs:
    X        double array of training examples
                X[i] is ith example,
                X[i][j] is jth feature value of ith example
    y        array of labels
                y[i] is label of ith example
    t_max    maximum number of iterations

    Returns:
    (w, Ws)  w is the final weight vector w
             Ws is a list of the weight vector at every iteration                  
    """
    n = len(X)
    assert n > 0, "No training examples provided"
    d = len(X[0])
    assert d > 0, "First training example has no features"

    ##########################################################
    # TODO: finish the implementation of the pocket algorithm
    ##########################################################
    raise NotImplementedError()


def featurize(x):
    """Converts 400 image array into 2 features of your choosing

    Inputs:
    x        1d array of 400 image pixels

    Returns:
    (x_1, x_2)  a pair of feature values
    """
    ##################################################
    # TODO: write code here to compute error correctly
    ##################################################
    # you find this line helpful
    img = x.reshape(20,20)      # img is a 2-array where img[i][j] is pixel in ith row and jth col
    raise NotImplementedError()
