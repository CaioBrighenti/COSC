import functools
from functools import partial
import matplotlib.pyplot as plt
import matplotlib.patches as patches

def eval_polynomial(x, coeffs):
    """Evaluates a polynomial function of variable x defined by coeffs at the given x value.
    Coeffs is a list of coefficients a0, a1, ..., aN such that the polynomial is
    f(x) = a0 + x * a1 + x^2 * a2 + ... + x^N * aN

    Your solution SHOULD use built-in functions enumerate and sum.

    >>> eval_polynomial(3, [2, 0.5])
    3.5
    >>> eval_polynomial(10, [3.25, 1, 4])
    413.25
    """
    out = coeffs[0]
    for i,ele in enumerate(coeffs[1:]): 
        degree = i+1
        coef = ele
        out = out + (coef * (x**degree))
    return(out)



def make_polynomial_function(coeffs):
    """Given coefficients coeffs that define a polynomial, return a function f(x)
    that computes the polynomial.

    Your solution SHOULD use functools.partial.

    >>> f = make_polynomial_function([2, 0.5])
    >>> f(3)
    3.5
    >>> g = make_polynomial_function([3.25, 1, 4])
    >>> g(10)
    413.25
    """
    f = partial(eval_polynomial, coeffs = coeffs)
    return(f)


## modified from primer notebook
def lazy_nums(n=1, step = 1):
    while True:
        yield n  # a function with yield statement creates a generator
        n += step

def linspace(start, stop, num=50):
    """
    (Adapted from numpy.linspace. Write your function from scratch)
    Return evenly spaced numbers over a specified interval.

    Returns num evenly spaced samples, calculated over the interval [start, stop].

    Your solution SHOULD generate the numbers lazily.
    """
    step = (stop - start) / (num-1)
    num_gen = lazy_nums(start, step)
    nums = []
    for i in range(num):
        nums.append(next(num_gen))
    return(nums)


def plot_fx(f, x_low, x_high, num=50):
    """
    Plots the given function over the interval [x_low, x_high].  The function is interpolated
    over num points.

    The plot should have labeled axes and a title "A simple plot of f(x)"
    """
    x = linspace(x_low,x_high,num)
    y = []
    # calculate y values
    for point in x:
        y.append(f(point))
    # create plot
    plt.plot(x,y)
    plt.xlabel('x')
    plt.ylabel('f(x)')
    plt.title('A simple plot of f(x)')
    plt.show


def integrate_fn(f, x_low, x_high, num=50):
    """Approximates the integral of f(x) from x_low to x_high"""
    x = linspace(x_low,x_high,num)
    y = []
    # calculate y values
    for point in x:
        y.append(f(point))
    # loop over partitions
    area = 0
    for i in range(len(y)-1):
        h = (x[i+1] - x[i])
        trap_area =  h * ((y[i] + y[i+1]) / 2)
        area += trap_area
    return(area)

def plot_integration(f, x_low, x_high, num=10):
    """
    Generates a plot that illustrates approximate integration of f(x) over [x_low, x_high] using a
    Riemann sum.  The parameter num controls the number of partitions in the approximation.

    The plot should have labeled axes and an appropriate title.
    """
    x = linspace(x_low,x_high)
    y = []
    # calculate y values
    for point in x:
        y.append(f(point))
    # create plot
    plt.plot(x,y)
    plt.xlabel('x')
    plt.ylabel('f(x)')
    plt.title('A simple plot of f(x)')
    # recalculate x,y for integration partitions
    x = linspace(x_low,x_high, num)
    y = []
    # calculate y values
    for point in x:
        y.append(f(point))
    # calculate integrations
    for i in range(len(y)-1):
        plt.fill_between([x[i],x[i+1]], [y[i],y[i+1]], alpha = 0.2, color="b") 
    plt.show
    


def main():
    print("Welcome to python practice!")


if __name__ == "__main__":
    main()
