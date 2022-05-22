# -*- coding: utf-8 -*-
"""
Created on Sun Feb 13 11:17:00 2022

@author: Student
"""

import nural_network
from sklearn.model_selection import KFold
from keras.models import Sequential
from keras.layers import Conv2D
from keras.layers import MaxPooling2D
from keras.layers import Dropout
from keras.layers import Dense
from keras.layers import Flatten
from keras.optimizers import SGD
from numpy import array
import numpy as np


def run_model(train, test, validation):
    """
    The function normalizes li and returns it.
    
    
    Parameters:    
    ---------
    input:
    train- list of train
    test- list of test
    validation- list of validation
    ---------
    output:
    Returns a normalized list.    
    ---------
    """
    model = define_model()
    model = train_model(model, train[0], train[1], validation[0], validation[1])
    evaluate_model(model, test[0], test[1])

def define_model():
    """
    The function normalizes li and returns it.
    
    
    Parameters:    
    ---------
    input:
    li- the dataset.    
    ---------
    output:
    Returns a normalized list.    
    ---------
    """
    
    model = Sequential()
    model.add(Conv2D(16, (3, 3), 
                 activation='relu', 
                 kernel_initializer='he_uniform', 
                 input_shape=(100, 100, 3)))   
        
    model.add(MaxPooling2D((2, 2)))
    
    model.add(Conv2D(32, (3, 3), 
                 activation='relu', 
                 kernel_initializer='he_uniform'))
        
    model.add(Dropout(0.2))
        
    model.add(Conv2D(64, (3, 3), 
                 activation='relu', 
                 kernel_initializer='he_uniform'))
  
    model.add(Conv2D(32, (3, 3), 
                 activation='relu', 
                 kernel_initializer='he_uniform'))
        
    model.add(MaxPooling2D((2, 2)))
    model.add(Flatten())
    
    model.add(Dense(1,
                    activation='sigmoid'))
    	
    Adam = SGD(lr=0.01, momentum=0.9)
    model.compile(optimizer=Adam, 
                     loss='binary_crossentropy', 
                     metrics=['accuracy'])
        
    return model


def train_model(model, trainX ,trainY, validationX, validationY):
    """
    The function fits the model it gets and then reutrns it.
    
    
    Parameters:    
    ---------
    input:
    model- The model we fit.
    trainX- the list of train.
    trainY- The labels of the train data.
    validationX- the list of validation.
    validationY- The labels of the validation data.
    ---------
    output:
    Returns the model after fitting it. 
    ---------
    """
    model.fit(trainX, trainY, epochs=20, batch_size=256, validation_data=(validationX, validationY))
    
    return model
    
def evaluate_model(model, testX, testY):
    """
    The function fits the model it gets and then prints the score.
    
    
    Parameters: 
    score-
    ---------
    input:
    model-The model we fit.
    testX-The list of test
    testY- The labels of the test data.
    ---------
    output:
    Prints the scores- accuracy and loss of the test fit.    
    ---------
    """
    score = model.evaluate(testX, testY, verbose=1)
    print(score)































