# -*- coding: utf-8 -*-
"""
Created on Fri Jan 21 10:26:11 2022

@author: Student
"""
import numpy as np
import split_train_test_validation

def take_part(li, precent, number_of_elements):
    """
    Parameters
    ---------
    
    """
    num = int(number_of_elements * precent)
    new_li = []
    for element in range(num):
        new_li.append(li.pop())
        
    return new_li


def split_train_test_val(li, train = 0.7, test = 0.2, validation = 0.1):
    number_of_elements=len(li)
    np.random.shuffle(li)
    train_data = take_part(li, train, number_of_elements)
    test_data = take_part(li, test, number_of_elements)
    validation_data = take_part(li, validation, number_of_elements)
    return train_data, test_data, validation_data

    
def train_test_val_total(li, total, sign):
    total.extend(li)
    if(sign):
        np.random.shuffle(total)
    return total

def split_x_y(data):
    x=data
    category=""
    y=[]
    for i in range(len(x)):
        category=(x[i].rsplit("_"))[0]
        if(category=="book"):
            y.append(0)
        if(category=="newspaper"):
            y.append(1)
        if(category=="cardboard"):
            y.append(2)
        if(category=="glass"):
            y.append(3)
        if(category=="fruit"):
            y.append(4)
        if(category=="meat"):
            y.append(5)
        if(category=="vegtable"):
            y.append(6)
        if(category=="nature"):
            y.append(7)
    return y
    
        