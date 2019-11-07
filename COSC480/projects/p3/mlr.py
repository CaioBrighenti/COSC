import numpy as np
import matplotlib.pyplot as plt

def feature_expansion(x, deg=4):
    '''Takes an vector of n scalar values and returns an (n x deg+1)
    matrix by applying the feature expansion
                x ==> [1, x, x^2, x^3, x^4, ..., x^d]
    to each scalar x value.

    Inputs:
        x       n scalar values (array of length n)
        deg     desired polynomial degree
    Output:
        X       n x (d+1) matrix (2d array)
    '''
    if x.ndim > 1:
        raise ValueError('x should be a 1-dimensional array')
    x_powers = [x**k for k in range(0,deg+1)]
    X = np.stack( x_powers, axis=1 )
    return X

def generate_dataset():
    '''Generates data from a 4 degree polynomial noisy target function.
    Inputs:
        None
    Output:
        x       100 training examples (each is a single feature)
        y       corresponding y for each training example
    '''
    # Set random seed
    np.random.seed(0)

    # Create random set of N training x values between -5 and 5
    N = 100
    x = np.random.rand(N)*10 - 5

    # Feature expansion
    deg = 4
    X = feature_expansion(x, deg)

    # Define weights w and generate y values
    w = 0.1*np.array([1, 1, 10, 0.5, -0.5]);
    y = np.dot(X, w) + np.random.randn(N)   # polynomial plus noise
    return x, y

def normalize(X):   
    X_norm = (X[:,1:] - np.mean(X[:,1:],axis=0)) / np.std(X[:,1:],axis=0)
    X_norm = np.insert(X_norm, 0, X[:,0], axis=1)
    return(X_norm)

def un_normalize(w,X):
    w_copy = w.copy()
    w_copy[0] = w_copy[0] - np.sum(
                                np.divide(
                                    np.multiply(
                                        w[1:],
                                        np.mean(X[:,1:],axis=0)
                                    ),
                                    np.std(
                                        X[:,1:],
                                        axis=0
                                    )
                                )
                            )
    X_stds = np.insert(np.std(X[:,1:],axis=0), 0, 1)
    w_unnorm = np.divide(w_copy,X_stds)
    return(w_unnorm)


def error_function(X, y, w):
    '''
    Compute the error function for a particular data set and
    hypothesis (weight vector)

    Inputs:
        X       N x (d+1) data matrix
        y       target function output (length N vector)
        w       weights (length N vector)
    Output:
        err    the value of the error function (scalar)
    '''
    error = 0
    ##################################################
    # TODO: write code here to compute error correctly
    ##################################################
    y_out = np.dot(X,w)
    error = sum((y-y_out)**2) / len(y)
    return error

def normal_equations(X, y):
    '''
    Train a linear regression model using the normal equations.

    Inputs:
        X       N x (d+1) data matrix
        y       target function output (length N vector)
    Output:
        w       weights (length d+1 vector)
    '''
    # see LFD Ch. 3.2.1
    w = np.dot(
        np.linalg.pinv( np.dot(X.T, X) ),
        np.dot(X.T, y)
        )
    return w

def plot_model(x, y, w):
    '''
    Plot the data (x, y) and then the model given weights w
    '''
    # Create evenly spaced test x values (for plotting)
    x_test = np.linspace(min(x), max(x), 100)
    X_test = feature_expansion(x_test, 4)

    # Plot the data
    plt.scatter(x, y, label='Training examples')

    # Now plot the model on top of it
    y_test = np.dot(X_test, w)
    plt.plot(x_test, y_test, 'r', label='$h_{\mathbf{w}}(\mathbf{x})$')
    plt.title('Polynomial regression')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()

def gradient_descent( X, y, eta, iters, w=None ):
    '''
    Train a linear regression model by gradient descent

    Inputs:
        X       N x (d+1) data matrix
        y       target function output (length N vector)
        eta     step size
        iters   number of iterations
        w       initial parameter values (length d+1 vector; optional)

    Output:
        w           learned parameters (length d+1 vector)
        Ein_history trace of error function value in each iteration
    '''
    N, d = X.shape

    if w is None:
        w = np.zeros(d)

    # For recording error function value during gradient descent
    Ein_history = np.zeros(iters)

    for k in range(iters):
        ######################################################
        # TODO: compute gradient (vectorized) and update w
        ######################################################
        y_out = np.dot(X,w)
        update = np.sum(np.multiply(y-y_out,X.T).T,axis=0) / N
        w = w + np.multiply(update,eta)
        # Record error function
        Ein_history[k] = error_function(X, y, w)

    return w, Ein_history

