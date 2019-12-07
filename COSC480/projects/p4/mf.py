import numpy as np
from tqdm import tqdm

##################################################
# TODO: write any additional code here
##################################################

def error_function(r,u,v,mu,a,b):
    return r - np.dot(u,v) - mu - a - b

def sgd(num_iter,train,eta,lambda_reg,U,V,mu,a,b):
    num_ratings = np.shape(train)[0]
    rating_ids = np.arange(num_ratings)
    for j in tqdm(range(num_iter)):
        np.random.shuffle(rating_ids)
        for id in rating_ids:
            review = train.iloc[id,]
            u_id = review.loc['user_id']
            v_id = review.loc['movie_id']
            r = review.loc['rating']
            # compute e
            e = error_function(r,U[u_id,:],V[v_id,:],mu,a[u_id],b[v_id])
            # update u
            u_update = np.multiply(
                np.subtract(
                    np.multiply(e,V[v_id,:]),
                    np.multiply(lambda_reg,U[u_id,:])
                ),
                eta
            )
            U[u_id,:] = U[u_id,:] + u_update
            # update v
            v_update = np.multiply(
                np.subtract(
                    np.multiply(e,U[u_id,:]),
                    np.multiply(lambda_reg,V[v_id,:])
                ),
                eta
            )
            V[v_id,:] = V[v_id,:] + v_update
            # update biases a,b
            a_update = eta * (e - (lambda_reg * a[u_id]))
            a[u_id] = a[u_id] + a_update
            b_update = eta * (e - (lambda_reg * b[v_id]))
            b[v_id] = b[v_id] + b_update
    return U,V,a,b



def sgd_vectorized(num_iter,train,eta,lambda_reg,U,V):
    num_ratings = np.shape(train)[0]
    for j in tqdm(range(1)):
        u_ids = train.user_id
        v_ids = train.movie_id
        R = train.rating
        # extract U,V
        print(np.shape(U))
        print(np.shape(V))
        print(np.shape(u_ids))
        print(np.shape(v_ids))
        print(np.shape(R))
        # compute e
        # e = error_function(r,U[u_id,:],V[v_id,:])
        # # update u
        # u_update = np.multiply(
        #     np.subtract(
        #         np.multiply(e,V[v_id,:]),
        #         np.multiply(lambda_reg,U[u_id,:])
        #     ),
        #     eta
        # )
        # U[u_id,:] = U[u_id,:] + u_update

    return U,V

    