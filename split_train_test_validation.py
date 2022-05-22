# -*- coding: utf-8 -*-
"""
Created on Fri Jan 21 10:26:11 2022

@author: Student
"""
import numpy as np
import cv2
import split_train_test_validation

def take_part(li, precent, number_of_elements):
    """
    Takes li and make new list in the size of the elements. The program delets the elements it already used.
    
    
    Parameters:
    num- the size we want to the new list.
    new_li- the list we return in the size of num.    
    ---------
    input:
    li- the dataset
    precent- the precent of the list we want
    number_of_elements- li size    
    ---------
    output:
    returns a list in the size of the precent.    
    ---------
    """
    num = int(number_of_elements * precent)
    new_li = []
    for element in range(num):
        new_li.append(li.pop())
        
    return new_li


def split_train_test_val(li, train = 0.7, test = 0.2, validation = 0.1):
    """
    Make 3 new lists of li, which each in the size we got with the function.
    
    
    Parameters:    
    train_data- contains train amount from li.
    test_data-contains test amount from li.
    validation_data- contains validation amount from li.
    input:
    li- the dataset.  
    train- the amount of items from li.
    train- the amount of items from li.
    train- the amount of items from li.
    ---------
    output:
    Returns train, test and validation   
    ---------
    """
    number_of_elements=len(li)
    np.random.seed(7)
    np.random.shuffle(li)
    train_data = take_part(li, train, number_of_elements)
    test_data = take_part(li, test, number_of_elements)
    validation_data = take_part(li, validation, number_of_elements)
    return train_data, test_data, validation_data

    
def train_test_val_total(li, total, sign):
    """
    Adding li to the end of total. Shuffles total if sign==true.
    
    
    Parameters:
    ---------
    input:
    li- a list
    total- the dataset
    sign- true or false    
    ---------
    output:
    Returns the list total after adding li to the end of total.  
    ---------
    """
    total.extend(li)
    if(sign):
        np.random.shuffle(total)
    return total

def split_x_y(data):
    """
    The function make labels to each element in data and returns the data and the labels.
    
    
    Parameters: 
    x- the dataset
    y- list of labels
    category- conducts 0 to recycle and 1 to not-recycle.
    ---------
    input:
    data- the dataset.    
    ---------
    output:
    Returns the data and the labels of each place in the data. 
    ---------
    """
    x=data
    y=[]
    category=" " 
    for i in range(len(x)):        
       category = (x[i].rsplit("_"))[0]
       category = (x[i].rsplit("\\"))[::-1]
       category=category[1]
       if(category=="books"):
            y.append(0)
       if(category=="newspapers"):
            y.append(0)
       if(category=="cardboard"):
            y.append(0)
       if(category=="glass bottles"):
            y.append(0)
       if(category=="fruits"):
            y.append(1)
       if(category=="meat"):
            y.append(1)
       if(category=="vegtables"):
            y.append(1)
       if(category=="nature"):
            y.append(1)
    return y, x   

    
def normalize_pixels(li):
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
    for i in range(len(li)):
        li[i] = cv2.imread(li[i])
        norm = np.zeros((800,800))
        final = cv2.normalize(li[i],  norm, 0, 255, cv2.NORM_MINMAX)
        final_norm = final.astype('float32')
        final_norm = final_norm / 255.0    
        li[i]=final_norm.reshape((100, 100, 3))
    li = np.array(li) 
    return li






















