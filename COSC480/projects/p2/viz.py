import numpy as np
import matplotlib.pyplot as plt

def decision_boundary(x1,w):
    return((w[0]+(w[1]*x1)) / (-w[2]))

def plot_perceptron(X, y, w):
    """Plot the points X, their color using y, and decision classification using
    perceptron with weights w.

    Inputs:
    X        double array of training examples
                X[i] is ith example,
                X[i][j] is jth feature value of ith example
    y        array of labels
                y[i] is label of ith example
    w        array of weights, w[j] is jth weight

    Returns:
    None    
    """
    assert len(w) == 3, "Expecting a perceptron over two-dimension X data (x_1, x_2)"
    ##################################################
    # TODO: write code here to plot data + perceptron
    ##################################################
    ## plot data
    x0, x1, x2 = zip(*X)
    colormap = {1 : 'b', -1: 'r'}
    colors = [colormap[label] for label in y]
    plt.scatter(x1,x2,c=colors)
    ## plot decision boundary
    xx = np.linspace(min(x1), max(x1))
    plt.plot(xx,decision_boundary(xx,w))

    plt.title('Perceptron')
    plt.show
