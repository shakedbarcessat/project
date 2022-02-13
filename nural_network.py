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


def define_model():
	model = Sequential()
	model.add(Conv2D(32, (3, 3), 
              activation='relu', 
              kernel_initializer='he_uniform', 
              input_shape=(28, 28, 1)))
    
	model.add(MaxPooling2D((2, 2)))
	model.add(Flatten())
	model.add(Dense(100, 
                    activation='relu', 
                    kernel_initializer='he_uniform'))
    
	model.add(Dense(10,
                    activation='sigmoid'))
	
    # compile model
	opt = SGD(lr=0.01, momentum=0.9)
	model.compile(optimizer=opt, 
                  loss='binary_crossentropy', 
                  metrics=['accuracy'])
    
	return model









