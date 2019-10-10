import random
import numpy as np
import random

def test_function(x):
    print("here is x")
    print(x)

def error_function(w, X, y):
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
    ##################################################
    # TODO: write code here to compute error correctly
    ##################################################
    X_temp = X.copy()
    X_temp = np.insert(X_temp, 0, 1, axis=1)
    y_out = np.dot(X_temp,w)
    y_hat = np.where(y_out >= 0, 1, -1)
    error = sum(np.where(y_hat == y, 0, 1)) / len(y)
    return(error)


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
    X_temp = X.copy()
    ## add X0
    X_temp = np.insert(X_temp, 0, 1, axis=1)
    w = np.zeros(len(X_temp[0]))
    Ws = list([w])
    ## iterative update
    for i in range(t_max):
        y_hat = np.where(np.dot(X_temp,w) >= 0, 1, -1)
        if sum(np.where(y_hat == y, 0, 1)) == 0:
            break
        #print(error_function(X_temp, y, w))
        idx = random.choice(np.where(y_hat != y)[0])
        w = np.add(w,np.multiply(y[idx],X_temp[idx]))
        Ws.append(w)
    return((w,Ws))
        



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
    X_temp = X.copy()
    ## add X0
    X_temp = np.insert(X_temp, 0, 1, axis=1)
    w = np.zeros(len(X_temp[0]))
    Ws = list([w])
    w_pocket = w
    for i in range(t_max):
        y_hat = np.where(np.dot(X_temp,w) >= 0, 1, -1)
        if sum(np.where(y_hat == y, 0, 1)) == 0:
            break
        idx = random.choice(np.where(y_hat != y)[0])
        w = np.add(w,np.multiply(y[idx],X_temp[idx]))
        ## check if new min
        if error_function(w,X,y) < error_function(w_pocket,X,y):
            w_pocket = w
        Ws.append(w_pocket)
    return((w_pocket,Ws))


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
    intensity = np.sum(np.abs(x))
    flipped = np.flip(img,1).reshape(1,400)
    symmetry = np.sum(np.abs(np.subtract(flipped, x)))
    return(intensity,symmetry)
