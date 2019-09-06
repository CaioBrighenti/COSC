import functools
import matplotlib.pyplot as plt


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
    pass


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
    pass


def linspace(start, stop, num=50):
    """
    (Adapted from numpy.linspace. Write your function from scratch)
    Return evenly spaced numbers over a specified interval.

    Returns num evenly spaced samples, calculated over the interval [start, stop].

    Your solution SHOULD generate the numbers lazily.
    """
    pass


def plot_fx(f, x_low, x_high, num=50):
    """
    Plots the given function over the interval [x_low, x_high].  The function is interpolated
    over num points.

    The plot should have labeled axes and a title "A simple plot of f(x)"
    """
    pass


def integrate_fn(f, x_low, x_high, num=50):
    """Approximates the integral of f(x) from x_low to x_high"""
    pass


def plot_integration(f, x_low, x_high, num=10):
    """
    Generates a plot that illustrates approximate integration of f(x) over [x_low, x_high] using a
    Riemann sum.  The parameter num controls the number of partitions in the approximation.

    The plot should have labeled axes and an appropriate title.
    """
    pass


def main():
    print("Welcome to python practice!")


if __name__ == "__main__":
    main()
