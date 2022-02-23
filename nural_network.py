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
from keras.layers import Dense
from keras.layers import Flatten
from keras.optimizers import SGD
from numpy import array
import numpy as np

def run_model(train, test, validation):
    model = define_model()
    model = train_model(model, train[0], train[1], validation[0], validation[1])
    evaluate_model(model, test[0], test[1])

def define_model():
	model = Sequential()
	model.add(Conv2D(32, (3, 3), 
              activation='relu', 
              kernel_initializer='he_uniform', 
              input_shape=(100, 100, 3)))
    
	model.add(MaxPooling2D((2, 2)))
	model.add(Flatten())
	model.add(Dense(100, 
                    activation='relu', 
                    kernel_initializer='he_uniform'))
    
	model.add(Dense(1,
                    activation='sigmoid'))
	
    # compile model
	opt = SGD(lr=0.01, momentum=0.9)
	model.compile(optimizer=opt, 
                  loss='binary_crossentropy', 
                  metrics=['accuracy'])
    
	return model


def train_model(model, trainX ,trainY, validationX, validationY):
    model.fit(trainX, trainY, epochs=120, batch_size=280, validation_data=(validationX, validationY))
    
    return model
    
def evaluate_model(model, testX, testY):
    score = model.evaluate(testX, testY, verbose=1)
    print(score)































