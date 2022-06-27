# -*- coding: utf-8 -*-
"""
Created on Wed Jan 12 08:07:55 2022

@author: Student
"""
import os
#print(sys.path)
#sys.path.remove('C:\\Users\\Student\\AppData\\Roaming\\Python\\Python37\\site-packages')

import change_pictures_names
import build_directory
import split_train_test_validation
import numpy as np
import cv2
import nural_network
import changing_data
import pyunpack



"""
unrar
"""
    
path = os.getcwd()
print(str(path))
"""
pyunpack.Archive(path+r"\sorted.rar").extractall(path+r"\out")
"""
build_directory.build_directories(path)
 #7100 pics each group
"""
num=7100
l_book=os.listdir(os.path.join(path, r'out\sorted\recycle\books'))
p = os.path.join(path, r'out\sorted\recycle\books')
change_pictures_names.brighting_image(path, l_book, p,'\\recycle\\books', int(num/len(l_book)), "book")
    
l_cardboard=os.listdir(os.path.join(path, r'out\sorted\recycle\cardboard'))
change_pictures_names.brighting_image(path, l_cardboard, os.path.join(path, r'out\sorted\recycle\cardboard'),'\\recycle\\cardboard', int(num/len(l_cardboard)), "cardboard")
    
l_glass_bottles=os.listdir(os.path.join(path, r'out\sorted\recycle\glass bottles'))
change_pictures_names.brighting_image(path, l_glass_bottles, os.path.join(path, r'out\sorted\recycle\glass bottles'),'\\recycle\\glass bottles', int(num/len(l_glass_bottles)), "glass_bottle")
    
l_newspapers=os.listdir(os.path.join(path, r'out\sorted\recycle\newspapers'))
change_pictures_names.brighting_image(path, l_newspapers, os.path.join(path, r'out\sorted\recycle\newspapers'),'\\recycle\\newspapers', int(num/len(l_newspapers)), "newspaper")
    
l_fruits=os.listdir(os.path.join(path, r'out\sorted\not-recycle\fruits'))
change_pictures_names.brighting_image(path, l_fruits, os.path.join(path, r'out\sorted\not-recycle\fruits'),'\\not-recycle\\fruits', int(num/len(l_fruits)), "fruit")
    
l_meat=os.listdir(os.path.join(path, r'out\sorted\not-recycle\meat'))
change_pictures_names.brighting_image(path, l_meat, os.path.join(path, r'out\sorted\not-recycle\meat'),'\\not-recycle\\meat', int(num/len(l_meat)), "meat")
    
l_nature=os.listdir(os.path.join(path, r'out\sorted\not-recycle\nature'))
change_pictures_names.brighting_image(path, l_nature, os.path.join(path, r'out\sorted\not-recycle\nature'),'\\not-recycle\\nature', int(num/len(l_nature)), "nature")
    
l_vegtables=os.listdir(os.path.join(path, r'out\sorted\not-recycle\vegtables'))
change_pictures_names.brighting_image(path, l_vegtables, os.path.join(path, r'out\sorted\not-recycle\vegtables'),'\\not-recycle\\vegtables', int(num/len(l_vegtables)), "vegtable")
    
    
l_book=os.listdir(os.path.join(path, r'augmented\recycle\books'))
p = os.path.join(path, r'augmented\recycle\books')
change_pictures_names.rotating_image(path, l_book, p,'\\recycle\\books', int(num/len(l_book)), "book")
    
l_cardboard=os.listdir(os.path.join(path, r'augmented\recycle\cardboard'))
change_pictures_names.rotating_image(path, l_cardboard, os.path.join(path, r'augmented\recycle\cardboard'),'\\recycle\\cardboard', int(num/len(l_cardboard)), "cardboard")
    
l_glass_bottles=os.listdir(os.path.join(path, r'augmented\recycle\glass bottles'))
change_pictures_names.rotating_image(path, l_glass_bottles, os.path.join(path, r'augmented\recycle\glass bottles'),'\\recycle\\glass bottles', int(num/len(l_glass_bottles)), "glass_bottle")
    
l_newspapers=os.listdir(os.path.join(path, r'augmented\recycle\newspapers'))
change_pictures_names.rotating_image(path, l_newspapers, os.path.join(path, r'augmented\recycle\newspapers'),'\\recycle\\newspapers', int(num/len(l_newspapers)), "newspaper")

l_fruits=os.listdir(os.path.join(path, r'augmented\not-recycle\fruits'))
change_pictures_names.rotating_image(path, l_fruits, os.path.join(path, r'augmented\not-recycle\fruits'),'\\not-recycle\\fruits', int(num/len(l_fruits)), "fruit")
    
l_meat=os.listdir(os.path.join(path, r'augmented\not-recycle\meat'))
change_pictures_names.rotating_image(path, l_meat, os.path.join(path, r'augmented\not-recycle\meat'),'\\not-recycle\\meat', int(num/len(l_meat)), "meat")
    
l_nature=os.listdir(os.path.join(path, r'augmented\not-recycle\nature'))
change_pictures_names.rotating_image(path, l_nature, os.path.join(path, r'augmented\not-recycle\nature'),'\\not-recycle\\nature', int(num/len(l_nature)), "nature")
    
l_vegtables=os.listdir(os.path.join(path, r'augmented\not-recycle\vegtables'))
change_pictures_names.rotating_image(path, l_vegtables, os.path.join(path, r'augmented\not-recycle\vegtables'),'\\not-recycle\\vegtables', int(num/len(l_vegtables)), "vegtable")
"""
    
pics = os.listdir(os.path.join(path, r'final\recycle\books'))
pics = [os.path.join(os.path.join(path, r'final\recycle\books', p)) for p in pics]
train_books, test_books, validation_books = split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path, r'final\recycle\cardboard'))
pics = [os.path.join(os.path.join(path, r'final\recycle\cardboard', p)) for p in pics]
train_cardboard, test_cardboard, validation_cardboard =  split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path, r'final\recycle\glass bottles'))
pics = [os.path.join(os.path.join(path,  r'final\recycle\glass bottles', p)) for p in pics]
train_glass_bottles, test_glass_bottles, validation_glass_bottles = split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path, r'final\recycle\newspapers'))
pics = [os.path.join(os.path.join(path, r'final\recycle\newspapers', p)) for p in pics]
train_newspapers, test_newspapers, validation_newspapers = split_train_test_validation.split_train_test_val(pics)
    
pics = os.listdir(os.path.join(path,r'final\not-recycle\fruits'))
pics = [os.path.join(os.path.join(path,r'final\not-recycle\fruits', p)) for p in pics]
train_fruits, test_fruits, validation_fruits = split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path,r'final\not-recycle\meat'))
pics = [os.path.join(os.path.join(path,r'final\not-recycle\meat', p)) for p in pics]
train_meat, test_meat, validation_meat = split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path,  r'final\not-recycle\nature'))
pics = [os.path.join(os.path.join(path, r'final\not-recycle\nature', p)) for p in pics]
train_nature, test_nature, validation_nature = split_train_test_validation.split_train_test_val(pics)
pics = os.listdir(os.path.join(path, r'final\not-recycle\vegtables'))
pics = [os.path.join(os.path.join(path,  r'final\not-recycle\vegtables', p)) for p in pics]
train_vegtables, test_vegtables, validation_vegtables = split_train_test_validation.split_train_test_val(pics)
    
train_total=[]
train_total=split_train_test_validation.train_test_val_total(train_books, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_cardboard, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_glass_bottles, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_newspapers, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_fruits, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_meat, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_nature, train_total, False)
train_total=split_train_test_validation.train_test_val_total(train_vegtables, train_total, True)
    
test_total=[]
test_total=split_train_test_validation.train_test_val_total(test_books, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_cardboard, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_glass_bottles, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_newspapers, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_fruits, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_meat, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_nature, test_total, False)
test_total=split_train_test_validation.train_test_val_total(test_vegtables, test_total, True)
validation_total=[]
validation_total=split_train_test_validation.train_test_val_total(validation_books, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_cardboard, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_glass_bottles, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_newspapers, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_fruits, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_meat, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_nature, validation_total, False)
validation_total=split_train_test_validation.train_test_val_total(validation_vegtables, validation_total, True)
    
      
    
    
y_train, x_train = split_train_test_validation.split_x_y(train_total)
y_test, x_test = split_train_test_validation.split_x_y(test_total)
y_validation, x_validation = split_train_test_validation.split_x_y(validation_total)

def send_test_images():
    return y_test, x_test     

x_train=split_train_test_validation.normalize_pixels(x_train)
x_test=split_train_test_validation.normalize_pixels(x_test)
x_validation=split_train_test_validation.normalize_pixels(x_validation) 
 
def send_train():
    return x_train, np.array(y_train), x_validation, np.array(y_validation)

def send_test():
    return x_test, np.array(y_test)
"""
nural_network.run_model((x_train, np.array(y_train)), (x_test, np.array(y_test)), (x_validation, np.array(y_validation)))
"""
