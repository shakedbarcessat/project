# -*- coding: utf-8 -*-
"""
Created on Thu Mar 31 10:30:47 2022

@author: Shaked Barcessat
"""
import tkinter.messagebox as message_box
from tkinter import *
import change_pictures_names

FINISHED_ORGANIZE=False
TRAIN, TEST, VALIDATION = None, None, None

def new_window(window_to_close):
    ans = message_box.askyesnocancel(title='Alert Message!', message="I don't like your face!")
    
    if ans is True:
        root1= Tk()
        root1.mainloop()
        
def range_dataset(root):
    train, test, validation= change_pictures_names.sum()
    global TRAIN
    global TEST
    global VALIDATION
    TRAIN= train
    TEST= test
    VALIDATION= validation
root= Tk()

btn1 = Button(root, text="Range the dataset", command= lambda: new_window(root))
btn1.grid(row=0, column=0)

btn2 = Button(root, text="Select model", command= lambda: new_window(root))
btn2.grid(row=1, column=0)

btn3 = Button(root, text="Test current model", command= lambda: new_window(root))
btn3.grid(row=2, column=0)

root.mainloop()

