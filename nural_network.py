# -*- coding: utf-8 -*-
"""
Created on Sun Feb 13 11:17:00 2022

@author: Student
"""

import nural_network
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D
from tensorflow.keras.layers import MaxPooling2D
from tensorflow.keras.layers import Dropout
from tensorflow.keras.layers import Dense
from tensorflow.keras.layers import Flatten
from tensorflow.keras.optimizers import SGD

import matplotlib.pyplot as plt

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
    history = train_model(model, train[0], train[1], validation[0], validation[1])
    evaluate_model(history, test[0], test[1])
    
    


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
    history = model.fit(trainX, trainY, epochs=2, batch_size=256, validation_data=(validationX, validationY))
    

    accuracy = history.history['accuracy']  # each epoch train accuracy
    val_acc = history.history['val_accuracy']  # each epoch validation accuracy
    
    loss = history.history['loss']  # each epoch train loss
    val_loss = history.history['val_loss']  # each epoch validation loss
    
    epochs_range = range(2)  # a list containing numbers from 0 to the number of epochs-1
    plt.figure(figsize=(8, 8))
    
    plt.subplot(1, 2, 1)
    plt.plot(epochs_range, accuracy, label='Training Accuracy')
    plt.plot(epochs_range, val_acc, label='Validation Accuracy')
    plt.legend(loc='lower right')
    plt.title('Training and Validation Accuracy')
    
    plt.subplot(1, 2, 2)
    plt.plot(epochs_range, loss, label='Training Loss')
    plt.plot(epochs_range, val_loss, label='Validation Loss')
    plt.legend(loc='upper right')
    plt.title('Training and validation loss')
    plt.show()

    return history

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
    #history2 = model
    #the history of accuracies and losses of each epoch throughout the learning phase
    score = model.evaluate(testX, testY, verbose=1)
    print(score)
    # Generate a prediction using model.predict() 
    # and calculate it's shape:    
    print("Generate a prediction")
    prediction = model.predict(testX[:1])
    print("prediction shape:", prediction.shape)
    #model.save('savedmodel/model.h5')
