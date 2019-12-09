import numpy as np
from tqdm import tqdm

##################################################
# TODO: write any additional code here
##################################################

def error_function(r,u,v,mu,a,b):
    return r - np.dot(u,v) - mu - a - b

def rmse(h, r):
    resid = h - r
    cost = np.sqrt(np.mean(resid**2))
    return cost

def predict(user, movie, U, V, mu, a, b):
    '''
    PREDICT Make predictions for user/movie pairs
    Inputs: 
      model parameters
      user               vector of users
      movie              vector of movies
    
    Output:
      predictions        vector of predictions
    '''    
    user_weights = U[user,:]
    movie_weights = V[movie,:]
    user_biases = a[user]
    movie_biases = b[movie]
    predictions = np.sum(np.multiply(user_weights,movie_weights),axis=1)
    predictions = predictions + mu + user_biases + movie_biases
    return predictions

def sgd(num_iter,train_user,train_movie,train_rating,eta,lambda_reg,U,V,mu,a,b,valid_user,valid_movie,valid_rating):
    num_ratings = len(train_rating)
    rating_ids = np.arange(num_ratings)
    train_rmse = np.zeros(num_iter)
    valid_rmse = np.zeros(num_iter)
    for j in tqdm(range(num_iter)):
        np.random.shuffle(rating_ids)
        for id in rating_ids:
            r = train_rating[id]
            u_id = train_user[id]
            v_id = train_movie[id]
            u_weights = U[u_id]
            v_weights = V[v_id]
            # compute e
            e = error_function(r,u_weights,V[v_id],mu,a[u_id],b[v_id])
            # compute updates
            u_update = eta * ((e * v_weights) - (lambda_reg * u_weights))
            v_update = eta * ((e * u_weights) - (lambda_reg * v_weights))
            # perform updates
            U[u_id] = u_weights + u_update
            V[v_id] = v_weights + v_update
            # update biases a,b
            a_update = eta * (e - (lambda_reg * a[u_id]))
            a[u_id] = a[u_id] + a_update
            b_update = eta * (e - (lambda_reg * b[v_id]))
            b[v_id] = b[v_id] + b_update
        # make predictions to track error
        train_rmse[j] = rmse(predict(train_user, train_movie, U, V, mu, a, b),train_rating)
        valid_rmse[j] = rmse(predict(valid_user, valid_movie, U, V, mu, a, b),valid_rating)
    return U,V,a,b,train_rmse,valid_rmse

def sgd_minimal(num_iter,train_user,train_movie,train_rating,eta,lambda_reg,U,V,mu,a,b):
    num_ratings = len(train_rating)
    rating_ids = np.arange(num_ratings)
    for j in range(num_iter):
        np.random.shuffle(rating_ids)
        for id in rating_ids:
            r = train_rating[id]
            u_id = train_user[id]
            v_id = train_movie[id]
            u_weights = U[u_id]
            v_weights = V[v_id]
            # compute e
            e = error_function(r,u_weights,V[v_id],mu,a[u_id],b[v_id])
            # compute updates
            u_update = eta * ((e * v_weights) - (lambda_reg * u_weights))
            v_update = eta * ((e * u_weights) - (lambda_reg * v_weights))
            # perform updates
            U[u_id] = u_weights + u_update
            V[v_id] = v_weights + v_update
            # update biases a,b
            a_update = eta * (e - (lambda_reg * a[u_id]))
            a[u_id] = a[u_id] + a_update
            b_update = eta * (e - (lambda_reg * b[v_id]))
            b[v_id] = b[v_id] + b_update
    return U,V,a,b

    