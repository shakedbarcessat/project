# -*- coding: utf-8 -*-
"""
Created on Wed Jan 12 08:07:55 2022

@author: Student
"""
import os
import change_pictures_names
import build_directory
import split_train_test_validation




build_directory.build_directories()
data_R_books = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\recycle\books'), r'D:\ShakedProjectY\sorted\recycle\books')
data_R_cardboard = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\recycle\cardboard'), r'D:\ShakedProjectY\sorted\recycle\cardboard')
data_R_glass_bottles = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\recycle\glass bottles'), r'D:\ShakedProjectY\sorted\recycle\glass bottles')
data_R_newspapers = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\recycle\newspapers'), r'D:\ShakedProjectY\sorted\recycle\newspapers')
data_O_fruits = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\fruits'), r'D:\ShakedProjectY\sorted\not-recycle\fruits')
data_O_meat = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\meat'), r'D:\ShakedProjectY\sorted\not-recycle\meat')
data_O_nature = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\nature'), r'D:\ShakedProjectY\sorted\not-recycle\nature')
data_O_vegtables = change_pictures_names.resize(os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\vegtables'), r'D:\ShakedProjectY\sorted\not-recycle\vegtables')

"""
l_book=os.listdir(r'D:\ShakedProjectY\sorted\recycle\books')
change_pictures_names.brighting_image(l_book, r'D:\ShakedProjectY\sorted\recycle\books','\\recycle\\books', int(6000/len(l_book)), "book")

l_cardboard=os.listdir(r'D:\ShakedProjectY\sorted\recycle\cardboard')
change_pictures_names.brighting_image(l_cardboard, r'D:\ShakedProjectY\sorted\recycle\cardboard','\\recycle\\cardboard', int(6000/len(l_cardboard)), "cardboard")

l_glass_bottles=os.listdir(r'D:\ShakedProjectY\sorted\recycle\glass bottles')
change_pictures_names.brighting_image(l_glass_bottles, r'D:\ShakedProjectY\sorted\recycle\glass bottles','\\recycle\\glass bottles', int(6000/len(l_glass_bottles)), "glass_bottle")

l_newspapers=os.listdir(r'D:\ShakedProjectY\sorted\recycle\newspapers')
change_pictures_names.brighting_image(l_newspapers, r'D:\ShakedProjectY\sorted\recycle\newspapers','\\recycle\\newspapers', int(6000/len(l_newspapers)), "newspaper")

l_fruits=os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\fruits')
change_pictures_names.brighting_image(l_fruits, r'D:\ShakedProjectY\sorted\not-recycle\fruits','\\not-recycle\\fruits', int(6000/len(l_fruits)), "fruit")

l_meat=os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\meat')
change_pictures_names.brighting_image(l_meat, r'D:\ShakedProjectY\sorted\not-recycle\meat','\\not-recycle\\meat', int(6000/len(l_meat)), "meat")

l_nature=os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\nature')
change_pictures_names.brighting_image(l_nature, r'D:\ShakedProjectY\sorted\not-recycle\nature','\\not-recycle\\nature', int(6000/len(l_nature)), "nature")

l_vegtables=os.listdir(r'D:\ShakedProjectY\sorted\not-recycle\vegtables')
change_pictures_names.brighting_image(l_vegtables, r'D:\ShakedProjectY\sorted\not-recycle\vegtables','\\not-recycle\\vegtables', int(6000/len(l_vegtables)), "vegtable")
"""

train_books, test_books, validation_books = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\recycle\books'))
train_cardboard, test_cardboard, validation_cardboard =  split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\recycle\cardboard'))
train_glass_bottles, test_glass_bottles, validation_glass_bottles = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\recycle\glass bottles'))
train_newspapers, test_newspapers, validation_newspapers = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\recycle\newspapers'))

train_fruits, test_fruits, validation_fruits = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\not-recycle\fruits'))
train_meat, test_meat, validation_meat = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\not-recycle\meat'))
train_nature, test_nature, validation_nature = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\not-recycle\nature'))
train_vegtables, test_vegtables, validation_vegtables = split_train_test_validation.split_train_test_val(os.listdir(r'D:\ShakedProjectY\augmented\not-recycle\vegtables'))


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
test_total=split_train_test_validation.train_test_val_total(test_books, test_total, False)
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

y_train=split_train_test_validation.split_x_y(train_total)
y_test=split_train_test_validation.split_x_y(test_total)
y_validation=split_train_test_validation.split_x_y(validation_total)










