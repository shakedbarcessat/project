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
    np.random.seed(7)
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
    for i in range(len(li)):
        li[i] = cv2.imread(li[i],cv2.IMREAD_GRAYSCALE)
        norm = np.zeros((800,800))
        final = cv2.normalize(li[i],  norm, 0, 255, cv2.NORM_MINMAX)
        final_norm = final.astype('float32')
        final_norm = final_norm / 255.0    
        li[i]=final_norm
    li = np.array(li) 
    return li






















